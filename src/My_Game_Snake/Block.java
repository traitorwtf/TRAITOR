package My_Game_Snake;

import java.awt.*;

/**
 * Created by traitorwtf on 21.03.2017.
 */
public class Block {
    int x,y;
    Color color = Game.SNAKE_COLOR;

    public Block(int x, int y){
        this.setXY(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x*Game.BLOCK_SIZE,y*Game.BLOCK_SIZE,Game.BLOCK_SIZE, Game.BLOCK_SIZE);
        g.setColor(Color.GRAY);
        g.drawRect(x*Game.BLOCK_SIZE,y*Game.BLOCK_SIZE,Game.BLOCK_SIZE, Game.BLOCK_SIZE);
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
