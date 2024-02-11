package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Bullet;

public class BulletIcon extends JPanel {
    
    private Bullet bullet;
    private Image bulletImage;
    JPanel parentPanel;

    public BulletIcon(Bullet _bullet) {
        String imageName = "bullet.png";
        String currentDirectory = System.getProperty("user.dir");
        String siblingDirectoryPath = currentDirectory + File.separator + "assets"+ File.separator + "images";
        String imagePath = siblingDirectoryPath + File.separator + imageName;
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            bulletImage = icon.getImage();
            bullet = _bullet;
        } else {
            System.err.println("Failed to load image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int bulletX = (int) bullet.getX(); 
        int bulletY = (int) bullet.getY(); 
        int bulletWidth = 10; 
        int bulletHeight = 10; 
        g.drawImage(bulletImage, bulletX, bulletY, bulletWidth, bulletHeight, this);
    }


    public Bullet getBullet(){
        return bullet;
    }
}
