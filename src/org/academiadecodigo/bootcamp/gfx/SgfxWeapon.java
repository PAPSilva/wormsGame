package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Fireable;
import org.academiadecodigo.bootcamp.wormgame.Projectile;
import org.academiadecodigo.bootcamp.wormgame.Weapon;
import org.academiadecodigo.bootcamp.wormgame.WeaponType;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 09/06/2018.
 */
public class SgfxWeapon extends Weapon {

    private Picture picture;
    private SgfxViewport viewport;
    private Text ammoCounter;



    public SgfxWeapon (WeaponType weapon, SgfxViewport viewport ) {

        super(weapon);
        this.viewport = viewport;


        picture = new Picture(10,6, weapon.getPictureSkin());
        picture.draw();
        //ammoCounter = new Text(60,10,"Ammo left: " + weapon.getAmmo());
        //ammoCounter.draw();

    }

    /*public SgfxWeapon (WeaponType weapon, int ammo, SgfxViewport viewport) {
        this(weapon, viewport);
        int deltaAmmo = ammo - weapon.getAmmo();
        addAmmo(deltaAmmo);
    }

    /*public void removeAmmo() {

        ammoCounter.delete();

    }


    @Override
    public Projectile fire(Vector2D position, double aim) {

        System.out.println("HERE");
        Projectile projectile = super.fire(position, aim);
        ammoCounter.setText("" + getAmmo());
        ammoCounter.draw();
        System.out.println(ammoCounter);

        return projectile;

    }*/

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void removeWeapon(){

        picture.delete();

    }


}
