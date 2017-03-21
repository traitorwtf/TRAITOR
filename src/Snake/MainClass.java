package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainClass {

    static final String TITLE_OF_PROGRAM = "Игра Змейка";
    static final String GAME_OVER_MESSAGE = "Game Over";

    static final int POINT_RADIUS = 20; // in pix
    static final int FIELD_WIDTH = 30; // in points
    static final int FIELD_HEIGHT = 20;
    static final int FIELD_DX = 6;
    static final int FIELD_DY = 28;
    static final int START_LOCATION = 200;
    static final int START_SNAKE_SIZE = 6;
    static final int START_SNAKE_X = 10;
    static final int START_SNAKE_Y = 10;
    static final int SHOW_DELAY = 100;
    static final int LEFT = 37;
    static final int UP = 38;
    static final int RIGHT = 39;
    static final int DOWN = 40;
    static final int START_DIRECTION = RIGHT;

    static final Color DEFAULT_COLOR = Color.white;
    static final Color FOOD_COLOR = Color.green;
    static final Color POISON_COLOR = Color.red;

    static Snake snake = new Snake(START_SNAKE_X, START_SNAKE_Y, START_SNAKE_SIZE, START_DIRECTION);
    static Food food = new Food();
    static Random random = new Random();

    static boolean gameOver = false;

    public static void main(String [] args){
        GameSnake game = new GameSnake();
        game.go();
    }
}
