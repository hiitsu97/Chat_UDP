package Server;

import java.io.IOException;
import java.net.*;

public class Server {
    private static String inetAddr = "224.5.6.7";
    final static int port = 2137;

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(inetAddr);
        try(DatagramSocket serverSocket = new DatagramSocket()){
            for (int i = 0; i < 5; i++){
                String msg = "Sent message no " + i;
                DatagramPacket datagramPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length, inetAddress, port);
                serverSocket.send(datagramPacket);
                System.out.println("Server sent packet with msg: " + msg);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
