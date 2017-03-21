package My_Game_Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by traitorwtf on 21.03.2017.
 */
public class Game {

    private final String GAME_TITLE = "Game Snake";
    static final String GAME_OVER_MSG = "Game Over";

    static final int BLOCK_SIZE = 20; // in pixels
    static final int FIELD_WIDTH = 30; // in blocks
    static final int FIELD_HEIGHT = 20; // in blocks
    final int FIELD_DX = 6;
    final int FIELD_DY = 50;

    static final int START_SNAKE_SIZE = 6;
    static final int START_SNAKE_X = 10;
    static final int START_SNAKE_Y = 10;

    int DELAY = 100;

    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;

    static final int START_DIRECTION = RIGHT;

    final Color DEFAULT_COLOR = Color.black;
    static final Color SNAKE_COLOR = Color.green;
    static final Color FOOD_COLOR = Color.blue;

    static boolean isOver = false;

    public static int foodCount = 0;

    static Snake snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, START_DIRECTION);
    static Food food = new Food();


    public void go() {
        JFrame mainFrame = new JFrame(GAME_TITLE);
        mainFrame.setSize(BLOCK_SIZE*FIELD_WIDTH+FIELD_DX, BLOCK_SIZE*FIELD_HEIGHT+FIELD_DY);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        canvas.setBackground(DEFAULT_COLOR);

        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.setDirection(e);
            }
        });

        mainFrame.getContentPane().add(BorderLayout.CENTER, canvas);
        mainFrame.setVisible(true);

        while(!isOver){
            snake.move();
            canvas.repaint();
            switch (foodCount){
                case 5: DELAY = 85;
                        break;
                case 10: DELAY = 70;
                         break;
                case 15: DELAY = 50;
                         break;
                case 20: DELAY = 40;
                         break;
            }

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
