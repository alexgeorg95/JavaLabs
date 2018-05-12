package blinov_9;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class Server {

	public static void main(String[] args) {
		try {
            ServerSocket server = new ServerSocket(7050);
            final Socket sender = server.accept();
            final Socket receiver = server.accept(); 
            try {
            	  OutputStream os = receiver.getOutputStream();
            	  InputStream is = sender.getInputStream();
            	  byte[] sizeAr = new byte[4];
                  is.read(sizeAr);
                  int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                  byte[] imageAr = new byte[size];
                  is.read(imageAr);
                  //считываем полученный массив байт, восстанавливаем изображение и записываем изображение в новый файл
                  BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
                  ImageIO.write(image, "jpg", new File("src/blinov_9/testimage1.jpg"));
                  BufferedImage imageS = ImageIO.read(new File("src/blinov_9/testimage1.jpg"));

                  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                  ImageIO.write(imageS, "jpg", byteArrayOutputStream);
                  byte[] sizeS = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                  os.write(sizeS);
                  os.write(byteArrayOutputStream.toByteArray());// отсылаем клиенту-получателю массив байт
                  os.flush();
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
