package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character extends CircularBody2D implements Hittable, Shooter, Controllable {

    private int health;
    private int minDamage;
    private double aim;

    private Fireable currentWeapon = new Weapon(WeaponType.BAZOOKA); // TODO change this to get from player
    private boolean active = false;

    public Character(double mass, double radius, Vector2D position, int health, int minDamage) {

        super(mass,radius, position);
        this.health = health;
        this.minDamage = minDamage;
        this.aim = 0;
        this.active = false;


    }

    public void move(double x, double y) {
        this.getPosition().add(x, y);
    }

    public void changeAim(double angle) {

        if(aim + angle < Math.PI * 0.45 && aim + angle > Math.PI * - 0.45) {
            aim += angle;
        }
        if(aim - angle > Math.PI * 0.55 && aim - angle < Math.PI * 1.45) {
            aim -= angle;
        }

        // Aim is to the right and aiming higher
        //this.aim += (angle > 0 && Math.cos(angle) > 0 && aim < Math.PI * 0.45) ? angle : 0.0;
        // Aim is to the left and aiming higher
        //this.aim += (angle > 0 && Math.cos(angle) < 0 && aim > Math.PI * 0.45) ? -angle : 0.0;
        // Aim is to the right and aiming lower
        //this.aim += (angle < 0 && Math.cos(angle) > 0 && aim > -Math.PI * 0.45) ? angle : 0.0;
        // Aim is to the left and aiming higher
        //this.aim += (angle < 0 && Math.cos(angle) < 0 && aim > 3.0 * Math.PI * 0.45) ? -angle : 0.0;

    }

    @Override
    public Projectile fire() {
        return currentWeapon.fire(getPosition(), aim);
    }

    public void changeWeapon(Fireable weapon) {
        System.out.print("Changed from " + currentWeapon.getWeaponType());
        this.currentWeapon = weapon;
        System.out.println(" to " + getWeapon().getWeaponType());
    }

    @Override
    public boolean suffer(int sufferDamage){

        int damage = sufferDamage - minDamage;
        if(damage > 0 && health > 0) {
            health -= (damage < health) ? damage : health;
            System.out.println("Ouch");
            return true;
        }

        return false;

    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    public Vector2D getPosition() {
        return super.getPosition();
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void toggleActive() {
        active = !active;
    }

    @Override
    public double getAim() {
        return aim;
    }

    @Override
    public void turnAim() {
        aim = (Math.PI - aim);
    }

    public Fireable getWeapon() {

        return currentWeapon;

    }


}
