package org.academiadecodigo.bootcamp.gfx;

import org.academiadecodigo.bootcamp.physics2D.Body2D.CircularBody2D;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.bootcamp.wormgame.Projectile;
import org.academiadecodigo.bootcamp.wormgame.ProjectileType;

/**
 * Created by codecadet on 04/06/2018.
 */
public class SgfxProjectileType extends Projectile {

    private ProjectileType projectileType;
    private SgfxCircularBody2D circle;

    public SgfxProjectileType(ProjectileType projectileType, Vector2D position,SgfxViewport viewport) {
        super(projectileType, position);

        this.projectileType = projectileType;
        circle = new SgfxCircularBody2D(projectileType.getMass(),projectileType.getRadius(),position,viewport);
    }
}
