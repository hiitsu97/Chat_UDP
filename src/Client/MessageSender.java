package Client;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class MessageSender implements Runnable {
    private final DatagramSocket datagramSocket;
    Gson gson = new Gson();

    public MessageSender(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void sendMessage(Message message){
        try {
        byte[] buffer = new byte[256];
        int port = 1234;
        String jsonMessage = gson.toJson(message);
        buffer = jsonMessage.getBytes(StandardCharsets.UTF_8);
        InetAddress inetAddress = InetAddress.getByName("localhost");
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){}
}
