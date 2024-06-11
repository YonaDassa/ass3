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
    /**
     * Creates a GUI and adds Balls and Blocks to the GameEnvironment, then displays the objects and moves the Balls.
     * @param args arguments for main method
     */


    public static void main(String[] args) {
        GUI gui = new GUI("Balls", 600, 800);

        GameEnvironment environment = new GameEnvironment();
        Block[] arrBlock = new Block[7];
        arrBlock[0] = new Block(new Rectangle(new Point(0, 0), 600, 20, Color.BLACK));
        arrBlock[1] = new Block(new Rectangle(new Point(0, 20), 20, 760, java.awt.Color.BLACK));
        arrBlock[2] = new Block(new Rectangle(new Point(0, 780), 600, 20, java.awt.Color.BLACK));
        arrBlock[3] = new Block(new Rectangle(new Point(580, 10), 20, 760, java.awt.Color.BLACK));
        double x = 300;
        double y = 400;
        for (int i = 4; i < 7; i++) {
            arrBlock[i] = new Block(new Rectangle(new Point(x, y), 40, 30, Color.BLUE));
            y += 50;

        }
        for (int i = 0; i < 7; i++) {
            environment.addCollidable(arrBlock[i]);
        }

        Ball ball = new Ball(400, 240, 10, java.awt.Color.RED, environment);
        Ball ball2 = new Ball(200, 400, 10, Color.BLUE, environment);
        ball.setVelocity(new Velocity(4, 4));
        ball2.setVelocity(new Velocity(4, 4));


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12; j++) {
                Point newPoint = new Point(25, 75);
                Rectangle blockRect = new Rectangle(newPoint, 50, 20, BLOCK_COLORS[2]);
                Block block = new Block(blockRect, BLOCK_COLORS[i % BLOCK_COLORS.length]);

            }
        }

        Sleeper sleeper = new Sleeper();
        while (true) {
            //ball.moveOneStep();
            //ball2.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            ball2.drawOn(d);
            for (int i = 0; i < 7; i++) {
                arrBlock[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
