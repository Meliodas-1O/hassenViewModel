package Models;

import java.util.List;

import Utils.PhysicsManager;
import View.EnemyIcon;

public class Bullet extends Vehicule{

    public Bullet(double x, double y, int speed, int life) {
        super(x, y, speed, life);
    }

    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;

    public void move(Vehicule v, List<EnemyIcon> enemies){
        PhysicsManager.moveObject(this, v);

        for (EnemyIcon enemyIcon : enemies) {
            Enemy enemy = enemyIcon.getEnemy();
            if (PhysicsManager.isColliding(this, enemy)) {
                enemies.remove(enemyIcon);
                return;
            }
        }
    }
 
}
