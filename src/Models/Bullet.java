package Models;

import Utils.PhysicsManager;

public class Bullet extends Vehicule{

    private Enemy target;

    public Bullet(double x, double y, int speed, int life, Enemy target) {
        super(x, y, speed, life);
        this.target = target;
    }

    public void setTarget(Enemy newTarget){
        if(this.target == null){
            this.target = newTarget;
        }
    }

    public Enemy getTarget(){
        return this.target;

    }
    
    public Bullet(double x, double y, int speed, int life) {
        super(x, y, speed, life);
    }

    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;

    public void move(){
        PhysicsManager.moveObject(this, this.target, speed);

        if (PhysicsManager.isColliding(this, this.target)) {
            target.die();
            return;
        }
    }
    
    public boolean hasHit(Vehicule v){
        return PhysicsManager.isColliding(this, v);
    }
 
}
