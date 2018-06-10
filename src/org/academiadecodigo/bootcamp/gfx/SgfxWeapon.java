package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.wormgame.actors.Weapon;
import org.academiadecodigo.bootcamp.wormgame.actors.WeaponType;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SgfxWeapon extends Weapon implements Drawable {

    private Picture picture;
    private SgfxViewport viewport;

    public SgfxWeapon (WeaponType weapon, SgfxViewport viewport ) {

        super(weapon);
        this.viewport = viewport;

        picture = new Picture(10,6, weapon.getPictureSkin());
        picture.draw();

    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void removeWeapon(){

        picture.delete();

    }

    @Override
    public void draw() {
        picture.draw();
    }

    @Override
    public void delete() {
        picture.delete();
    }

    @Override
    public void remove() {
        delete();
    }
    
}
