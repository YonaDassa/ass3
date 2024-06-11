/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 */

/**
 * CollisionInfo class represents collision between a ball and an object.
 * <p>
 * It has the collidable object that was hit and the point of collision.
 */
public class CollisionInfo {
    /**
     * The collidable object that was hit in the collision.
     */
    private Collidable collisionObject;
    /**
     * The point of collision between the ball and the collidable object.
     */
    private Point collisionPoint;
    /**
     * The line of collision between the ball and the collidable object.
     */
    private Line trajectory;


    /**
     * Creates a new CollisionInfo object.
     *
     * @param collisionObject the collidable object that was hit
     * @param collisionPoint  the point of collision between the ball and the collidable object
     */

    public CollisionInfo(Line trajectory, Collidable collisionObject, Point collisionPoint) {
        this.trajectory = trajectory;
        this.collisionObject = collisionObject;
        this.collisionPoint = trajectory.closestIntersectionToStartOfLine(this.collisionObject.getCollisionRectangle());
    }


    /**
     * Returns the point of collision between the ball and the object.
     *
     * @return the point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object that was hit in the collision.
     *
     * @return the collidable object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }


}