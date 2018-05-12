package blinov_1;
import java.net.*;
import java.io.*;

public class ClientSender {

	public static void main(String[] args) {
		Socket socket = null;
		BufferedWriter bw = null;

        try {
            socket = new Socket(InetAddress.getLocalHost(),8040);
            
            bw = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())); // �������� ������ ��������� � ����� ������

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(System.in)); // ���� ������ ��� �������� � �������
            String message = null;
            System.out.print("Enter the text for receiver:");
            // ���������� ������ � ������ � ����� ������
            while ((message = br.readLine()) != null) {
                bw.write(message + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ��������� �����
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
