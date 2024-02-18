package Controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import View.EnemyIcon;
import View.TankIcon;

public class GameManager implements ActionListener {
    
    private TankIcon tankIcon;
    private Timer timer;
    private Timer shootTimer; 
    private JLabel killedLabel;
    private JLabel levelLabel;
    private JPanel healthBar;

    public GameManager(TankIcon tankIcon, JLabel killedLabel, JLabel levLabel, JPanel healthBar) {
        this.tankIcon = tankIcon;
        this.killedLabel = killedLabel;
        this.levelLabel = levLabel;
        this.healthBar = healthBar;
        this.timer = new Timer(20, this);
        this.shootTimer = new Timer(100, new ActionListener() {
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
        tankIcon.repaint();
        addEnemyRandomly();
        int tankHealth = tankIcon.getTank().radius;
        int maxHealth = 300; 
        double healthPercentage = (double) tankHealth / maxHealth;
        int barWidth = (int) (healthPercentage * 100);
        System.out.println(healthBar.getSize());

        killedLabel.setText("Killed ennemies : " + tankIcon.getKilledEnemies());
        levelLabel.setText("Level : " + tankIcon.getTank().level + "    ");
        if(healthBar.getSize().width > 1080){
            //healthBar.setSize(new Dimension(maxHealth, 20));
            //healthBar.setPreferredSize(new Dimension(barWidth, 20));
        }
    }


    private void addEnemyRandomly() {
        Random random = new Random();
        if((random.nextInt(1000 - 200) + 100)%19==0 ){
            int enemyX = random.nextInt(1000 - 200) + 100;
            int enemyY = random.nextInt(1000 - 200) + 100;
            EnemyIcon enemyIcon = new EnemyIcon(enemyX, enemyY, tankIcon.getTank().level);
            addEnemy(enemyIcon);
        }        
    }


    public void handleShoot() {
        EnemyIcon target = tankIcon.findClosestEnemy(tankIcon.enemies);
        if(target != null)
        tankIcon.getTank().shootTarget(tankIcon, target.getEnemy());
    }


    public void addEnemy(EnemyIcon enemyIcon) {
        tankIcon.enemies.add(enemyIcon);
    }
}
