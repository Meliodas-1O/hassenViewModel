package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import Models.Bullet;
import View.EnemyIcon;
import View.TankIcon;

public class GameManager implements ActionListener {
    
    private List<EnemyIcon> enemies = new ArrayList<>();
    private TankIcon tankIcon;
    private Timer timer;
    private Timer shootTimer; // Timer for shooting bullets


    public GameManager(TankIcon tankIcon) {
        this.tankIcon = tankIcon;
        this.timer = new Timer(20, this);
        this.shootTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleShoot();
            }
        });
    }

    public void startGame() {
        timer.start();
        shootTimer.start(); 
    }

    public void stopGame() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addEnemyRandomly();
        moveBullets();
        moveEnemies(); 
        tankIcon.repaint();
    }

    private void moveBullets() {
        for (Bullet bullet : tankIcon.getBullets()) {
            EnemyIcon icon = tankIcon.findClosestEnemy();
            if(icon != null){
                bullet.setTarget(icon.getEnemy());
                bullet.move();
            }
        }
    }

    private void addEnemyRandomly() {
        Random random = new Random();
        if((random.nextInt(1000 - 200) + 100)%20==0 ){
            int enemyX = random.nextInt(1000 - 200) + 100;
            int enemyY = random.nextInt(1000 - 200) + 100;
            EnemyIcon enemyIcon = new EnemyIcon(enemyX, enemyY);
            addEnemy(enemyIcon);
        }        
    }

    private void moveEnemies() {
        for (EnemyIcon enemyIcon : enemies) {
            enemyIcon.getEnemy().attack(tankIcon.getTank());
        }
    }

    public void handleShoot() {
        tankIcon.getTank().shoot(tankIcon);
    }
    public void satelliteBro(){
        tankIcon.getTank().moveSatellite(tankIcon);
    }

    public void addEnemy(EnemyIcon enemyIcon) {
        tankIcon.enemies.add(enemyIcon);
    }
}
