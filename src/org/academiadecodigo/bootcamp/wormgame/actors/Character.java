package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.gfx.SgfxCharacter;
import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.sound.SoundFX;

public class Character extends CircularBody2D implements Hittable, Shooter, Controllable {

    private int health;
    private int minDamage;
    private double aim;

    private Fireable currentWeapon = new Weapon(WeaponType.BAZOOKA); // TODO change this to get from player
    private boolean active = false;

    public Character(double mass, double radius, Vector2D position, int health, int minDamage) {

        super(mass, radius, position);
        this.health = health;
        this.minDamage = minDamage;
        this.aim = 0;
        this.active = false;

    }

    @Override
    public int health() {
        return health;
    }

    @Override
    public boolean suffer(int sufferDamage) {

        if (sufferDamage > minDamage && health > 0) {
            health -= (sufferDamage < health) ? sufferDamage : health;
            System.out.println("Ouch");
            return true;
        }

        return false;

    }

    @Override
    public boolean isDead() {
        if (health <= 0) {
            System.out.println("I'm dead!");
            ((SgfxCharacter) this).setDeathPic();
        }
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
    public Fireable getWeapon() {
        return currentWeapon;
    }

    @Override
    public void changeWeapon(Fireable fireable) {
        this.currentWeapon = fireable;
    }

    @Override
    public Projectile fire() {
        SoundFX.playOnce("sounds/shooting.wav");
        return currentWeapon.fire(getPosition(), aim);

    }

    @Override
    public void changeAim(double angle) {

        if (aim + angle < Math.PI * 0.45 && aim + angle > Math.PI * -0.45) {
            aim += angle;
        }
        if (aim - angle > Math.PI * 0.55 && aim - angle < Math.PI * 1.45) {
            aim -= angle;
        }

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
