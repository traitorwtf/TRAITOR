package My_Game_Xonix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixGame {

    final String GAME_TITLE = "Xonix the Game";
    static final String GAME_OVER_MSG = "Game Over";

    static final int POINT_SIZE = 10;
    static final int FIELD_WIDTH = 640/POINT_SIZE;
    static final int FIELD_HEIGHT = 480/POINT_SIZE;
    final int FIELD_DX = 6;
    final int FIELD_DY = 28;
    static final int COLOR_WATER = 0;
    static final int COLOR_LAND = 0x00a8a8;
    static final int COLOR_TRACE = 0x901290;
    static final int TEMP_COLOR = 1;
    static int totalLifeCount = 3;

    static final int LEFT = 37; // key codes
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;

    final int GAME_DELAY = 60;
    static final int PERCENT_OF_WATER_CAPTURE = 75;

    final String FORMAT_STRING = "%20s %d %20s %20s %d";
    final Font font = new Font("", Font.BOLD, 21);

    static XonixField gameField = new XonixField();
    static XonixPlayer player = new XonixPlayer();
    //static XonixBall ball = new XonixBall();
    static XonixBalls balls = new XonixBalls();

    static boolean gameIsOver;
    static boolean youWon;
    static boolean gameIsCompletlyOver;


    JFrame field;
    XonixCanvas canvas;
    JLabel board;

    public void go() {
        field = new JFrame();
        field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        field.setResizable(false);
        field.setSize(FIELD_WIDTH*POINT_SIZE+FIELD_DX,FIELD_HEIGHT*POINT_SIZE+FIELD_DY+29);
        field.setLocationRelativeTo(null);

        board = new JLabel();
        board.setFont(font);


        canvas = new XonixCanvas();
        canvas.setBackground(Color.darkGray);

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.setDirection(e);
            }
        });

        field.getContentPane().add(BorderLayout.CENTER, canvas);
        field.getContentPane().add(BorderLayout.SOUTH, board);
        field.setVisible(true);

        gameLoop();
    }

    void gameLoop(){
        while(!gameIsCompletlyOver){
            player.move();
            balls.move();
            try {
                Thread.sleep(GAME_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (youWon){
                gameField.initialize();
                player.initialize();
                new XonixBalls();
                XonixField.percentOfFilledWater = 0;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                youWon = false;
            }
            if (gameIsOver){
                gameField.initialize();
                player.initialize();
                balls.initialize();
                totalLifeCount--;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameIsOver = false;
            }
            if (totalLifeCount == 0) gameIsCompletlyOver = true;
            board.setText(String.format(FORMAT_STRING, "Life:  ", totalLifeCount,"              ", "Filled Water Area: ", XonixField.percentOfFilledWater));

            canvas.repaint();
        }
    }
}
