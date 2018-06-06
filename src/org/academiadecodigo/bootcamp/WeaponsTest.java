package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.*;
import org.academiadecodigo.bootcamp.wormgame.Character;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

import java.awt.*;

/**
 * Created by codecadet on 03/06/2018.
 */
public class WeaponsTest {

    public static void main(String[] args) {



        SgfxViewport testField = new SgfxViewport(640,480,1.0);
        testField.show();

        //Rectangle rectangle = new Rectangle(40,40,100,50);
        //rectangle.draw();


        //Projectile projectile = new Projectile(ProjectileType.ROCKET, new Vector2D(rectangle.getX(), rectangle.getY()));
        //SgfxCharacter character = new SgfxCharacter(new Vector2D(20,20), testField);

        //character.initKeyboard();


        //Weapon weapon = new Weapon(WeaponType.SNIPER);
        //System.out.println("You got a " + weapon.getWeapon());
        //System.out.println("You start with " + weapon.getAmmo() + " bullets.");


        /*weapon.fire();
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());
        weapon.addAmmo(4);
        System.out.println("Ammo updated! Now you have " + weapon.getAmmo() + " bullets.");
        weapon.fire();
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());
        weapon.fire();
        System.out.println("The weapon is firing! BOOOM!");
        System.out.println("Ammo left: " + weapon.getAmmo());
*/
    }

}
