package Models;

import Utils.PhysicsManager;

public class Enemy extends Vehicule{

    public static final int ENEMY_WIDTH = 30;
    public static final int ENEMY_HEIGHT = 30;

    public Enemy(double x, double y, int speed, int life) {
        super(x, y, speed, life);
    }

    public void attack(Tank playerTank){
        PhysicsManager.moveObject(this, playerTank);
    }
    
}
