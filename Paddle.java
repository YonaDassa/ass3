/**
 * @author yona mamane
 * @version 1.0  *
 * @since 07/05/2023
 * <p>
 * <p>
 * The Paddle class represents the player's paddle in the game. It implements both the Sprite and Collidable interfaces,
 * allowing it to be drawn on the screen and interact with other game objects.
 */

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;


import biuoop.GUI;

import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    // keyboard sensor for moving the paddle
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private int paddleSpeed;
    private static final int NUM_REGIONS = 5;
    private double REGION_WIDTH;

    /**
     * Constructs a new Paddle object with the given parameters.
     *
     * @param upperLeft the upper left point of the paddle's rectangle
     * @param width     the width of the paddle's rectangle
     * @param height    the height of the paddle's rectangle
     * @param speed     the speed of the paddle
     * @param keyboard  the keyboard sensor for moving the paddle
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, int paddleSpeed) {
        this.paddleRect = rect;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        REGION_WIDTH = rect.getWidth() / NUM_REGIONS;
    }

    /**
     * Moves the paddle to the left by paddleSpeed units.
     */
    public void moveLeft() {
        paddleRect.setUpperLeft(paddleRect.getUpperLeft().getX() - paddleSpeed, paddleRect.getUpperLeft().getY());
    }

    /**
     * Moves the paddle to the right by paddleSpeed units.
     */
    public void moveRight() {
        paddleRect.setUpperLeft(paddleRect.getUpperLeft().getX() + paddleSpeed, paddleRect.getUpperLeft().getY());

    }

    /**
     * Implements the timePassed method of the Sprite interface. Checks if the left or right keys are pressed and moves
     * the paddle accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Implements the drawOn method of the Sprite interface. Draws the paddle on the given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) paddleRect.getUpperLeft().getX(),
                (int) paddleRect.getUpperLeft().getY(),
                (int) paddleRect.getWidth(),
                (int) paddleRect.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return paddleRect;

    }


    /**
     * Adds the paddle to the this game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Handles the hit of the paddle by a ball.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the hit
     */
@Override
public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
    double region = (collisionPoint.getX() - paddleRect.getUpperLeft().getX()) / REGION_WIDTH;
    double newAngle = 0;
    if (region >= 1 && region <= NUM_REGIONS) {
        if (region == 1) {
            newAngle = 300; // Leftmost region
        } else if (region == 2) {
            newAngle = 330; // Second region
        } else if (region == 4) {
            newAngle = 30; // Fourth region
        } else if (region == NUM_REGIONS) {
            newAngle = 60; // Rightmost region
        }
        return Velocity.fromAngleAndSpeed(newAngle, currentVelocity.getSpeed());
    }
    return currentVelocity;
}
}

