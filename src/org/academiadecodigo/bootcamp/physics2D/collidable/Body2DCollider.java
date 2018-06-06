package org.academiadecodigo.bootcamp.physics2D.collidable;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.utils.Vector;

public class Body2DCollider implements Collider {

    private final double TINY;
    private final double INFINIY = 1.0e20;

    public Body2DCollider(double tiny) {
        TINY = tiny;
    }

    @Override
    public boolean checkCollision(Body2D body1, Body2D body2) {

        if(checkCircleCircleCollision(body1, body2)) {
            return true;
        }

        if(checkCircleRectangleCollision(body1, body2)) {
            return true;
        }

        return false;
    }

    private boolean checkCircleCircleCollision(Body2D body1, Body2D body2) {

        // Check if both bodies are circles
        if(!(body1 instanceof CircularBody2D) || !(body2 instanceof CircularBody2D)) {
            return false;
        }

        CircularBody2D circBody1 = (CircularBody2D) body1;
        CircularBody2D circBody2 = (CircularBody2D) body2;

        // Distance between centers should be lower than their radius combined, if colliding
        double maxDistance = circBody1.getRadius() + circBody2.getRadius();
        return pointInCircle(circBody1.getPosition(), circBody2.getPosition(), maxDistance);

    }

    private boolean checkCircleRectangleCollision(Body2D body1, Body2D body2) {

        // Check if one is a circle
        if(!(body1 instanceof CircularBody2D || body2 instanceof CircularBody2D)) {
            return false;
        }

        // Check if the other is a rectangle
        if(!(body1 instanceof RectangularBody2D || body2 instanceof RectangularBody2D)) {
            return false;
        }

        CircularBody2D circle = (body1 instanceof CircularBody2D) ?
                (CircularBody2D) body1 : (CircularBody2D) body2;

        RectangularBody2D rectangle = (body1 instanceof RectangularBody2D) ?
                (RectangularBody2D) body1 : (RectangularBody2D) body2;

        // Distance between circle center and each rectangle's face should be smaller than the radius, if colliding
        Vector2D center = circle.getPosition();
        Vector2D[] corners = rectangle.getCorners();
        double radius = circle.getRadius();

        return pointInPolygonVicinity(center, corners, radius);

    }

    private boolean pointInCircle(Vector2D point, Vector2D center, double radius) {

        Vector2D toCenter = new Vector2D(point);
        toCenter.subtract(center);
        return toCenter.norm() <= radius;

    }

    private boolean pointInPolygonVicinity(Vector2D point, Vector2D[] polygonCorners, double distance) {

        int penetrations = 0;
        for(int i=0; i < polygonCorners.length; i++) {

            // Next vertex
            int j = (i + 1) % polygonCorners.length;

            // Calculate normal to side
            Vector2D normal = new Vector2D(polygonCorners[j]);
            normal.subtract(polygonCorners[i]);
            normal.rotate(Math.PI * 0.5);
            normal.divide(normal.norm());

            // Is it below the line
            if( penetrationThroughLine(point, normal, polygonCorners[i]) <= distance) {
                penetrations++;
            }

        }

        if(penetrations==polygonCorners.length) {
            System.out.println("COLLIDING");
        }
        // Point is only inside if it is below all sides
        return penetrations == polygonCorners.length;

    }

    /**
     * @param point to check if its distance normal to the line.
     * @param normal perpendicular vector that defines the up side of the line.
     * @param linePoint point belonging to the line.
     * @return distance from line. Negative if below, positive other wise and zero if in the line.
     */
    private double penetrationThroughLine(Vector2D point, Vector2D normal, Vector2D linePoint) {
        return point.dot(normal) - linePoint.dot(normal);
    }


