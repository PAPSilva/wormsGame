package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.utils.Vector;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

public class Tests {

    private static final double TINY = 1.0e-12;

    public static void main(String[] args) {

        assertCondition("Vector test", vectorTest());
        assertCondition("Vector2D test", vector2DTest());
        assertCondition("AbstractBody2D test (via TestBody2D)", absBodyTest());
        assertCondition("Linear collision test", linearCollisionTest());
        assertCondition("SgfxViewport test", viewportTest());

    }

    private static TestResult vectorTest() {

        Vector v = new Vector(3);
        for(int i=0; i < v.length(); i++) {
            v.set(1.0 * ((double) i+1), i);
        }
        Vector u = new Vector(v);

        // TODO Basic vector operation

        v.add(u);
        u.add(1.0);
        if(Math.abs(v.get(0) - u.get(0)) > TINY ) {
            return new TestResult(false, "adding is not calculating correctly: " + v + " " + u);
        }

        u.subtract(1.0);
        v.subtract(u);
        if(Math.abs(v.get(0) - u.get(0)) > TINY || Math.abs(v.get(1) - u.get(1)) > TINY || Math.abs(v.get(2) - u.get(2)) > TINY) {
            return new TestResult(false, "subtracting is not calculating correctly: " + v + " " + u);
        }

        // TODO Vector properties





        return new TestResult(true, null);

    }

    private static TestResult vector2DTest() {

        Vector2D v = new Vector2D(1.0, 2.0);
        Vector2D u = new Vector2D(-2.0, 3.0);
        Vector2D w = new Vector2D(1.0, 0.0);

        // Translations

        v.add(u.x(), u.y());
        if(Math.abs(v.x() + 1.0) > TINY || Math.abs(v.y() - 5.0) > TINY) {
            return new TestResult(false, "translation by scalars is calculating incorrectly " + v);
        }

        u.add(u);
        if(Math.abs(u.x() + 4.0) > TINY || Math.abs(u.y() - 6.0) > TINY) {
            return new TestResult(false, "translation by vector is calculating incorrectly " + u);
        }

        // Rotation

        w.rotate(Math.PI * 0.5);
        if(Math.abs(w.x()) > TINY || Math.abs(w.y() - 1.0) > TINY) {
            return new TestResult(false, "rotation by angle is calculating incorrectly " + w);
        }

        // Magnify

        u.multiply(2.0);
        if(Math.abs(u.x() + 8.0) > TINY || Math.abs(u.y() - 12.0) > TINY) {
            return new TestResult(false, "translation by vector is calculating incorrectly " + u);
        }

        return new TestResult(true, null);
    }

    // Abstract body test

    private static TestResult absBodyTest() {

        TestBody2D body = new TestBody2D(20.0, new Vector2D(0.0, 0.0));
        Vector2D[] forces = {new Vector2D(2000.0, 0.0)};

        // Apply force from rest

        body.applyForces(forces, 0.1);
        if(Math.abs(body.getPosition().x() - 1.0) > TINY || Math.abs(body.getPosition().y()) > TINY) {
            return new TestResult(false, "forces are not being applied correctly from rest" + body);
        }

        // Apply force from movement

        forces[0].add(-6000.0, 0.0);
        body.applyForces(forces, 0.1);
        if(Math.abs(body.getPosition().x()) > TINY || Math.abs(body.getPosition().y()) > TINY) {
            return new TestResult(false, "forces are not being applied correctly from movement " + body);
        }

        return new TestResult(true, null);
    }

    private static TestResult linearCollisionTest() {

        Body2DCollider collider = new Body2DCollider(1.0e-8);

        // Circle collisions

        CircularBody2D body1 = new CircularBody2D(10.0, 20.0, new Vector2D(0.0,0.0));
        body1.setVelocity(new Vector2D(10.0,0.0));

        CircularBody2D body2 = new CircularBody2D(10.0, 20.0, new Vector2D(20.0, 0.0));

        // Check collision detection
        if(!collider.checkCollision(body1, body2)) {
            return new TestResult(false, "Circle-circle collision detection is not working: " + body1 + body2);
        }

        // Check change in momentum
        collider.solveCollision(body1, body2, 1.0);
        if(Math.abs(body2.getVelocity().x() - 10.0) > TINY || Math.abs(body1.getVelocity().x()) > TINY) {
            return new TestResult(false, "Change in momentum of body in rest is not working: " + body1 + body2);
        }

        body1.setVelocity(new Vector2D(10.0,0.0));
        body1.setVelocity(new Vector2D(-10.0,0.0));
        if(Math.abs(body1.getVelocity().x() + 10.0) > TINY || Math.abs(body2.getVelocity().x() - 10.0) > TINY) {
            return new TestResult(false, "Change in momentum of bodies in contrary velocities is not working: " + body1 + body2);
        }

        body1.setVelocity(new Vector2D(10.0,10.0));
        body1.setVelocity(new Vector2D(-10.0,10.0));
        if(Math.abs(body1.getVelocity().x() + 10.0) > TINY || Math.abs(body2.getVelocity().x() - 10.0) > TINY) {
            return new TestResult(false, "Change in momentum of bodies in 90º velocities is not working: " + body1 + body2);
        }


        return new TestResult(true, null);

    }

    private static TestResult viewportTest() {

        SgfxViewport viewport = new SgfxViewport(400, 200, 1.0);
        viewport.translate(new Vector2D(0.0, 200.0));

        // Test equivalent scale

        Vector2D coord = new Vector2D(100.0, 50.0);
        Vector2D viewCoord = viewport.toViewportCoordinates(coord);
        if(Math.abs(coord.x() - viewCoord.x()) > TINY || Math.abs(150.0 - viewCoord.y()) > TINY) {
            return new TestResult(false, "viewCoord should be equal to coord for scale=1. coord: " + coord + " viewCoord: " + viewCoord);
        }

        coord = viewport.fromViewportCoordinates(viewCoord);
        if(Math.abs(coord.x() - viewCoord.x()) > TINY || Math.abs(coord.y() - 50.0) > TINY) {
            return new TestResult(false, "coord should be equal to viewCoord for scale=1. coord: " + coord + " viewCoord: " + viewCoord);
        }

        // Test zoomed in scale

        viewport.rescale(2.0);
        coord = new Vector2D(100.0, 50.0);
        viewCoord = viewport.toViewportCoordinates(coord);
        if(Math.abs(coord.x() - viewCoord.x()) > TINY || Math.abs(coord.y() - viewCoord.y()) > TINY) {
            return new TestResult(false, "viewCoord should be equal to coord for scale=2. coord: " + coord + " viewCoord: " + viewCoord);
        }

        coord = viewport.fromViewportCoordinates(viewCoord);
        if(Math.abs(coord.x() - viewCoord.x()) > TINY || Math.abs(coord.y() - viewCoord.y()) > TINY) {
            return new TestResult(false, "coord should be equal to viewCoord for scale=2. coord: " + coord + " viewCoord: " + viewCoord);
        }

        return new TestResult(true, null);
    }

    // Auxiliary test functions

    private static void assertCondition(String test, TestResult result) {

        System.out.println(test + ": " + (result.pass ? "OK" : "FAIL"));
        if (!result.pass) {
            System.out.println(" => " + result.message);
        }

    }

    private static class TestResult {

        public boolean pass = true;
        public String message;

        public TestResult(boolean pass, String message) {
            this.pass = pass;
            this.message = message;
        }
    }

}