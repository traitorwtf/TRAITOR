package Snake;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 06.03.2017.
 */
public class Snake {
    static ArrayList<Point> snake = new ArrayList<Point>();
    int direction;

    public Snake(int x, int y, int length, int direction){
        for (int i = 0; i < length; i++){
            Point point = new Point(x-i,y);
            snake.add(point);
        }
        this.direction = direction;
    }

    boolean ifFood(Point food){
        return ((snake.get(0).getX() == food.getX()) && (snake.get(0).getY() == food.getY()));
    }

    boolean isInsideSnake(int x, int y) {
        for (Point point : snake) {
            if ((point.getX() == x) && (point.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    void move(){

        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        if (direction == MainClass.LEFT) {x--;}
        if (direction == MainClass.RIGHT) {x++;}
        if (direction == MainClass.UP) {y--;}
        if (direction == MainClass.DOWN) {y++;}
        if (x > MainClass.FIELD_WIDTH - 1) { x = 0; }
        if (x < 0) { x = MainClass.FIELD_WIDTH - 1; }
        if (y > MainClass.FIELD_HEIGHT - 1) { y = 0; }
        if (y < 0) { y = MainClass.FIELD_HEIGHT - 1; }

        //MainClass.gameOver = isInsideSnake(x,y);
        if (isInsideSnake(x,y)) MainClass.gameOver = true;

        snake.add(0, new Point(x,y));

        if (ifFood(MainClass.food)){
            MainClass.food.eat();
        } else{
            snake.remove(snake.size()-1);
        }


    }

    void setDirection(int direction){
        if (direction >= MainClass.LEFT && direction <= MainClass.DOWN){
            if (Math.abs(this.direction - direction) != 2) {
                this.direction = direction;
            }
        }
    }

    public void paint(Graphics g){
        for (Point point : snake){
            point.paint(g);
        }
    }
}
