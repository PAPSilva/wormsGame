package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxCircularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class TestPhysicSystem {

    public static void main(String[] args) {

        // Start viewport
        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();

        // Start system
        Body2DCollider collider = new Body2DCollider(1.0E-8);
        Vector2D gravity = new Vector2D(0.0,-980.0);
        Body2DSystem system = new Body2DSystem(10, gravity, collider);

        // Add bodies to system

        SgfxCircularBody2D circle = new SgfxCircularBody2D(20.0, 20.0, 100.0, 150.0, simWindow);

        System.out.println(circle);
        //circle.setVelocity(new Vector2D(100.0, 100.0));
        System.out.println(circle);

        SgfxCircularBody2D circle2 = new SgfxCircularBody2D(20.0,20.0, new Vector2D(100.0,50.0), simWindow);
        circle2.toggleMovable();

        system.add(circle);
        system.add(circle2);

        double dt = 0.001;
        for(int i=0; i < 5.0 / dt; i++) {

            system.update(dt, dt);

            try {
                Thread.sleep( 1 );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println(circle);
        System.out.println(circle2);

        System.out.println("Ended!");

    }

}
