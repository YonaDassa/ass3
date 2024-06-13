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
        Point nextPoint = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPoint);
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);

        if (collision != null) {
            Point collisionPoint = collision.collisionPoint();
            Collidable collidable = collision.collisionObject();

            // Adjust the velocity based on the collision
            this.v = collidable.hit(collisionPoint, this.v);
            this.center = this.v.applyToPoint(this.center);
        } else {
            // Check for collision with walls
            if (this.center.getX() - this.R <= 0 || this.center.getX() + this.R >= 800) {
                this.v = new Velocity(-this.v.getdx(), this.v.getdy());
            }
            if (this.center.getY() - this.R <= 0 || this.center.getY() + this.R >= 600) {
                this.v = new Velocity(this.v.getdx(), -this.v.getdy());
            }

            // Move the ball
            this.center = nextPoint;
        }
    }

    public void addToGame(Game game) {
        game.addSprite(this);
    }
    public Velocity getVelocity() {
        return this.v;

    }



    /**
     * Returns the radius of the ball.
     *
     * @return the size of the ball.
     */


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

