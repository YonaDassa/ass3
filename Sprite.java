/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 * The Sprite interface represents an object that can be drawn on a DrawSurface and updated over time.
 * Any object that implements this interface can be added to a SpriteCollection and drawn on the screen.
 */

import biuoop.DrawSurface;

public interface Sprite {
    /**
     * Draw the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     * This method is used for updating the state of the sprite over time.
     */

    void timePassed();
}