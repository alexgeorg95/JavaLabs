package blinov_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerPart {
	private List<ClientsConnected> clients = new ArrayList<>(); // ������ ���� ��������
    public ServerPart() {
    	try{
    		ServerSocket server = new ServerSocket(9030);
    		
    		while(true){
    			Socket socket = server.accept();
    			System.out.println(socket.getInetAddress().getHostName()+"connected");
    			
    			ClientsConnected client = new ClientsConnected(socket);
    			clients.add(client);
    			client.start();
    			    			
    		}
    	} catch(IOException e) {
    		System.err.println(e);
    	}
    }
//�����, �������������� ������ ����������� � ��������� ������
private class ClientsConnected extends Thread {
private PrintWriter os;
private BufferedReader is;
private String name ="";
private Socket sock;
public ClientsConnected(Socket s) throws IOException{
	sock = s;
	os = new PrintWriter(s.getOutputStream());
	is = new BufferedReader(new InputStreamReader(s.getInputStream()));
}
@Override
public void run() {
	String str;
	try {
		 name = is.readLine();
		/*for (ClientsConnected c :clients){
			c.send("@" + name + ":entered the chat"); //�������� ����, ��� ������������ ����� � ���
		}*/
		String recipient = is.readLine();
		while((str = is.readLine())!=null) {
			if(str.equals("exit")) break; // ���������� �������� ��������� ��� ����� "exit" � �������� ���
			for(ClientsConnected c: clients){
			if (c.name.equals(recipient)){
			c.send("Message from @" + name + ":" + str); // ��������� ��������� ���������� �� ������ �������
				}
			}
		}
		/*for (ClientsConnected c :clients){
			send("@" + name  + ":left the chat"); // �������� ����,��� ������������ ������� ���
		}*/
	} catch(IOException e) {
		System.err.println("Disconnect");
		
	} finally {
		disconnect();
	}
}
public void send(String s) throws IOException {
	os.println(s);
	os.flush();
}
public void disconnect(){
	try{
		if(os!=null){
			os.close();
		}
		if(is!=null){
			is.close();
		}
	} catch(IOException e){
		e.printStackTrace();
	} finally {
		this.interrupt();
	}
}
}

}
