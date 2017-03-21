package My_Game_Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 21.03.2017.
 */
public class Snake{
    ArrayList<Block> snake = new ArrayList<Block>();
    int direction;

    public Snake(int x, int y, int length, int direction){
        for (int i = 0; i < length; i++) {
            Block block = new Block(x-i, y);
            snake.add(block);
        }
        this.direction = direction;
    }

    void move(){
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        if (direction == Game.LEFT) {x--;}
        if (direction == Game.RIGHT) {x++;}
        if (direction == Game.UP) {y--;}
        if (direction == Game.DOWN) {y++;}
        if (x > Game.FIELD_WIDTH-1) x=0;
        if (x < 0) x = Game.FIELD_WIDTH;
        if (y > Game.FIELD_HEIGHT) y = 0;
        if (y < 0) y = Game.FIELD_HEIGHT;

        if (snakeAteHerself(x,y)){ Game.isOver = true;        }

        if (snakeFoundFood(Game.food)){
            Game.foodCount++;
            Game.food.next();
        } else {
            snake.remove(snake.size() - 1);
        }
        snake.add(0,new Block(x,y));
    }

    public void setDirection(KeyEvent e){
        int dir = e.getKeyCode();
        if (dir >= 37 && dir<=40){
            if (this.direction == Game.RIGHT && dir == Game.LEFT) this.direction = Game.RIGHT;
            else if(this.direction == Game.LEFT && dir == Game.RIGHT) this.direction = Game.LEFT;
            else if(this.direction == Game.UP && dir == Game.DOWN) this.direction = Game.UP;
            else if(this.direction == Game.DOWN && dir == Game.UP) this.direction = Game.DOWN;
            else this.direction = dir;
        }

    }

    boolean snakeFoundFood(Food food){
        return (snake.get(0).getX() == food.getX() && snake.get(0).getY() == food.getY());

    }

    boolean snakeAteHerself(int x, int y){
        for (int i = 1; i < snake.size();i++){
            if (x == snake.get(i).getX()
                    && y == snake.get(i).getY())
                return true;
        }
        return false;
    }

    public void paint(Graphics g){
        for (Block block : snake){
            block.paint(g);
        }
    }
}
