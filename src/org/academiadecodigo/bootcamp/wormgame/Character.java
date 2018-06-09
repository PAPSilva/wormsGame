package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;

/**
 * Created by codecadet on 03/06/2018.
 */
public class Character extends CircularBody2D implements Hittable, Shooter {

    private int health;
    private int minDamage;
    private double aim;
    Fireable currentWeapon;

    public Character(double mass, double radius, Vector2D position, int health, int minDamage) {

        super(mass,radius, position);
        this.currentWeapon = new Weapon(WeaponType.BAZOOKA);
        this.health = health;
        this.minDamage = minDamage;
        this.aim = 0;

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


    public void changeWeapon(Fireable weapon) {

        this.currentWeapon = weapon;

    }


    @Override
    public Projectile fire() {

        return currentWeapon.fire(getPosition(), aim);

    }


    @Override
    public void suffer(int sufferDamage){

        int damage = sufferDamage - minDamage;
        if(damage > 0 && health > 0) {
            health -= (damage < health) ? damage : health;
        }

    }


    @Override
    public Vector2D getPosition() {

        return super.getPosition();

    }


    @Override
    public double getAim() {
        return aim;
    }


    @Override
    public void turnAim() {

        aim = (Math.PI - aim);

    }

}
