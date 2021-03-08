import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws IOException {


        JFrame jFrame = new JFrame("Server");
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabelText = new JLabel("Waiting for image");

        jFrame.add(jLabelText, BorderLayout.NORTH);

        jFrame.setVisible(true);
        ServerSocket serverSocket = new ServerSocket(1124);

        Socket socket1 = serverSocket.accept();
        System.out.println("Connection has established");
        InputStream inputStream = socket1.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
        bufferedInputStream.close();
        socket1.close();

        JLabel jLabelPic = new JLabel(new ImageIcon(bufferedImage));
        jLabelText.setText(" Got the image");
        jFrame.add(jLabelPic, BorderLayout.CENTER);

    }
}