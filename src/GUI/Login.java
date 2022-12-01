package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JButton joinButton;
    private JTextField textField1;
    private JPanel Window;

    public Login() {
        setContentPane(Window);
        setTitle("UDP Chat - login");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        joinButton.addActionListener(this);

    }

    public static void main(String[] args) {
        Login login = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textField1.getText().length() > 0){
            Chat chat = null;
            chat = new Chat(textField1.getText());
            chat.setVisible(true);
            setVisible(false);
        }
    }
}
