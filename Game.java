import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

public class Game {
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

    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    public void initialize() {
        Point center = new Point(400, 300);
        Ball ball = new Ball(400, 550, 5, Color.blue, environment);
        ball.setVelocity(5, 5);
        Ball ball2 = new Ball(400, 550, 5, Color.RED, environment);
        ball2.setVelocity(6, 6);

        Block[] blocks = new Block[10];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(new Rectangle(new Point(50 + i * 70, 200), 60, 30, Color.GREEN));
            blocks[i].addToGame(this);
        }

        Paddle paddle = new Paddle(new Rectangle(new Point(350, 575), 100, 10, Color.GREEN), gui.getKeyboardSensor(), 5);
        paddle.addToGame(this);
        ball.addToGame(this);
        ball2.addToGame(this);

        Block[] arrBlockSides = new Block[4];
        arrBlockSides[0] = new Block(new Rectangle(new Point(0, 0), 600, 20, Color.gray)); // top
        arrBlockSides[1] = new Block(new Rectangle(new Point(0, 20 + 2), 20, 600, Color.gray)); // side
        arrBlockSides[2] = new Block(new Rectangle(new Point(20 + 2, 600 - 20), 800 - 2 * 50 - 2, 20, Color.gray));
        arrBlockSides[3] = new Block(new Rectangle(new Point(800 - 20 + 2, 20 + 2), 20, 600 - 20, Color.gray));
        for (int i = 0; i < 4; i++) {
            arrBlockSides[i].addToGame(this);
        }
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

    public static void main(String[] args) {
        System.out.println("Starting game initialization...");
        Game game = new Game();
        game.initialize();
        System.out.println("Game initialized successfully.");
        game.run();
    }
}
