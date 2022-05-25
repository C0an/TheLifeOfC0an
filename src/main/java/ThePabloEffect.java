import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThePabloEffect {

    public static int HEIGHT = 600, WIDTH = 600;

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Helvetica.ttf")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(true);
        frame.setVisible(true);

        ThePabloPanel thePabloPanel = new ThePabloPanel();
        frame.getContentPane().add(thePabloPanel, BorderLayout.CENTER);

        thePabloPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                String xString = String.valueOf(x);
                String yString = String.valueOf(y);
                frame.setTitle(xString + ", " + yString);
                thePabloPanel.setLastPoint(e);
            }

            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                String xString = String.valueOf(x);
                String yString = String.valueOf(y);
                frame.setTitle("dragged - " + xString + ", " + yString);

                Graphics g = thePabloPanel.getGraphics();
                Point lastPoint = thePabloPanel.getLastPoint();
                g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
                thePabloPanel.setLastPoint(new Point(e.getX(), e.getY()));
                g.dispose();
            }
        });
    }

    public static BufferedImage drawImage() {
        Font font = new Font("Helvetica", Font.BOLD, 14);
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(new Color(247, 140, 88));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        String text = "THE  LIFE  OF  C0AN";

        // LEFT

        int lastPos = 0;

        for (int i = 1; i < 14; i++) {
            lastPos = 30 + (i * 18);
            graphics.drawString(text, 52, lastPos);
        }

        for (int i = 1; i < 3; i++) {
            graphics.drawString(text, 54, 127 + (i * 16));
        }

        for (int i = 1; i < 8; i++) {
            graphics.drawString(text, 47, (lastPos - 7) + (i * 18));
        }

        // MIDDLE

        for (int i = 1; i < 14; i++) {
            graphics.drawString(text, 223, 95 + (i * 18));
        }

        for (int i = 1; i < 3; i++) {
            graphics.drawString(text, 226, 193 + (i * 16));
        }

        for (int i = 1; i < 10; i++) {
            graphics.drawString(text, 227, 337 + (i * 18));
        }

        // RIGHT

        for (int i = 1; i < 16; i++) {
            graphics.drawString(text, 380, 85 + (i * 18));
        }


        for (int i = 1; i < 9; i++) {
            graphics.drawString(text, 363, 340 + (i * 18));
        }

        // WEDDING GRAPHIC

        graphics.drawImage(

                ThePabloEffect.getWeddingImage(new String[]{"6d48a195-553b-467a-89ed-721dbbec1aa5"}),
                37,
                386,
                185,
                125,
                null
        );

        graphics.dispose();

        return bufferedImage;
    }

    @SneakyThrows
    public static BufferedImage getWeddingImage(String[] mcUUID) {
        BufferedImage weddingPhoto = ImageIO.read(new File("wedphoto.png"));
        Graphics2D g = weddingPhoto.createGraphics();

        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<BufferedImage> images = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            String img = "https://crafatar.com/renders/head/" + mcUUID[0];
            try { img = "https://crafatar.com/renders/head/" + mcUUID[i];
            }catch (Exception ignored){}

            images.add(ImageIO.read(new URL(img)));
        }

        g.drawImage(images.get(0), -10, 45, 64, 64, null);
        g.drawImage(images.get(1), 40, 33, 75, 75, null);
        g.drawImage(images.get(2), 105, 57, 64, 64, null);
        g.drawImage(images.get(3), 180, 63, 70, 70, null);
        g.drawImage(images.get(4), 276, 24, 70, 70, null);
        g.drawImage(images.get(5), 460, 50, -70, 70, null);
        g.drawImage(images.get(6), 503, 12, -70, 70, null);
        g.drawImage(images.get(7), 493, 23, 70, 70, null);
        g.drawImage(images.get(8), 585, 15, 70, 70, null);
        g.drawImage(images.get(9), 553, 16, 58, 58, null);

        g.dispose();

        return weddingPhoto;
    }

}
