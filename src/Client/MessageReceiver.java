package Client;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class MessageReceiver implements Runnable{

    private final DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];
    JTextArea jTextArea;

    JTextArea userList;
    Gson gson = new Gson();

    public MessageReceiver(DatagramSocket datagramSocket,JTextArea jTextArea, JTextArea userList){
        this.datagramSocket = datagramSocket;
        this.jTextArea = jTextArea;
        this.userList = userList;
    }

    @Override
    public void run() {
        while (true){
            try {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                try {
                    Message message = gson.fromJson(receivedMessage, Message.class);
                    jTextArea.append(message.getSender() + " : " + message.getContent() + "\n");
                } catch (JsonSyntaxException e){
                    HashSet users = (gson.fromJson(receivedMessage, HashSet.class));
                    userList.setText(users.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
