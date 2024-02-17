package View;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Bullet;
import Models.Enemy;
import Models.Tank;
import Utils.PhysicsManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TankIcon extends JPanel {
    private Tank tank;
    private Image tankImage;
    private boolean isShooting = false; 
    private List<Bullet> bullets  = new ArrayList<>(); 
    public List<EnemyIcon> enemies = new ArrayList<>();
    private  double rotationSpeedFactor = 1; // Adjust this value to change the speed (increase for slower, decrease for faster)



    public EnemyIcon findClosestEnemy() {
        double closestDistance = Double.MAX_VALUE;
        EnemyIcon closestEnemy = null;
        Tank playerTank = this.tank;
        
        for (EnemyIcon enemyIcon : enemies) {
            Enemy enemy = enemyIcon.getEnemy();
            double distance = PhysicsManager.distance(playerTank, enemy);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestEnemy = enemyIcon;
            }
        }
        return closestEnemy;
    }

    public TankIcon() {
        String imageName = "plane.png";
        String currentDirectory = System.getProperty("user.dir");
        String siblingDirectoryPath = currentDirectory + File.separator + "assets"+ File.separator + "images";
        String imagePath = siblingDirectoryPath + File.separator + imageName;
        ImageIcon icon = new ImageIcon(imagePath);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            this.tankImage = icon.getImage();
            tank = new Tank(0,0, 0, 2000, 1000, 100);
        
        } else {
            System.err.println("Failed to load image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // The tank
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int tankWidth = 70;
        int tankHeight = 50; 
        int tankX = centerX - tankWidth / 2;
        int tankY = centerY - tankHeight / 2;
        this.tank.setX(tankX);
        this.tank.setY(tankY);
        g.drawImage(tankImage, tankX-20, tankY-10, Tank.TANK_WIDTH, Tank.TANK_HEIGHT, this);

        // The revolving ball
        int ballRadius = 5; // Adjust the radius as needed
        double ballCenterX = this.tank.getX()+20 ; // Adjust the distance from the tank center
        double ballCenterY = this.tank.getY()+10;

        rotationSpeedFactor += 0.1;
        int numBalls = 2;

        // Define colors for the balls
        Color[] ballColors = {Color.GREEN, Color.BLUE, Color.RED}; 
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw multiple revolving balls
        for (int i = 0; i < numBalls; i++) {
            double ratio = (double)i/(1);
            int ballX = (int) (ballCenterX + Math.cos(rotationSpeedFactor+(ratio)) * tank.radius);
            int ballY = (int) (ballCenterY + Math.sin(rotationSpeedFactor+(ratio)) * tank.radius);
            g2d.setColor(ballColors[i]);
            g2d.fillOval(ballX - ballRadius, ballY - ballRadius, 2 * ballRadius, 2 * ballRadius);
        }
        
        List<EnemyIcon> enemiesToRemove = new ArrayList<>();
        for (EnemyIcon enemyIcon : enemies) {
            Enemy enemy = enemyIcon.getEnemy();
            double distance = PhysicsManager.distance(this.tank, enemy);
            if (distance < this.tank.radius) {
                enemiesToRemove.add(enemyIcon);
            }
        }
        enemies.removeAll(enemiesToRemove);

        // The bullets
        List<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            BulletIcon bulletIcon = new BulletIcon(bullet);
            bulletIcon.paintComponent(g);
            EnemyIcon randomEnemyIcon = findClosestEnemy();
            if (randomEnemyIcon != null) {
                bulletIcon.getBullet().setTarget(randomEnemyIcon.getEnemy());
                bulletIcon.getBullet().move();
                if (bulletIcon.getBullet().hasHit(randomEnemyIcon.getEnemy()) || bullet.getTarget().life==0) {
                    bulletsToRemove.add(bullet);
                    enemiesToRemove.add(randomEnemyIcon);
                }
            } else {
                bulletsToRemove.add(bullet);
            }
        }
        
        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);

       // Ennemies 
        for (EnemyIcon enemyIcon : enemies) {
            enemyIcon.paintComponent(g);
            enemyIcon.getEnemy().attack(tank);
        }
        
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
        repaint();
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
        repaint();
    }

    public Tank getTank() {
        return tank;
    }

    public boolean isShooting(){
        return isShooting;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<EnemyIcon> getEnemies() {
        return enemies;
    }
}