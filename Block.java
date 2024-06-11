/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Block class represents a rectangular block that can be hit and destroyed by a ball.
 * A block has a color and is defined by its bounding rectangle.
 */
public class Block implements Collidable, Sprite {
    /**
     * The bounding rectangle of this block.
     */
    private Rectangle rec;
    /**
     * The color of this block.
     */
    private Color color;

    /**
     * Creates a new Block object with the specified bounding rectangle and color.
     *
     * @param rec   the bounding rectangle of the block
     * @param color the color of the block
     */

    public Block(Rectangle rec, java.awt.Color color) {

        this.rec = rec;
        this.color = color;
    }

    public Block(Rectangle rec) {
        this.rec = rec;
        this.color = Color.blue;
    }


    /**
     * Returns the color of this block.
     *
     * @return the color of the block
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Determines if the specified point is on the specified line.
     *
     * @param point the point to test
     * @param line  the line to test against
     * @return true if the point is on the line, false otherwise
     */
    public boolean isPointOnLine(Point point, Line line) {
        if (point == null) {
            return false;
        }
        if (((line.start().getX() <= point.getX()) && (point.getX() <= line.end().getX())) && ((line.start().getY() <= point.getY()) && (point.getY() <= line.end().getY()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the bounding rectangle of this block.
     *
     * @return the bounding rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Returns a new velocity for a ball after hitting this block at the specified collision point and with the specified
     * current velocity.
     *
     * @param collisionPoint  the point where the ball collided with the block
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // Update the velocity based on the collision with the block
        // For example, you can reverse the direction of the ball
        return new Velocity(-currentVelocity.getdx(), -currentVelocity.getdy());
    }

    /**
     * Draws this block on the specified surface.
     *
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(), (int) rec.getWidth(), (int) rec.getHeight());

        d.setColor(Color.BLACK);
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(), (int) rec.getWidth(), (int) rec.getHeight());
    }

    @Override
    public void timePassed() {

    }


}