    @Override
    public Vector2D[] solveCollision(Body2D body1, Body2D body2, double dt) {

        // Solve the case of two circles colliding
        if((body1 instanceof CircularBody2D) && (body2 instanceof CircularBody2D)) {
            CircularBody2D circBody1 = (CircularBody2D) body1;
            CircularBody2D circBody2 = (CircularBody2D) body2;
            Vector2D[] forces = solveCircleCircleCollision(circBody1, circBody2, dt);
            return forces;
        }

        // Solve the case for a circle and a rectangle colliding
        if(body1 instanceof CircularBody2D && body2 instanceof RectangularBody2D) {
            CircularBody2D circle = (CircularBody2D) body1;
            RectangularBody2D rectangle = (RectangularBody2D) body2;
            Vector2D[] forces = solveCircleRectangleCollision(circle, rectangle, dt, true);
            return forces;
        }
        if(body1 instanceof RectangularBody2D && body2 instanceof CircularBody2D) {
            CircularBody2D circle = (CircularBody2D) body2;
            RectangularBody2D rectangle = (RectangularBody2D) body1;
            Vector2D[] forces = solveCircleRectangleCollision(circle, rectangle, dt, false);
            return forces;
        }

        // No collisions
        Vector2D impulse = new Vector2D(0.0, 0.0);
        Vector2D[] impulses = {impulse, impulse};
        return impulses;

    }

    private Vector2D[] solveCircleCircleCollision(CircularBody2D body1, CircularBody2D body2, double dt) {

        // Get masses
        double mass1 = body1.isMovable() ? body1.getMass() : INFINIY;
        double mass2 = body2.isMovable() ? body2.getMass() : INFINIY;

        // Get collision tangent (unit vector)
        Vector2D tangent = new Vector2D(body2.getPosition());
        tangent.subtract(body1.getPosition());
        tangent.divide(tangent.norm());

        // Get tangent component of velocity of both bodies
        Vector2D tangentVelocity1 = new Vector2D(tangent);
        tangentVelocity1.multiply( body1.getVelocity().dot(tangent) );
        Vector2D tangentVelocity2 = new Vector2D(tangent);
        tangentVelocity2.multiply( body2.getVelocity().dot(tangent) );

        // Get perpendicular component of velocity of both bodies
        Vector2D perpVelocity1 = new Vector2D(body1.getVelocity());
        perpVelocity1.subtract(tangentVelocity1);
        Vector2D perpVelocity2 = new Vector2D(body2.getVelocity());
        perpVelocity2.subtract(tangentVelocity2);

        // Collide only if velocities are converging
        // TODO this code is buggy, but something similar might be needed in the recent future...
//        Vector2D velocity = new Vector2D(body2.getVelocity());
//        velocity.subtract(body1.getVelocity());
//        double convergence = velocity.dot(perpendicular);
//        System.out.println("Convergence " + convergence);
//        if(convergence > TINY) {
//            Vector2D impulse = new Vector2D(0.0, 0.0);
//            Vector2D[] impulses = {impulse, impulse};
//            return impulses;
//        }

        // Calculate final tangent, total velocities with restitution and apply them
        // TODO deal with sinking objects
        Vector2D newTangentVelocity1 = calculateCircularVelocity(
                mass1, mass2, tangentVelocity1, tangentVelocity2);
        newTangentVelocity1.multiply(body1.getRestitution());
        Vector2D finalVelocity1 = new Vector2D(perpVelocity1);
        finalVelocity1.add(newTangentVelocity1);
        Vector2D newTangentVelocity2 = calculateCircularVelocity(
                mass2, mass1, tangentVelocity2, tangentVelocity1);
        newTangentVelocity2.multiply(body2.getRestitution());
        Vector2D finalVelocity2 = new Vector2D(perpVelocity2);
        finalVelocity2.add(newTangentVelocity2);

        // Return the impulses
        Vector2D impulse1 = body1.getImpulse(finalVelocity1);
        Vector2D impulse2 = body2.getImpulse(finalVelocity2);
        impulse1.divide(dt);
        impulse2.divide(dt);
        Vector2D[] impulses = {impulse1, impulse2};

        return impulses;

    }

