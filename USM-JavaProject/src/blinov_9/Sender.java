package blinov_9;

import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64.Encoder;
public class Sender {

	public static void main(String[] args) {
		Socket socket = null;
		
        try {
            socket = new Socket(InetAddress.getLocalHost(),7050);
            OutputStream os = socket.getOutputStream();
            
            File  inputFile = new File("D:/white_bell_flowers-t3.jpg");
    		BufferedImage input = ImageIO.read(inputFile);
    		//записываем изображение в байтовый поток вывода
    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
    		ImageIO.write(input, "jpg",bos);
    		byte[] size = ByteBuffer.allocate(4).putInt(bos.size()).array();
            os.write(size);
            os.write(bos.toByteArray()); //пересылаем байты серверу
            os.flush();
        }catch(IOException e) {
            e.printStackTrace();
        } finally {
            // закрываем сокет
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
