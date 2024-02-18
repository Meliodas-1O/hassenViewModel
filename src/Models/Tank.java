package Models;

import View.TankIcon;

public class Tank extends Vehicule{

    public double mana;
    public int radius;

    public static int RADIUS_RATIO = 75;
    public static int MANA_RATIO = 150;
    public static int LIFE_RATIO = 200;
    public int level = 1;
    public int satelliteNumber = level;
    

    public static final int TANK_HEIGHT = 50;
    public static final int TANK_WIDTH = 70;


    public Tank(double x, double y, int speed, int mana, int _level) {
        super(x, y, speed, LIFE_RATIO*_level);
        this.mana = MANA_RATIO*level;
        this.radius = RADIUS_RATIO*level;
    }

    public void shootTarget(TankIcon tankIcon, Enemy enemy){
        System.out.println(level);
        if(!enemy.isTargeted){
            Bullet bullet = new Bullet(this.getX(), this.getY(), 1+level*0.125, 120, enemy);
            tankIcon.addBullet(bullet);        
        }
    }

    private void moveSatellite(TankIcon tankIcon){
        Satellite satellite = new Satellite(this.getX()+20, this.getY()+10, level+1.0, 99, this);
        tankIcon.addSatellite(satellite);
    }

    public void setLevel(TankIcon tankIcon, int level){
        if(this.level != level){
            this.level = level;
            upgrade(tankIcon);
        }
    }
    public void upgrade(TankIcon tankIcon){
        radius += 5;
        mana = MANA_RATIO*level;
        satelliteNumber = level;
        moveSatellite(tankIcon);
    }
}