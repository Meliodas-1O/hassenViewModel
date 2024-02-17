package Models;

import View.TankIcon;

public class Tank extends Vehicule{

    public double mana;
    public int radius;
    
    public static final int TANK_HEIGHT = 50;
    public static final int TANK_WIDTH = 70;

    public Tank(double x, double y, int speed, int life, int mana, int radius) {
        super(x, y, speed, life);
        this.mana = mana;
        this.radius = radius;
    }

    public void shoot(TankIcon tankIcon) {
        Bullet bullet = new Bullet(this.getX(), this.getY(), 3, 120);
        tankIcon.addBullet(bullet);
    }

    public void moveSatellite(TankIcon tankIcon){
        Satellite satellite = new Satellite(this.getX()+20, this.getY()+10, 1.0, 99, this);
        tankIcon.addSatellite(satellite);
    }
}