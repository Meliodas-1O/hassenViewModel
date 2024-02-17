package Utils;

import Models.Bullet;
import Models.Enemy;
import Models.Vehicule;

public class PhysicsManager {
    
    public static void moveObject(Vehicule A, Vehicule B, double speed){
        moveCoordinate(A, B, A.getX(), B.getX(),speed, true);
        moveCoordinate(A, B, A.getY(), B.getY(),speed, false);
    }

    private static void moveCoordinate(Vehicule A, Vehicule B, double current, double target, double speed, boolean isXAxis) {
        if (current < target) {
            updateCoordinate(A, current, speed, isXAxis);
        } else if (current > target) {
            updateCoordinate(A, current, -speed, isXAxis);
        }
    }

    private static void updateCoordinate(Vehicule A, double current, double increment, boolean isXAxis) {
        if (isXAxis) {
            A.setX(current + increment);
        } else {
            A.setY(current + increment);
        }
    }

    public static double distance(Vehicule player, Vehicule enemy){
       return Math.sqrt(Math.pow(player.getX() - enemy.getX(), 2) + Math.pow(player.getY() - enemy.getY(), 2));
    }

    public static boolean isColliding(Vehicule bullet, Vehicule enemy) {
        double distanceSquared = Math.pow(bullet.getX() - enemy.getX(), 2) + Math.pow(bullet.getY() - enemy.getY(), 2);
        double collisionDistanceSquared = Math.pow((Bullet.BULLET_WIDTH + Enemy.ENEMY_WIDTH) / 2, 2);
        return distanceSquared <= collisionDistanceSquared;
    }
}
