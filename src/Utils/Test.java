package Utils;
import javax.swing.*;
import java.awt.*;

public class Test extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the center of the panel
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw a centered rectangle
        int rectWidth = 100;
        int rectHeight = 80;
        int rectX = centerX - rectWidth / 2;
        int rectY = centerY - rectHeight / 2;
        g.setColor(Color.BLUE);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Draw a centered oval
        int ovalWidth = 60;
        int ovalHeight = 40;
        int ovalX = centerX - ovalWidth / 2;
        int ovalY = centerY - ovalHeight / 2;
        g.setColor(Color.RED);
        g.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);

        // Draw a centered line
        g.setColor(Color.BLACK);
        g.drawLine(centerX - 75, centerY, centerX + 75, centerY);

        // Draw centered text
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String text = "Image";
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textX = centerX - textWidth / 2;
        int textY = centerY - 20; // Adjust vertical position
        g.drawString(text, textX, textY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Centered Custom Graphics");
        Test customPanel = new Test();
        frame.add(customPanel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}