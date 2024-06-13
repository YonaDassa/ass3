/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 * <p>
 * <p>
 * The GameEnvironment class represents a collection of Collidable objects that may be collided with by other objects.
 */

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


import java.awt.Color;

public class GameEnvironment {
    // list of collidable objects in the environment

    private List<Collidable> collidables;
    // array of colors for blocks

    public static final Color[] BLOCK_COLORS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

    /**
     * Constructor for GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * Adds a Collidable object to the GameEnvironment.
     * @param c the Collidable object to be added
     */

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Finds the closest collision between a given Line and the Collidable objects in the GameEnvironment.
     * @param trajectory the Line to check for collisions
     * @return a CollisionInfo object representing the closest collision, or null if there is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable theNextCollision = null;
        Point point = null;
        double minDistance = Double.MAX_VALUE;
        double minDistanceTemp = Double.MAX_VALUE;

        for (Collidable c : this.collidables) {
            point = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (point == null) {
                continue;
            }
            if (trajectory.start().distance(point) <= minDistance) {
//   closetToTheBall= trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                minDistanceTemp = minDistance;
                minDistance = trajectory.start().distance(point);
                theNextCollision = c;
            }
        }
        if ((minDistance == Double.MAX_VALUE)) {
            return null;
        } else {
            return new CollisionInfo(trajectory, theNextCollision, point);
        }


    }

}
