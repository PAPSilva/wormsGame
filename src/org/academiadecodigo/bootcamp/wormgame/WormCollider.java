package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.Body2D;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class WormCollider implements Collider {

    private final int IMPULSE_MODIFIER = 1000000;
    private Body2DCollider bodyCollider;

    public WormCollider(double tiny) {
        this.bodyCollider = new Body2DCollider(tiny);
    }

    @Override
    public boolean checkCollision(Body2D body1, Body2D body2) {
        return bodyCollider.checkCollision(body1, body2);
    }

    /**
     * While the impulse is calculated for both bodies, only body1 is affected by the game mechanics. This function should be called a second time to apply the game mechanics to body2. TODO affect both bodies at the same time
     */
    @Override
    public Vector2D[] solveCollision(Body2D body1, Body2D body2, double dt) {

        // First resolve physical collisions
        Vector2D[] impulse = bodyCollider.solveCollision(body1, body2, dt);

        // Resolve each kind of interactions specific to the game.

        // Hittable suffer damage on impact, proportional to the damage of a PainGiver.
        if(body1 instanceof Hittable) {

            int damageMultiplier = 1;
            if(body2 instanceof PainGiver) {
                damageMultiplier = ((PainGiver) body2).getDamage();
            }

            // TODO Do this properly

            int damage = ((int) (impulse[0].norm() / IMPULSE_MODIFIER)) * damageMultiplier;

            // Modify impulse damage if body2 is unmovable.
            if(!body2.isMovable()) {
                damage /= 10;
            }

            //System.out.println("Damage = " + damage + " = " + impulse[0].norm() + " / " + IMPULSE_MODIFIER + " * " + damageMultiplier);

            ((Hittable) body1).suffer(damage);

        }

        return impulse;

    }

}
