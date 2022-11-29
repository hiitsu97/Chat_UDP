package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    JButton joinButton = new JButton("Join");
    JTextField loginTextField = new JTextField();
    String login;
    public LoginWindow(){
        setTitle("Log in");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,150);
        setLayout(null);
        loginTextField.setBounds(25,25,235,25);
        add(loginTextField);
        joinButton.setBounds(90,65,100,25);
        add(joinButton);
        setVisible(true);
        joinButton.addActionListener(this);
    }

    public String getUserName(){
        return login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.login = loginTextField.getText();

    }
}
