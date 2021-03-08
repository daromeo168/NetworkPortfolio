import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1124);
        System.out.println("Connection succeed");

        JFrame jFrame = new JFrame("Client");
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabelText = new JLabel("Connected to server");

        jFrame.add(jLabelText, BorderLayout.SOUTH);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\MSI\\Desktop\\Meme\\catx.jpg");
        JLabel jLabelPic = new JLabel(imageIcon);
        JButton jButton = new JButton(" Send to server");

        jFrame.add(jLabelPic, BorderLayout.CENTER);
        jFrame.add(jButton, BorderLayout.NORTH);


        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    Image image = imageIcon.getImage();
                    BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);

                    Graphics graphics = bufferedImage.createGraphics();
                    graphics.drawImage(image, 0, 0, null);
                    graphics.dispose();

                    ImageIO.write(bufferedImage, "jpg", bufferedOutputStream);

                    bufferedOutputStream.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
