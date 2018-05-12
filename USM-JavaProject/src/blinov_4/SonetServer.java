package blinov_4;
import java.io.*;
import java.net.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class SonetServer {

	public static void main(String[] args) {
		Socket s =null;
		PrintWriter out = null;;
		try{
			ServerSocket server = new ServerSocket(9080);
			s = server.accept();
			out = new PrintWriter(s.getOutputStream());
			String [] sonetToSend = chooseSonet(); //выбираем произвольный сонет
			for(String outMsg:sonetToSend){
			out.write(outMsg);
			out.write("\n");
			}
		}catch(IOException e){
			System.err.println(e);
		} finally{
			if(out!=null){
				out.close();
			}
			if(s!=null){
				try{
					s.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}

	}
public static String[] chooseSonet() throws IOException{
	List<String> sonets = new ArrayList<>();
	String str =null;
	try {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/sonets.txt")));
		// считываем все строки из документа и записываем в список
		while((str=br.readLine())!=null){
			sonets.add(str);
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	int randomNumber = new SecureRandom().nextInt(10);
	//каждый сонет с заголовком занимает ровно 15 строк
	String[]result = sonets.subList(15*randomNumber,15*randomNumber+15).toArray(new String [15]); 
	return result;	
}
}
