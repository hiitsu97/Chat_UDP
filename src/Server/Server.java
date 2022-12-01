package Server;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Server implements Runnable{

    private DatagramSocket datagramSocket;
    private ArrayList<Client> clients = new ArrayList<>();
    private Gson gson = new Gson();
    private static int port = 1234;

    public Server(DatagramSocket datagramSocket) { this.datagramSocket = datagramSocket; }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(port);
        Server server = new Server(datagramSocket);
        server.run();
    }
    public void run() {
        while (true){
            byte[] buffer = new byte[256];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(datagramPacket);
                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                Message message = gson.fromJson(receivedMessage, Message.class);
                Client client = new Client();
                client.setUsername(message.getSender());
                client.setUserInetAddress(datagramPacket.getAddress());
                client.setUserPort(datagramPacket.getPort());
                clients.add(client);
                String jsonMessage = gson.toJson(message);
                byte[] senderBuffer = jsonMessage.getBytes(StandardCharsets.UTF_8);
                for (Client receiver : clients) {
                    DatagramPacket sentDatagramPacket = new DatagramPacket(senderBuffer, senderBuffer.length, receiver.getUserInetAddress(), receiver.getUserPort());
                    try {
                        datagramSocket.send(sentDatagramPacket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
