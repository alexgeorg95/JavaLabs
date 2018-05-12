package blinov_1;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		try {
            ServerSocket server = new ServerSocket(8040);

            final Socket sender = server.accept();
            final Socket receiver = server.accept();

            BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                br = new BufferedReader(
                        new InputStreamReader(sender.getInputStream())); //сервер получает в поток ввода сообщение от отправителя
                bw = new BufferedWriter(
                        new OutputStreamWriter(receiver.getOutputStream()));
                String inputMessage = null;
                while ((inputMessage = br.readLine()) != null) {
                    System.out.println("Received from ClientSender: " + inputMessage);
                    String outputMessage = inputMessage; // отсылаем получателю сообщение без изменений
                    bw.write(outputMessage + "\n");
                    bw.flush();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    sender.close();
                    receiver.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}

}
