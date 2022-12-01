package Server;

import java.net.InetAddress;

public class Client {

    private String username;
    private InetAddress userInetAddress;
    private int userPort;

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
    boolean firstTime = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InetAddress getUserInetAddress() {
        return userInetAddress;
    }

    public void setUserInetAddress(InetAddress userInetAddress) {
        this.userInetAddress = userInetAddress;
    }

    public int getUserPort() {
        return userPort;
    }

    public void setUserPort(int userPort) {
        this.userPort = userPort;
    }


}
