/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 */

/**
 * The Point class represents a point, with x and y coordinates.
 */
public class Point {

    /**
     * The x coordinate of the point.
     */
    private double x;

    /**
     * The y coordinate of the point.
     */
    private double y;

    /**
     * Constructs a point with the given x and y coordinates.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of the point.
     *
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y value of the point.
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to calculate the distance to it
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        double answer = Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        return answer;
    }

    /**
     * Compares this point to another point to see if they are equal.
     *
     * @param other the other point to compare to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        } else {
            return false;
        }
    }
}



