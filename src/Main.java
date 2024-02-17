import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Controllers.GameManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import View.EnemyIcon;
import View.TankIcon;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        
        TankIcon tankIcon = new TankIcon();
        tankIcon.setBackground(Color.BLACK);

        JFrame frame = new JFrame("Tank Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tankIcon); 
        frame.pack(); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Create the top bar panel
        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(new BorderLayout());
        topBarPanel.setBackground(Color.BLACK); // Set background color to black
        topBarPanel.setPreferredSize(new Dimension(0, 50)); // Increase height

        JLabel killedLabel = new JLabel("Enemies Killed: 0");
        killedLabel.setForeground(Color.WHITE); // Set text color to white
        killedLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size

        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE); // Set text color to white
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size

        JLabel levelLabel = new JLabel("Level: 1 "); // Add padding to the right
        levelLabel.setForeground(Color.WHITE); // Set text color to white
        levelLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size

        // Add components to the top bar panel
        topBarPanel.add(killedLabel, BorderLayout.WEST);
        topBarPanel.add(scoreLabel, BorderLayout.CENTER);
        topBarPanel.add(levelLabel, BorderLayout.EAST);

        GameManager gameManager = new GameManager(tankIcon);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameManager.handleShoot();
                }
            }
        });
        frame.requestFocus();
        frame.add(topBarPanel, BorderLayout.NORTH); // Add the top bar panel to the top of the frame
        frame.add(tankIcon, BorderLayout.CENTER); //
        gameManager.startGame();


        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int enemyX = random.nextInt(frame.getWidth() - 200) + 100;
            int enemyY = random.nextInt(frame.getHeight() - 200) + 100;
            EnemyIcon enemyIcon = new EnemyIcon(enemyX, enemyY);
            gameManager.addEnemy(enemyIcon);
        }

    }
}