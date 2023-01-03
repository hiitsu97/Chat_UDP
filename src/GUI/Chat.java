package GUI;

import Client.Client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Chat extends JFrame {
    private JButton send;
    private JTextField textField1;
    private JTextArea textArea1;
    private JList list1;
    private JPanel Window;
    private JScrollPane Messages;
    private JScrollPane List;
    private JTextArea textArea2;
    private Client client;

    private boolean firstMessage = true;
    public Chat(String username){
        setContentPane(Window);
        setTitle("UDP Chat ");
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.sendSpecialMessage("Disconnect");
            }
        });

        setVisible(true);
        textArea1.setEditable(false);
        textArea2.setEditable(false);

        client = new Client(this, username, textArea1, textArea2);
        System.out.println(textArea2.getText());
        if(firstMessage){
                client.sendSpecialMessage("Connect");
                textField1.setText("");
                firstMessage = false;

        }
        send.addActionListener(e -> {
            client.sendMessage(textField1.getText());
            textField1.setText("");
        });
    }
}
