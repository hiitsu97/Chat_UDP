package Client;

import GUI.Chat;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
    private String username;
    private MessageSender messageSender;
    private  Chat chat;

    public Client(Chat chat,String username, JTextArea jTextArea){
        try{
            this.chat = chat;
            this.username = username;
            DatagramSocket datagramSocket = new DatagramSocket();
            MessageReceiver messageReceiver = new MessageReceiver(datagramSocket, jTextArea);
            this.messageSender = new MessageSender(datagramSocket);
            Thread sender = new Thread(messageSender);
            Thread receiver = new Thread(messageReceiver);
            sender.start();
            receiver.start();

        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(String message) {
        Message messageToSend = new Message();
        messageToSend.setSender(this.username);
        messageToSend.setContent(message);
        messageSender.sendMessage(messageToSend);
    }
}
