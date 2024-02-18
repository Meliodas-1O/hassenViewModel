package Models;

import Utils.PhysicsManager;

public class Bullet extends Vehicule{

    private Enemy target;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;

    public Bullet(double x, double y, double speed, int life, Enemy target) {
        super(x, y, speed, life);
        if(this.target == null && !target.isTargeted){
            this.target = target;
            target.isTargeted = true;
        }
    }

    public void setTarget(Enemy newTarget){
        if(this.target == null){
            this.target = newTarget;
            newTarget.isTargeted = true;
        }
    }

    public Enemy getTarget(){
        return this.target;

    }



    public void move(){
        PhysicsManager.moveObject(this, this.target, speed);

        if (PhysicsManager.isColliding(this, this.target)) {
            target.die();
            return;
        }
    }
    
    public boolean hasHit(){
        return PhysicsManager.isColliding(this, target);
    }
 
}
