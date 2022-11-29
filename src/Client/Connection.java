package Client;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.ArrayList;

public class Connection {
    private static String group = "224.5.6.7";
    private static int port = 2137;
    private ArrayList<String> messages = new ArrayList<String>();
    private InetAddress groupIP = InetAddress.getByName(group);
    MulticastSocket clientSocket = new MulticastSocket(port);
    public Connection() throws IOException {
        byte [] buf = new byte[256];
        UserInterface userInterface = new UserInterface(this);
        userInterface.setTextArea(this);
        try {
            clientSocket.joinGroup(groupIP);
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);

                String msg = new String(buf, 0, buf.length);
                saveMessage(msg);
                for (String message : messages) {
                    System.out.println(message);
                }
                System.out.println("Socket 1 received msg: " + msg);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void saveMessage(String message){
        messages.add(message);
    }
    public ArrayList<String> getMessages(){
        return messages;
    }
    public void sendMessage(String message) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.getBytes().length,groupIP,port);
        clientSocket.send(datagramPacket);
    }
}
