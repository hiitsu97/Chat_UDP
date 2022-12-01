package GUI;

import Client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame implements ActionListener {
    private JButton send;
    private JTextField textField1;
    private JTextArea textArea1;
    private JList list1;
    private JPanel Window;
    private JScrollPane Messages;
    private JScrollPane List;
    private Client client;

    private DefaultListModel<String> users = new DefaultListModel<>();
    public Chat(String username){
        setContentPane(Window);
        setTitle("UDP Chat ");
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        textArea1.setEditable(false);
        list1.setModel(users);
        client = new Client(this, username, textArea1);
        send.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea1.append("Me: " + textField1.getText() + "\n");
        client.sendMessage(textField1.getText());
        textField1.setText("");
    }
}
