/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 * The SpriteCollection class is responsible for managing a collection of sprites
 * and updating and drawing them.
 */

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection object and initializes an empty list of sprites.
     */

    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notifies all the sprites in the collection that time has passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : this.sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all the sprites in the collection on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the sprites on.
     */

    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
