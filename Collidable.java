/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 */


/**
 * The Collidable interface represents objects that can collide with other objects in the game.
 * <p>
 * It includes methods for receiving the collision rectangle and calculating the new velocity
 * <p>
 * of an object after the collidable
 */

public interface Collidable {
    /**
     * Returns the collision rectangle of a object.
     *
     * @return the collision rectangle of this object
     */

    Rectangle getCollisionRectangle();

    /**
     * Calculates and returns the new velocity of an object after a collision with this object and
     * If the object does not collide with this object, its current velocity is returned.
     *
     * @param collisionPoint  the point of collision with this object
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity of the colliding object after collision with this object, or its current velocity if no collision occurs
     */

    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
