package Models;

import Utils.PhysicsManager;

public class Bullet extends Vehicule{

    public Bullet(double x, double y, int speed, int life) {
        super(x, y, speed, life);
    }

    public void move(Vehicule v){
        PhysicsManager.moveObject(this, v);
    }
 
}
