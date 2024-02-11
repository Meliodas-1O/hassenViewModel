package View;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Bullet;
import Models.Enemy;
import Models.Tank;
import Utils.PhysicsManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.List;

public class TankIcon extends JPanel {
    private Tank tank;
    private Image tankImage;
    private boolean isShooting = false; 
    private List<Bullet> bullets  = new ArrayList<>(); 
    public List<EnemyIcon> enemies = new ArrayList<>();

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


    public TankIcon(String imagePath) {
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

        // The radius
        g.setColor(Color.ORANGE);
        int circleRadius = this.tank.radius;
        g.drawOval( centerX - circleRadius - 20, centerY - circleRadius - 10, 2 * circleRadius, 2 * circleRadius);
        
        // List<EnemyIcon> enemiesToRemove = new ArrayList<>();
        // for (EnemyIcon enemyIcon : enemies) {
        //     Enemy enemy = enemyIcon.getEnemy();
        //     double distance = PhysicsManager.distance(this.tank, enemy);
        //     if (distance < this.tank.radius) {
        //         enemiesToRemove.add(enemyIcon);
        //     }
        // }
        // enemies.removeAll(enemiesToRemove);

        // The bullets
        for (Bullet bullet : bullets) {
            BulletIcon bulletIcon = new BulletIcon(bullet);
            bulletIcon.paintComponent(g);
            EnemyIcon closEnemyIcon = findClosestEnemy();
            if(closEnemyIcon != null)
            bulletIcon.getBullet().move(closEnemyIcon.getEnemy(), this.enemies);     
        }
        
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