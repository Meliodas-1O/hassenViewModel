package Utils;

import Models.Bullet;
import Models.Enemy;
import Models.Vehicule;

public class PhysicsManager {
    
    public static void moveObject(Vehicule A, Vehicule B, double speed) {
        double deltaX = B.getX() - A.getX();
        double deltaY = B.getY() - A.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        
        // Normalize the vector
        double normalizedDeltaX = deltaX / distance;
        double normalizedDeltaY = deltaY / distance;
    
        // Calculate the movement increments along X and Y axes
        double incrementX = normalizedDeltaX * speed;
        double incrementY = normalizedDeltaY * speed;
        
        // Update the coordinates of A
        A.setX(A.getX() + incrementX);
        A.setY(A.getY() + incrementY);
    }
    

    public static double distance(Vehicule player, Vehicule enemy){
       return Math.sqrt(Math.pow(player.getX() - enemy.getX(), 2) + Math.pow(player.getY() - enemy.getY(), 2));
    }

    public static boolean isColliding(Vehicule bullet, Vehicule enemy) {
        double distanceSquared = Math.pow(bullet.getX() - enemy.getX(), 2) + Math.pow(bullet.getY() - enemy.getY(), 2);
        double collisionDistanceSquared = Math.pow((Bullet.BULLET_WIDTH + Enemy.ENEMY_WIDTH) / 2, 2) + 100;
        if(distanceSquared <= collisionDistanceSquared){
            enemy.die();
            return true;
        }
         return false;
    }
}
