/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 * <p>
 * <p>
 * The Ball class represents a ball object in a 2D space. It implements the Sprite interface and can be drawn on a DrawSurface.
 * The ball has a center point, a radius, a color, and a velocity vector. It can move, change its velocity and collide with objects.
 * It can be added to a Game as a Sprite.
 * <p>
 * <p>
 * The Ball class represents a ball object in a 2D space. It implements the Sprite interface and can be drawn on a DrawSurface.
 * The ball has a center point, a radius, a color, and a velocity vector. It can move, change its velocity and collide with objects.
 * It can be added to a Game as a Sprite.
 */
/**

 The Ball class represents a ball object in a 2D space. It implements the Sprite interface and can be drawn on a DrawSurface.
 The ball has a center point, a radius, a color, and a velocity vector. It can move, change its velocity and collide with objects.
 It can be added to a Game as a Sprite.
 */

import biuoop.DrawSurface;

public class Ball implements Sprite {
    private Point center;
    private int R;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;

    /**
     * Constructs a ball with a given x and y of its center point, radius, and color.
     * The ball's velocity is set to (0, 0) by default, and its GameEnvironment is set to a new instance of GameEnvironment.
     *
     * @param x the x of the center point of the ball
     * @param y the y of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int R, java.awt.Color color) {
        this.center = new Point(x, y);
        this.R = R;
        this.color = color;
        this.v = new Velocity(0  , 0);
        this.environment = new GameEnvironment();
    }
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.R = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Constructs a ball with a given x and y coordinates of its center point, radius, color, and GameEnvironment.
     * The ball's velocity is set to (0, 0) by default.
     *
     * @param x the x of the center point of the ball
     * @param y the y of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param environment the GameEnvironment of the ball
     */


    public Ball(double x, double y, int R, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.R = R;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) (this.center.getX());
        int y = (int) (this.center.getY());
        int r = this.R;
        surface.setColor(this.color);
        surface.fillCircle(x, y, r);
    }

    /**
     * Returns the x of the center point of the ball.
     *
     * @return the x of the center point of the ball
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Returns the y of the center point of the ball.
     *
     * @return the y of the center point of the ball
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity vector of the ball.
     *
     * @return the velocity vector of the ball
     */
    public Velocity getV() {
        return this.v;
    }

    public int getR() {
        return R;
    }

    public Line calculateTrajectory() {
        Line trajectory = new Line(this.center, new Point(this.center.getX() + this.v.getdx(),
                this.center.getY() + this.v.getdy()));
        return trajectory;
    }

    /**
     * Moves the ball one step according to its velocity  and the collisions with objects.
     * If the ball collides with an object, its velocity  is updated accordingly.
     */
    public void moveOneStep() {
        CollisionInfo info;
        Velocity newVelocity;
        // if the width is too small.
        Line trajectory = calculateTrajectory();
        Point newPosition = this.v.applyToPoint(this.center);

        double newX = newPosition.getX();
        double newY = newPosition.getY();


        if (this.environment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            info = this.environment.getClosestCollision(trajectory);
            newVelocity = info.collisionObject().hit( info.collisionPoint(), this.v);

            if (Math.signum(this.v.getdy()) == Math.signum(newVelocity.getdy())) {
                if (this.v.getdx() > 0) {
                    newX = info.collisionPoint().getX() - this.R * 1.01;
                } else {
                    newX = info.collisionPoint().getX() + this.R * 1.01;
                }
                double ratioX = 0;
//                this.center = new Point(newX, this.center.getY() + ratioX * (info.collisionPoint().getY()
//                        - this.center.getY()));
            } else if (Math.signum(this.v.getdx()) == Math.signum(newVelocity.getdx())) {
                if (this.v.getdy() > 0) {
                    newY = info.collisionPoint().getY() - this.R * 1.01;
                } else {
                    newY = info.collisionPoint().getY() + this.R * 1.01;
                }
                double ratioY = 0;
//                this.center = new Point(this.center.getX() + ratioY * (info.collisionPoint().getX()
//                        - this.center.getX()), newY);

            } else { //padlle
                this.center = new Point(info.collisionPoint().getX(),
                        info.collisionObject().getCollisionRectangle().getUpperLeft().getY() - this.R);

            }
            this.setVelocity(newVelocity.getdx(), newVelocity.getdy());
        }
        if (newX - this.R <= 0 || newX + this.R >= 800) {
            // Reverse the horizontal velocity to make the ball bounce back
            this.v = new Velocity(-this.v.getdx(), this.v.getdy());
        }
        if (newY - this.R <= 0 || newY + this.R >= 600) {
            // Reverse the vertical velocity to make the ball bounce back
            this.v = new Velocity(this.v.getdx(), -this.v.getdy());
        }

        // Update the ball's center position
        this.center = this.v.applyToPoint(this.center);
    }

    public Velocity getVelocity() {
        return this.v;

    }

    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.R;

    }

    /**
     * Sets the velocity of the ball to the given dx and dy values.
     *
     * @param dx the new velocity of the ball along the x-axis.
     * @param dy the new velocity of the ball along the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Sets the velocity of the ball to the given Velocity object.
     *
     * @param v the new velocity of the ball.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }



    public void setVelocity(Velocity velocity) {
        this.v = velocity  ;
    }

}

