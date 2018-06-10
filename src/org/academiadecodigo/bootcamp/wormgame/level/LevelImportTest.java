package org.academiadecodigo.bootcamp.wormgame.level;

import org.academiadecodigo.bootcamp.gfx.SgfxCharacter;
import org.academiadecodigo.bootcamp.gfx.SgfxRectangularBody2D;
import org.academiadecodigo.bootcamp.gfx.SgfxViewport;
import org.academiadecodigo.bootcamp.physics2D.Body2D.RectangularBody2D;
import org.academiadecodigo.bootcamp.physics2D.Body2DSystem;
import org.academiadecodigo.bootcamp.physics2D.collidable.Body2DCollider;
import org.academiadecodigo.bootcamp.physics2D.collidable.Collider;
import org.academiadecodigo.bootcamp.physics2D.utils.Vector2D;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.List;

public class LevelImportTest {

    public static void main(String[] args) {

        LevelImportTest test = new LevelImportTest();

    }

    private LevelImportTest() {

        SgfxViewport viewport = new SgfxViewport(1200, 800, 1);
        Collider collider = new Body2DCollider(1.0E-8);
        Body2DSystem system = new Body2DSystem(new Vector2D(0.0,0.0), collider);

        LevelType level = LevelType.LEVEL_RUIN;

        // Add image
        Picture picture = level.image();
        picture.draw();

        // Add obstacles
        List<RectangularBody2D> obstacles = level.obstacles();

        Iterator<RectangularBody2D> it = obstacles.iterator();
        while (it.hasNext()) {

            RectangularBody2D body = it.next();
            Vector2D position = viewport.fromViewportCoordinates(body.getPosition());

            SgfxRectangularBody2D sgfxBody = new SgfxRectangularBody2D(body.getMass(), body.getWidth(), body.getHeight(), position, viewport);
            sgfxBody.rotate(body.getOrientation());
            sgfxBody.draw();

            system.add(sgfxBody); // TODO this is to see. Add it.next() directly.
        }

        // Add characters
        List<Vector2D> spawnSites = level.spawnSites();

        Iterator<Vector2D> spawnIt = spawnSites.iterator();
        while (spawnIt.hasNext()) {

            Vector2D position = spawnIt.next();
            System.out.println(position);
            SgfxCharacter character = new SgfxCharacter(20, 20, position,100, 1, "soldier.png", viewport);
            system.add(character);

        }


    }

}
