package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 06/06/2018.
 */
public class WormSystem extends Body2DSystem {


    public WormSystem(int n, Vector2D gravityAcceleration, Collider collider) {
        super(n, gravityAcceleration, collider);
        System.out.println("WormSystem IS WORKING");

    }



    /*  private void update(double dt) {

        // Check forces interacting with each body
        Vector2D[] force = new Vector2D[length];
        for(int i=0; i < length; i++) {
            force[i] = new Vector2D(0.0, 0.0);
        }
        // TODO Check for torque here
        for(int i=0; i < length; i++) {
            force[i].add(interactingForces(bodies[i], dt));
        }

        // Apply force to the objects
        // TODO Apply torque to the objects
        for(int i=0; i < length; i++) {
            bodies[i].applyForce(force[i], dt);
            bodies[i].updatePosition(dt);
        }

*/
}
