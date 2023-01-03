package Client;

import GUI.Chat;

import javax.swing.*;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Client {
    private String username;
    private MessageSender messageSender;
    private MessageReceiver messageReceiver;
    private  Chat chat;

    public Client(Chat chat, String username, JTextArea jTextArea, JTextArea userList){
        try{
            this.chat = chat;
            this.username = username;
            DatagramSocket datagramSocket = new DatagramSocket();
            this.messageReceiver = new MessageReceiver(datagramSocket, jTextArea, userList);
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
    public void sendSpecialMessage(String specialMessage) {
        Message specialMessageToSend = new Message();
        specialMessageToSend.setSender(this.username);
        specialMessageToSend.setContent(specialMessage);
        messageSender.sendMessage(specialMessageToSend);
    }
}
