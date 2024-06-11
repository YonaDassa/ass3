import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

public class Ass3Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public static final int BALL_RADIUS = 7;

    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        GameEnvironment environment = new GameEnvironment();
        SpriteCollection sprites = new SpriteCollection();
        Game game = new Game(environment, sprites);

        addPaddle(game, gui);
        addBalls(game, gui);
        addBlocks(game);

        game.initialize();
        game.run();
    }

    private static void addPaddle(Game game, GUI gui) {
        int paddleWidth = 100;
        int paddleHeight = 20;
        Point paddleStartPoint = new Point(350, 580);  // Example starting point for the paddle
        int paddleSpeed = 10;  // Example paddle speed

        Paddle paddle = new Paddle(new Rectangle(paddleStartPoint, paddleWidth, paddleHeight, Color.GREEN), gui.getKeyboardSensor(), paddleSpeed);
        game.addSprite(paddle);
    }



    private static void addBlocks(Game game) {
        int topMargin = 100; // Adjust the top margin for block placement
        int blockHeight = 20;
        int blockWidth = 70; // Adjust block width to create space between blocks
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {  // 10 blocks per row
                Point upperLeft = new Point(j * blockWidth + 50, topMargin + i * blockHeight);
                Rectangle rect = new Rectangle(upperLeft, blockWidth, blockHeight, colors[i % colors.length]);
                Block block = new Block(rect, colors[i % colors.length]);
                game.addSprite(block);
                game.addCollidable(block);
            }
        }
    }

    private static void addBalls(Game game, GUI gui) {
        // Define the initial position and velocity of the balls
        Point center1 = new Point(400, 580); // First ball position
        Point center2 = new Point(400, 560); // Second ball position
        int radius = 7; // Ball radius
        Velocity velocity1 = Velocity.fromAngleAndSpeed(45, 5); // First ball velocity
        Velocity velocity2 = Velocity.fromAngleAndSpeed(135, 5); // Second ball velocity
        Color ballColor = Color.WHITE; // Ball color

        // Create the balls and add them to the game
        Ball ball1 = new Ball(center1, radius, ballColor, game.getEnvironment());
        ball1.setVelocity(velocity1);
        game.addSprite(ball1);

        Ball ball2 = new Ball(center2, radius, ballColor, game.getEnvironment());
        ball2.setVelocity(velocity2);
        game.addSprite(ball2);
    }



}
