import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import Controllers.GameManager;

import javax.swing.JFrame;

import View.EnemyIcon;
import View.TankIcon;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String imageName = "plane.png";
        String currentDirectory = System.getProperty("user.dir");
        String siblingDirectoryPath = currentDirectory + File.separator + "assets"+ File.separator + "images";
        String imagePath = siblingDirectoryPath + File.separator + imageName;
        
        TankIcon tankIcon = new TankIcon(imagePath);
        tankIcon.setBackground(Color.BLACK);
        
        JFrame frame = new JFrame("Tank Icon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tankIcon); 
        frame.pack(); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

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
        gameManager.startGame();


        Random random = new Random();
        for (int i = 0; i < 03; i++) {
            int enemyX = random.nextInt(frame.getWidth() - 200) + 100;
            int enemyY = random.nextInt(frame.getHeight() - 200) + 100;
            System.out.println("Je sis X " + enemyX);
            System.out.println("Je sis Y " + enemyY);
            EnemyIcon enemyIcon = new EnemyIcon(enemyX, enemyY);
            gameManager.addEnemy(enemyIcon);
        } 
    }
}