    private Vector2D[] solveCircleRectangleCollision(CircularBody2D circle, RectangularBody2D rectangle, double dt, boolean returnCircleFirst) {

        System.out.println("COLIDING!");
        // Get masses
        double mass1 = circle.isMovable() ? circle.getMass() : INFINIY;
        double mass2 = rectangle.isMovable() ? rectangle.getMass() : INFINIY;

        // Get normal (unit) collision vector
        Vector2D normal = getCircleRectangleCollisionNormal(circle, rectangle);

        // Get perpendicular component of velocity of both bodies
        Vector2D perpVelocity1 = new Vector2D(normal);
        perpVelocity1.multiply( circle.getVelocity().dot(normal) );
        Vector2D perpVelocity2 = new Vector2D(normal);
        perpVelocity2.multiply( rectangle.getVelocity().dot(normal) );

        // Get tangent component of velocity of both bodies
        Vector2D tangentVelocity1 = circle.getVelocity();
        tangentVelocity1.subtract(perpVelocity1);
        Vector2D tangentVelocity2 = rectangle.getVelocity();
        tangentVelocity2.subtract(perpVelocity2);

        // Calculate final tangent, total velocities with restitution and apply them
        // TODO deal with sinking objects
        Vector2D newTangentVelocity1 = calculateCircularVelocity(
                mass1, mass2, tangentVelocity1, tangentVelocity2);
        newTangentVelocity1.multiply(circle.getRestitution());
        Vector2D finalVelocity1 = new Vector2D(perpVelocity1);
        finalVelocity1.add(newTangentVelocity1);
        Vector2D newTangentVelocity2 = calculateCircularVelocity(
                mass2, mass1, tangentVelocity2, tangentVelocity1);
        newTangentVelocity2.multiply(rectangle.getRestitution());
        Vector2D finalVelocity2 = new Vector2D(perpVelocity2);
        finalVelocity2.add(newTangentVelocity2);

        // Return the impulses
        Vector2D impulse1 = returnCircleFirst ?
            circle.getImpulse(finalVelocity1) : rectangle.getImpulse(finalVelocity2);
        Vector2D impulse2 = returnCircleFirst ?
                rectangle.getImpulse(finalVelocity2) : circle.getImpulse(finalVelocity1);
        impulse1.divide(dt);
        impulse2.divide(dt);
        Vector2D[] impulses = {impulse1, impulse2};

        return impulses;

    }

    /**
     * This method should only be called after a collision was detected.
     * @param circle
     * @param rectangle
     * @throws NullPointerException in the case of no collision
     * @return collision normal vector
     */
    private Vector2D getCircleRectangleCollisionNormal(CircularBody2D circle, RectangularBody2D rectangle) {

        // Case if the contact was at a vertex
//        for(Vector2D corner : rectangle.getCorners()) {
//
//            if(pointInCircle(corner, circle.getPosition(), circle.getRadius())) {
//                Vector2D normal = new Vector2D(corner);
//                normal.subtract(circle.getPosition());
//                normal.divide(normal.norm());
//                return normal;
//            }

//        }

        // Case it it was at a face
        Vector2D effPosition;
        Vector2D[] corners = rectangle.getCorners();
        double penetration = circle.getRadius() + TINY;
        double currPenetration;
        Vector2D normalCollision = null;
        for(int i=0; i < corners.length; i++) {

            // Next vertex
            int j = (i + 1) % corners.length;

            // Calculate normal to side
            Vector2D normal = new Vector2D(corners[j]);
            normal.subtract(corners[i]);
            normal.rotate(Math.PI * 0.5);
            normal.divide(normal.norm());

            effPosition = new Vector2D(normal);
            effPosition.multiply(-circle.getRadius());
            effPosition.add(circle.getPosition());
            currPenetration = penetrationThroughLine(effPosition, normal, corners[i]);
            if(currPenetration < penetration) {
                penetration = currPenetration;
                normalCollision = new Vector2D(normal);
            }

        }
        if(penetration < -TINY) {
            return normalCollision;
        }

        // Case of no collision
        throw new NullPointerException("No collision detected. This method should only be called in the case of a collision.");

    }

    private Vector2D calculateCircularVelocity(double mass1, double mass2, Vector2D velocity1, Vector2D velocity2) {

        // New velocity according to conservation of momentum
        // TODO do inelastic collision
        Vector2D finalVelocity = new Vector2D(velocity1);
        finalVelocity.multiply(mass1 - mass2);
        Vector2D temp = new Vector2D(velocity2);
        temp.multiply(2.0 * mass2);
        finalVelocity.add(temp);
        finalVelocity.multiply( 1.0 / (mass1 + mass2));

        return finalVelocity;

    }

}
