import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import Controllers.GameManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import View.EnemyIcon;
import View.TankIcon;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        
        TankIcon tankIcon = new TankIcon();
        tankIcon.setBackground(Color.BLACK);

        // Create the top bar panel
        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.setBackground(Color.BLACK); 
        topBarPanel.setPreferredSize(new Dimension(0, 50)); 

        JLabel killedLabel = new JLabel("Enemies Killed: "+tankIcon.getKilledEnemies());
        killedLabel.setForeground(Color.WHITE);
        killedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel scoreLabel = new JLabel("Score: 0");

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel levelLabel = new JLabel("Level: "+ tankIcon.getTank().level + "    ");
        levelLabel.setForeground(Color.WHITE); 
        levelLabel.setFont(new Font("Arial", Font.BOLD, 16)); 

        JPanel healthBar = new JPanel ();
        healthBar.setOpaque(true);
        healthBar.setBackground(Color.RED);
        healthBar.setPreferredSize(new Dimension(0, 20));
        healthBar.setSize(new Dimension(0, 20));

        // Add components to the top bar panel
        topBarPanel.add(killedLabel, BorderLayout.WEST);
        topBarPanel.add(scoreLabel, BorderLayout.CENTER);
        topBarPanel.add(levelLabel, BorderLayout.EAST);
        topBarPanel.add(healthBar, BorderLayout.SOUTH);

        GameManager gameManager = new GameManager(tankIcon, killedLabel, levelLabel, healthBar);

        JFrame frame = new JFrame("Tank Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tankIcon); 
        frame.requestFocus();
        frame.add(topBarPanel, BorderLayout.NORTH); 
        frame.add(tankIcon, BorderLayout.CENTER);
        frame.pack(); 
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


        gameManager.startGame();

        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            int enemyX = random.nextInt(frame.getWidth() - 200) + 100;
            int enemyY = random.nextInt(frame.getHeight() - 200) + 100;
            EnemyIcon enemyIcon = new EnemyIcon(enemyX, enemyY,  tankIcon.getTank().level);
            gameManager.addEnemy(enemyIcon);
        }

    }
}