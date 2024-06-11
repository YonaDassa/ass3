/**
 * @author yona mamane
 * @version 1.0  *
 * @since 23/04/2023
 * <p>
 * This class represents a line using the Point class.
 * <p>
 * This class represents a line using the Point class.
 * <p>
 * This class represents a line using the Point class.
 * <p>
 * This class represents a line using the Point class.
 */

/**
 * This class represents a line using the Point class.
 */

import java.util.List;

public class Line {
    private Point start; // The starting point of the line
    private Point end; // The ending point of the line

    /**
     * Constructs a new line with the starting and ending given points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * Constructs a new line with specific x,y for the starting and ending points.
     *
     * @param x1 the x of the starting point
     * @param y1 the y of the starting point
     * @param x2 the x of the ending point
     * @param y2 the y of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line using the mathematical distance function
     *
     * @param x1 the x of the starting point
     * @param y1 the y of the starting point
     * @param x2 the x of the ending point
     * @param y2 the y of the ending point
     * @return the length of the line
     */
    public double length(double x1, double y1, double x2, double y2) {
        return Math.abs(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y1, 2)));
    }

    /**
     * Returns the middle point of the line.
     *
     * @param x1 the x of the starting point
     * @param y1 the y of the starting point
     * @param x2 the x of the ending point
     * @param y2 the y of the ending point
     * @return the middle point of the line
     */
    public Point middle(double x1, double y1, double x2, double y2) {
        double x = ((x1 + x2) / 2);
        double y = ((y1 + y2) / 2);
        Point p = new Point(x, y);
        return p;
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise, using the mathematical function: y=mx+b.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */


    public boolean isIntersecting(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            return true;
        }
        return false;

    }


    /**
     * Returns the intersection point of this line with another line using the function above
     *
     * @param other the other line to find the intersection point with
     * @return the intersection point of the two lines, or null if they don't intersect
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        double x = x1 + ua * (x2 - x1);
        double y = y1 + ua * (y2 - y1);
        //  if (ua >= 0 && ua <= 1) {
        //     return new Point(x, y);
        // }
        if (isIntersecting(other) == true) {
            return new Point(x, y);

        }
        return null;

    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other the other line to check for equality
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((other.start().equals(this.start) && other.end().equals(this.end)) || (other.start().equals(this.end) && other.start().equals(this.end))) {
            return true;
        } else {
            return false;
        }
    }

    /**

     Returns the closest intersection point of this line with a given rectangle to the start point of the line.

     If there are no intersections, null is returned.

     @param rect the rectangle to check for intersection with this line

     @return the closest intersection point of this line with the rectangle to the start point of this line, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);

        if (intersections.isEmpty()) {
            return null;
        } else if (intersections.size() == 1) {
            return intersections.get(0);
        } else {
            Point closest = intersections.get(0);
            double minDistance = closest.distance(start());

            for (int i = 1; i < intersections.size(); i++) {
                Point intersection = intersections.get(i);
                double distance = intersection.distance(start());
                if (distance < minDistance) {
                    closest = intersection;
                    minDistance = distance;
                }
            }

            return closest;
        }
    }

    public boolean isInLine(Point p) {
        double x1 = start.getX();
        double y1 =  start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double xp = p.getX();
        double yp = p.getY();

        double m1 = (xp - x1) == 0 ? Double.POSITIVE_INFINITY : (yp - y1) / (double) (xp - x1);
        double m2 = (xp - x2) == 0 ? Double.POSITIVE_INFINITY : (yp - y2) / (double) (xp - x2);

        if (Double.compare(m1, m2) == 0) {
            return true;
        }

        return false;
    }

}
