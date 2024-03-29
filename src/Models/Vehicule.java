package Models;

public abstract class Vehicule {
    
    private double x;
    private double y;
    public double speed;
    public int life;

    public Vehicule(double x, double y, double speed, int life) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.life = life;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void die(){
        this.life = 0;
    }    
}
