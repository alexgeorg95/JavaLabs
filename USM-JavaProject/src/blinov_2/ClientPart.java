package blinov_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientPart {
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	
	public ClientPart() {
		Scanner scan = new Scanner(System.in);
		try {
			// ������������ � ������� � �������� ������(in � out) ��� �������� ���������
			socket = new Socket(InetAddress.getLocalHost(),9030);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("Enter your nickname:");
			out.println(scan.nextLine());
			
			System.out.println("Enter nickname of the recipient:");
			out.println(scan.nextLine());

			// ��������� ����� ���� �������� ��������� � �������
			Resender resend = new Resender();
			resend.start();

			// ���� ������������ �� ����� "exit" ���������� �� ������ ��, ���
			// ������� �� �������
			String str = "";
			System.out.println("Enter your message");
			while (!str.equals("exit")) {
				str = scan.nextLine();
				out.println(str);
			}
			resend.setStop();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	// ��������� ������� � �������� ������ � �����
	private void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.err.println("������ �� ���� �������!");
		}
	}

	/**
	 * ����� � ��������� ������ ���������� ��� ��������� �� ������� � �������.
	 * �������� ���� �� ����� ������ ����� setStop().
	 */
	private class Resender extends Thread {

		private boolean stoped;
		
		 //���������� ��������� ���������
		public void setStop() {
			stoped = true;
		}

		/**
		 * ��������� ��� ��������� �� ������� � �������� �� � �������.
		 * ��������������� ������� ������ setStop()
		 */
		@Override
		public void run() {
			try {
				while (!stoped) {
					String str = in.readLine();
					System.out.println(str);
				}
			} catch (IOException e) {
				System.err.println("������ ��� ��������� ���������.");
				e.printStackTrace();
			}
		}
	}

}
