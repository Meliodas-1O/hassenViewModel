package Models;

import View.TankIcon;

public class Tank extends Vehicule{

    public double mana;
    public int radius;


    public Tank(double x, double y, int speed, int life, int mana, int radius) {
        super(x, y, speed, life);
        this.mana = mana;
        this.radius = radius;
    }

    public void shoot(TankIcon tankIcon) {
        Bullet bullet = new Bullet(this.getX(), this.getY(), 30, 120);
        tankIcon.addBullet(bullet);
    }
}