package Server;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Server implements Runnable{

    private DatagramSocket datagramSocket;
    private ArrayList<Client> clients = new ArrayList<>();
    private Gson gson = new Gson();
    private static int port = 1234;

    private HashSet<String> usernames = new HashSet();
    public Server(DatagramSocket datagramSocket) { this.datagramSocket = datagramSocket; }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        Server server = new Server(datagramSocket);
        server.run();
    }
    private void sendToMany(String s) {
        String jsonUserList = s;
        byte[] senderBuffer = jsonUserList.getBytes(StandardCharsets.UTF_8);
        Iterator var = this.clients.iterator();

        while(var.hasNext()) {
            Client receiver = (Client)var.next();
            DatagramPacket sentDatagramPacket = new DatagramPacket(senderBuffer, senderBuffer.length, receiver.getUserInetAddress(), receiver.getUserPort());

            try {
                this.datagramSocket.send(sentDatagramPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() {
        while(true) {
            byte[] buffer = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

            try {
                this.datagramSocket.receive(datagramPacket);
                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                Message message = (Message)this.gson.fromJson(receivedMessage, Message.class);
                Client client = new Client();
                client.setUsername(message.getSender());
                client.setUserInetAddress(datagramPacket.getAddress());
                client.setUserPort(datagramPacket.getPort());
                String username = message.getSender();
                String content = message.getContent();

                if(content.equals("Connect")){
                    this.usernames.add(username);
                    this.clients.add(client);
                    sendToMany(this.gson.toJson(usernames));
                }

                if(content.equals("Disconnect")){
                    clients.removeIf(clientToDelete -> clientToDelete.getUsername().equals(username));
                    usernames.remove(username);
                    sendToMany(this.gson.toJson(usernames));
                }

                sendToMany(this.gson.toJson(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}