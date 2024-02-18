package View;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Models.Bullet;
import Models.Enemy;
import Models.Satellite;
import Models.Tank;
import Utils.PhysicsManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TankIcon extends JPanel {
    private Tank tank;
    private Image tankImage;
    private boolean isShooting = false; 
    private List<Bullet> bullets  = new ArrayList<>(); 
    private List<Satellite> satellites  = new ArrayList<>(); 
    public List<EnemyIcon> enemies = new ArrayList<>();

    private int killedEnemies = 0;

    public EnemyIcon findClosestEnemy( List<EnemyIcon> listEnemies) {
        double closestDistance = Double.MAX_VALUE;
        EnemyIcon closestEnemy = null;
        Tank playerTank = this.tank;  
        for (EnemyIcon enemyIcon : listEnemies) {
            Enemy enemy = enemyIcon.getEnemy();
            double distance = PhysicsManager.distance(playerTank, enemy);
            if(distance < tank.radius + 100){
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestEnemy = enemyIcon;
                }
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
            tank = new Tank(0,0, 0, 2000, 1);
        
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

        List<EnemyIcon> enemiesToRemove = new ArrayList<>();

        // The revolving ball

        for (Satellite satellite : satellites){
            SatelliteIcon satelliteIcon = new SatelliteIcon(satellite);
            satelliteIcon.paintComponent(g);
            satelliteIcon.getSatellite().move();
            for (EnemyIcon enemyIcon : enemies) {
                Enemy enemy = enemyIcon.getEnemy();
                double distance = PhysicsManager.distance(satellite, enemy);
                if (distance < 20) {
                    enemiesToRemove.add(enemyIcon);
                }
            }
        }

        // The radius
        g.setColor(Color.ORANGE);
        int circleRadius = this.tank.radius;
        g.drawOval(centerX - circleRadius, centerY - circleRadius, 2 * circleRadius, 2 * circleRadius);
    

        // The bullets
        List<Bullet> bulletsToRemove = new ArrayList<>();

        for (Bullet bullet : bullets) {
            BulletIcon bulletIcon = new BulletIcon(bullet);
            bulletIcon.paintComponent(g);
            List<EnemyIcon> closestEnemies = enemies.stream().filter(e -> e.getEnemy() == bullet.getTarget()).collect(Collectors.toList());
            
            if (closestEnemies.size() != 0) {
                bulletIcon.getBullet().move();
                bulletIcon.getBullet().hasHit();
                if (bullet.getTarget().life==0) {
                    bulletsToRemove.add(bullet);
                    enemiesToRemove.add(closestEnemies.get(0));
                }
            } else {
                bulletsToRemove.add(bullet);
            }
        }
        killedEnemies += enemiesToRemove.size();
        tank.setLevel(this,killedEnemies/10);
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

    public void addSatellite(Satellite sattelite){
        satellites.add(sattelite);
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

    public int getKilledEnemies(){
        return killedEnemies;
    }
}