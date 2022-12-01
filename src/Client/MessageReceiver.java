package Client;

import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class MessageReceiver implements Runnable{

    private final DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];
    JTextArea jTextArea;
    Gson gson = new Gson();

    public MessageReceiver(DatagramSocket datagramSocket,JTextArea jTextArea){
        this.datagramSocket = datagramSocket;
        this.jTextArea = jTextArea;
    }

    @Override
    public void run() {
        while (true){
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                Message message = gson.fromJson(receivedMessage, Message.class);

                jTextArea.append(message.getSender() + " : " + message.getContent() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
