package Utils;

import Models.Vehicule;

public class PhysicsManager {
    
    public static void moveObject(Vehicule A, Vehicule B){
        moveCoordinate(A, B, A.getX(), B.getX(), true);
        moveCoordinate(A, B, A.getY(), B.getY(), false);
    }

    private static void moveCoordinate(Vehicule A, Vehicule B, double current, double target, boolean isXAxis) {
        if (current < target) {
            updateCoordinate(A, current, 1, isXAxis);
        } else if (current > target) {
            updateCoordinate(A, current, -1, isXAxis);
        }
    }

    private static void updateCoordinate(Vehicule A, double current, double increment, boolean isXAxis) {
        if (isXAxis) {
            A.setX(current + increment);
        } else {
            A.setY(current + increment);
        }
    }
}
