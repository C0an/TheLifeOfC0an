import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ThePabloPanel extends JPanel {

    public ThePabloPanel() {
        super();
    }

    private Point lastPoint;

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point point) {
        lastPoint = point;

    }

    public void setLastPoint(MouseEvent e) {
        lastPoint = new Point(e.getX(), e.getY());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(ThePabloEffect.drawImage(), 0, 0, null);
        repaint();
    }


}
