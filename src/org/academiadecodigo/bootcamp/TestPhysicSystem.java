package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxCircularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxRectangularBody2D;
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

        // Stacked circles

        SgfxCircularBody2D circle1 = new SgfxCircularBody2D(20.0, 20.0, 100.0, 300.0, simWindow);

        SgfxCircularBody2D circle2 = new SgfxCircularBody2D(20.0, 20.0, 100.0, 150.0, simWindow);

        SgfxCircularBody2D circle3 = new SgfxCircularBody2D(20.0,20.0, new Vector2D(100.0,50.0), simWindow);
        circle3.toggleMovable();

        // Box and circles

        SgfxRectangularBody2D rectangle1 = new SgfxRectangularBody2D(20.0, 150.0, 15.0, new Vector2D(200.0, 150.0), simWindow);
        rectangle1.toggleMovable();
        rectangle1.rotate(-0.75);
        System.out.println(rectangle1.getPosition());

        //SgfxCircularBody2D circle4 = new SgfxCircularBody2D(20.0,20.0, new Vector2D(200.0,400.0), simWindow);

        // Add bodies to system and start simulation

        system.add(circle1);
        system.add(circle2);
        system.add(circle3);
        system.add(rectangle1);
        //system.add(circle4);

        rectangle1.toggleGravitable();

        double dt = 0.001;
        for(int i=0; i < 10.0 / dt; i++) {

            system.update(dt, dt);

            try {
                Thread.sleep( 1 );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Ended!");

    }

}
