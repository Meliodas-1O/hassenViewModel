package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import Models.Bullet;
import Models.Tank;
import View.EnemyIcon;
import View.TankIcon;

public class GameManager implements ActionListener {
    
    private List<EnemyIcon> enemies = new ArrayList<>();
    private TankIcon tankIcon;
    private Timer timer;

    public GameManager(TankIcon tankIcon) {
        this.tankIcon = tankIcon;
        this.timer = new Timer(16, this);
    }

    public void startGame() {
        timer.start();
    }

    public void stopGame() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBullets();
        moveEnemies(); 
        tankIcon.repaint();
    }

    private void moveBullets() {
        Tank enemyTank = new Tank(1500, 0, 10, 30, 50, 130);
        for (Bullet bullet : tankIcon.getBullets()) {
            bullet.move(enemyTank, tankIcon.getEnemies());
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

    public void addEnemy(EnemyIcon enemyIcon) {
        tankIcon.enemies.add(enemyIcon);
    }
}
