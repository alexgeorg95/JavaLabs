package blinov_1;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;

public class ClientReceiver {

	public static void main(String[] args) {
		Socket socket = null;
        BufferedReader br = null;

        try {
            socket = new Socket(InetAddress.getLocalHost(),8040);
            br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String inputMessage;
           // считываем построчно сообщение
            while ((inputMessage = br.readLine()) != null){
                System.out.println("Message from ClientSender: " + inputMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

}
