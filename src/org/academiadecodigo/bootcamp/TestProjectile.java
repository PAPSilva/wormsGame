package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.gfx.SgfxProjectile;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.ProjectileType;

/**
 * Created by codecadet on 04/06/2018.
 */
public class TestProjectile {
    public static void main(String[] args) {
        new SgfxProjectile(ProjectileType.ROCKET,new Vector2D(400,400),new SgfxViewport(640,480,1));
        new SgfxProjectile(ProjectileType.BULLET,new Vector2D(100,100),new SgfxViewport(640,480,1));
    }
}