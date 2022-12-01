package GUI;

import javax.swing.*;

public class Login extends JFrame  {
    private JButton joinButton;
    private JTextField textField1;
    private JPanel Window;

    public Login() {
        setContentPane(Window);
        setTitle("UDP Chat - login");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        joinButton.addActionListener(e -> {
            if (textField1.getText().length() > 0){
                Chat chat = null;
                chat = new Chat(textField1.getText());
                chat.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login();
    }
}
