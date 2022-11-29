package Client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface extends JFrame implements ActionListener {
    JButton sendButton = new JButton("Send");
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    Connection connection;
    public UserInterface(Connection connection){
        this.connection = connection;
        String username = "Dominik Kot";
        setTitle("IRC Client -" + username);
        setSize(700,475);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JList list = new JList<>();
        ScrollPane listSlider = new ScrollPane();
        listSlider.setBounds(635,20,25,360);
        add(listSlider);
        list.setBounds(460,20, 175, 360);
        add(list);
        sendButton.setBounds(460,400, 200,25);
        sendButton.addActionListener(this);
        add(sendButton);
        textField.setBounds(25,400,400,25);
        add(textField);
        textArea.setBounds(25,25,375,360);
        add(textArea);
        ScrollPane chatSlider = new ScrollPane();
        chatSlider.setBounds(400, 25, 25,360);
        add(chatSlider);
        setVisible(true);
    }

    public void login(ArrayList<String> users){
        Scanner userInput = new Scanner(System.in);
        String userName = userInput.nextLine();

        for (String username : users) {
            if(userName.equals(userName)){
                System.out.println("nazwa zajęta");
            } else {

            }
        }
    }
    public void setTextArea(Connection connection){
        ArrayList<String> messages = connection.getMessages();
        for (String message : messages) {
            textArea.setText(message);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = textField.getText();
        try {
            connection.sendMessage(message);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
