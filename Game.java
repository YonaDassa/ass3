import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Game {
    public static final double SCREEN_WIDTH = 800;
    public static final double SCREEN_HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    // No-argument constructor
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
    }

    // Constructor with parameters (if needed in future)
    public Game(GameEnvironment environment, SpriteCollection sprite) {
        this.sprites = sprite;
        this.environment = environment;
        this.gui = new GUI("Game", 800, 600);
    }

    public Game(GameEnvironment environment, SpriteCollection sprite, GUI gui) {
        this.sprites = sprite;
        this.environment = environment;
        this.gui = gui; // Store the GUI instance
    }

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void initialize() {
        // Create a new DrawSurface
        DrawSurface background = gui.getDrawSurface();

        // Fill the background with the desired color
        background.setColor(Color.BLUE);
        background.fillRectangle(0, 0, (int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);

        // Set the background as the GUI's drawing surface
        gui.show(background);

        // Create blocks for the edges
        Block topBlock = new Block(new Rectangle(new Point(0, 0), SCREEN_WIDTH, 20, Color.GRAY));
        Block bottomBlock = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT - 20), SCREEN_WIDTH, 20, Color.GRAY));
        Block leftBlock = new Block(new Rectangle(new Point(0, 20), 20, SCREEN_HEIGHT - 40, Color.GRAY));
        Block rightBlock = new Block(new Rectangle(new Point(SCREEN_WIDTH - 20, 20), 20, SCREEN_HEIGHT - 40, Color.GRAY));

        int paddleSpeed = 10; // Example paddle speed
        Paddle paddle = new Paddle(new Rectangle(new Point(350, 575), 100, 10, Color.YELLOW),
                gui.getKeyboardSensor(), paddleSpeed);


        // Add blocks to the game environment
        environment.addCollidable(topBlock);
        environment.addCollidable(bottomBlock);
        environment.addCollidable(leftBlock);
        environment.addCollidable(rightBlock);

        // Add paddle to the game
        paddle.addToGame(this);

        // Add blocks to the sprite collection to draw them
        sprites.addSprite(topBlock);
        sprites.addSprite(bottomBlock);
        sprites.addSprite(leftBlock);
        sprites.addSprite(rightBlock);
    }

    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    // Getter for GameEnvironment
    public GameEnvironment getEnvironment() {
        return environment;
    }

    // Getter for SpriteCollection
    public SpriteCollection getSprites() {
        return sprites;
    }

    public GUI getGui() {
        return this.gui;

    }
}
