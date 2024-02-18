package View;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;

public class EnemyIcon extends JPanel {
    private Enemy enemy;
    private Image enemyImage;

    public EnemyIcon(double x, double y, int level) {

        String imageName = "enemy.png";
        String currentDirectory = System.getProperty("user.dir");
        String siblingDirectoryPath = currentDirectory + File.separator + "assets"+ File.separator + "images";
        String imagePath = siblingDirectoryPath + File.separator + imageName;
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            enemyImage = icon.getImage();
            enemy = new Enemy(x, y, (double)level/2, 100); 
        } else {
            System.err.println("Failed to load image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int enemyX = (int) enemy.getX();
        int enemyY = (int) enemy.getY();
        g.drawImage(enemyImage, enemyX, enemyY, Enemy.ENEMY_WIDTH, Enemy.ENEMY_HEIGHT, this);
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
