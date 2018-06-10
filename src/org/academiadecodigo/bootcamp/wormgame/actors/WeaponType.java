package org.academiadecodigo.bootcamp.wormgame.actors;

import org.academiadecodigo.bootcamp.wormgame.actors.ProjectileType;

/**
 * Created by codecadet on 03/06/2018.
 */
public enum WeaponType {

    BAZOOKA(ProjectileType.ROCKET, 100.0, 40.0, 30000, "resources/bazooka.png"),
    SNIPER(ProjectileType.BULLET, 200.0, 40.0, 10000, "resources/sniper.png");

    private int ammo;
    private double shotSpeed;
    private ProjectileType bullet;
    private double barrelLength;
    private String pictureSkin;

    WeaponType(ProjectileType bullet, double shotSpeed, double barrelLength, int ammo, String pictureSkin) {

        this.pictureSkin = pictureSkin;
        this.ammo = ammo;
        this.shotSpeed = shotSpeed;
        this.bullet = bullet;
        this.barrelLength = barrelLength;

    }

    public int getAmmo() {

        return ammo;

    }

    public ProjectileType getBullet() {

        return bullet;

    }

    public double getShotSpeed() {

        return shotSpeed;

    }

    public double getBarrelLength() {

        return barrelLength;

    }

    public String getPictureSkin() {

        return pictureSkin;

    }

}
