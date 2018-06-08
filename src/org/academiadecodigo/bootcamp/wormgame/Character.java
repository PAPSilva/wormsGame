package org.academiadecodigo.bootcamp.wormgame;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectile;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
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

    // for now, receives a double that can be positive or negative. it can increase or decrease aim.
    public void changeAim(double angle) {

        this.aim += angle;

    }

    @Override
    public Projectile fire() {

        return currentWeapon.fire(getPosition(), aim);

    }


    public void changeWeapon(Fireable weapon) {

        this.currentWeapon = weapon;

    }

    @Override
    public void suffer(int sufferDamage){

        int damage = sufferDamage - minDamage;
        System.out.println("damage: " + damage + "? health: " + health );
        if(damage > 0 && health > 0) {
            health -= (damage < health) ? damage : health;
            System.out.println("Ouch!");
        }

    }

    public Vector2D getPosition() {

        return super.getPosition();

    }




}
