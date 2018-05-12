package blinov_8;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.InetAddress;
	import java.net.Socket;
	import java.util.Scanner;

	public class Client {
		private BufferedReader in;
		private PrintWriter out;
		private Socket socket;

		
		public Client() {
			Scanner scan = new Scanner(System.in);
			try {
				// Подключаемся в серверу и получаем потоки(in и out) для передачи сообщений
				socket = new Socket(InetAddress.getLocalHost(),9060);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				System.out.println("Enter your nickname:");
				out.println(scan.nextLine());

				// Запускаем вывод всех входящих сообщений в консоль
				Resender resend = new Resender();
				resend.start();

				// Пока пользователь не введёт "exit" отправляем на сервер всё, что
				// введено из консоли
				String str = "";
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
		// Закрывает входной и выходной потоки и сокет
		private void close() {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception e) {
				System.err.println("Потоки не были закрыты!");
			}
		}

		/**
		 * Класс в отдельном потоке пересылает все сообщения от сервера в консоль.
		 * Работает пока не будет вызван метод setStop().
		 */
		private class Resender extends Thread {

			private boolean stoped;
			
			 //Прекращает пересылку сообщений
			public void setStop() {
				stoped = true;
			}

			/**
			 * Считывает все сообщения от сервера и печатает их в консоль.
			 * Останавливается вызовом метода setStop()
			 */
			@Override
			public void run() {
				try {
					while (!stoped) {
						String str = in.readLine();
						System.out.println(str);
					}
				} catch (IOException e) {
					System.err.println("Ошибка при получении сообщения.");
					e.printStackTrace();
				}
			}
		}

	
}
