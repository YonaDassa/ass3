/**
 * @author yona mamane
 * @version 1.0  *
 * @since 05/06/2023
 */

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Rectangle {
    public Point upperLeft;
    public Point upperRight;
    public Point downLeft;
    public Point downRight;
    public Line LineTop;
    public Line LineBottom;
    public Line LineLeft;
    public Line LineRight;
    public Color color;


    public double width;
    public double height;

    /**
     * Creates a new Rectangle with the specified upper-left corner, width, height, and color.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */

    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(this.upperLeft.getX(), this.upperLeft.getY() + width);
        this.downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getX() + height);
        this.downRight = new Point(this.downLeft.getX() + width, this.upperRight.getY());


        this.LineTop = new Line(this.upperLeft, this.upperRight);
        this.LineBottom = new Line(this.downLeft, this.downRight);
        this.LineLeft = new Line(this.upperLeft, this.downLeft);
        this.LineRight = new Line(this.upperRight, this.downRight);
        this.color = color;

    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */

    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left corner of the rectangle.
     *
     * @return the upper-left corner of the rectangle
     */

    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper-right corner of the rectangle.
     *
     * @return the upper-right corner of the rectangle
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Returns the lower-left corner of the rectangle.
     *
     * @return the lower-left corner of the rectangle
     */
    public Point getDownLeft() {
        return this.downLeft;
    }

    /**
     * Returns the lower-right corner of the rectangle.
     *
     * @return the lower-right corner of the rectangle
     */
    public Point getDownRight() {
        return this.downRight;
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getLineTop() {
        return this.LineTop;
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */

    public Line getLineBottom() {
        return this.LineBottom;
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLineLeft() {
        return this.LineLeft;
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getLineRight() {
        return this.LineRight;
    }

    /**
     * Sets the upper-left corner of the rectangle to the specified point.
     *
     * @param x the x-coordinate of the new upper-left corner
     * @param y the y-coordinate of the new upper-left corner
     * @return the new upper-left corner of the rectangle
     */
    public Point setUpperLeft(double x, double y) {
        return new Point(x, y);
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line.
     * <p>
     * The method checks for intersections with each of the four edges of the rectangle:
     * <p>
     * top edge, right edge, bottom edge, and left edge.
     *
     * @param line the line to check for intersections with
     * @return a list of intersection points between this rectangle and the given line
     */

    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionsArray = new ArrayList<Point>();

        // Check for intersection with top edge
        Line topEdge = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        Point intersection = line.intersectionWith(topEdge);
        if (intersection != null) {
            intersectionsArray.add(intersection);
        }

        // Check for intersection with right edge
        Line rightEdge = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        intersection = line.intersectionWith(rightEdge);
        if (intersection != null) {
            intersectionsArray.add(intersection);
        }

        // Check for intersection with bottom edge
        Line bottomEdge = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        intersection = line.intersectionWith(bottomEdge);
        if (intersection != null) {
            intersectionsArray.add(intersection);
        }

        // Check for intersection with left edge
        Line leftEdge = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        intersection = line.intersectionWith(leftEdge);
        if (intersection != null) {
            intersectionsArray.add(intersection);
        }

        return intersectionsArray;
    }


}