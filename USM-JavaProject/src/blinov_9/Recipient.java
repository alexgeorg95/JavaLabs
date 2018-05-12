package blinov_9;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class Recipient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
        try {
            socket = new Socket(InetAddress.getLocalHost(),7050);
            InputStream is = socket.getInputStream();
            
            byte[] sizeAr = new byte[4];
            is.read(sizeAr);
            //��������� ����������� ������ ������� ����
            int sizeS = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
            //���������� � ������ ���� ������ �� ��������� ������
            byte[] imageAr = new byte[sizeS];
            is.read(imageAr);

            BufferedImage imageS = ImageIO.read(new ByteArrayInputStream(imageAr));
            //���������� ����������� � ����� ����
            ImageIO.write(imageS, "jpg", new File("src/blinov_9/testimage2.jpg"));
        }catch(IOException e) {
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
