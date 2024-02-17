package Models;

import java.awt.Color;

import Utils.PhysicsManager;

public class Satellite extends Vehicule{

    private Tank tank;

    
    public static final int SATELLITE_WIDTH = 10;
    public static final int SATELLITE_HEIGHT = 10;

    public Satellite(double x, double y, double speed, int life, Tank tank) {
        super(x, y, speed, life);
        this.tank = tank;
    }

    public boolean hasHit(Vehicule v){
        return PhysicsManager.isColliding(this, v);
    }
    
    public void move(){
        double ballCenterX = this.tank.getX()+20 ; 
        double ballCenterY = this.tank.getY()+10;
        this.setX(ballCenterX + Math.cos(speed) * tank.radius) ;
        this.setY(ballCenterY + Math.sin(speed) * tank.radius);   
        speed++;     
    }

}
