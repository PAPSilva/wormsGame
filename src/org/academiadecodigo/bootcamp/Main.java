package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxCircularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class Main {

    public static void main(String[] args) {

        SgfxViewport simWindow = new SgfxViewport(640,400, 1.0);
        simWindow.show();

        SgfxCircularBody2D circle = new SgfxCircularBody2D(20.0, 20.0, 100.0, 50, simWindow);

        System.out.println(circle);
        circle.changeMomentum(new Vector2D(2000.0, 0.0));
        System.out.println(circle);

        SgfxCircularBody2D circle2 = new SgfxCircularBody2D(20.0,20.0, new Vector2D(400.0,50.0), simWindow);


        if(circle.checkCollision(circle2)) {
            System.out.println("IMPACT1!");
            return;
        }

        double dt = 0.05;
        for(int i=0; i < 5.0 / dt; i++) {
            Vector2D force = new Vector2D(0.0,-980.0);

            // Check collision
            if(circle.checkCollision(circle2)) {
                System.out.println("IMPACT!");
                circle.solveCollision(circle2);
            }

            //circle.applyForce(force, dt);
            circle.updatePosition(dt);
            circle2.updatePosition(dt);


            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(circle);
        System.out.println(circle2);

        System.out.println("Ended!");

    }

}
