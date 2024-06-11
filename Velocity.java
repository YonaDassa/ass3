/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 */

/**
 * The Velocity class reflects the change in position on the x and the y using ball class,
 * <p>
 * and the angle and the speed of the ball movement.
 */
public class Velocity {
    public static final double TO_RAD = Math.PI / 180;
    private double dx;
    private double dy;


    /**
     * Constructs a velocity with the given dx and dy values.
     *
     * @param dx the change in position on the x.
     * @param dy the change in position on the y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }




    /**
     * Constructs a velocity with the given angle and speed values.
     *
     * @param angle the angle of the movement in degrees.
     * @param speed the speed of the movement.
     * @return the Velocity with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (int) speed * (Math.cos(angle * TO_RAD));
        double dy = (int) speed * (Math.sin(angle * TO_RAD));
        return new Velocity(dx, dy);
    }

    /**
     * Returns the value of the dx.
     *
     * @return the value of the dx.
     */
    public double getdx() {
        return this.dx;
    }

    /**
     * Returns the value of the dy.
     *
     * @return the value of the dy.
     */
    public double getdy() {
        return this.dy;
    }

    /**
     * Takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the point to apply the velocity on.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        Point new1 = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return new1;
    }
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }



}