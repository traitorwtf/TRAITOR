package My_Game_Xonix;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixPlayer {
    int x,y;
    int direction = 0;
    boolean isWater;

    XonixPlayer(){
        initialize();
    }

    void initialize(){
        x = XonixGame.FIELD_WIDTH/2;
        y = 0;
    }

    void move() {
        if (direction == XonixGame.LEFT) {
            x--;
        }
        if (direction == XonixGame.RIGHT) {
            x++;
        }
        if (direction == XonixGame.UP) {
            y--;
        }
        if (direction == XonixGame.DOWN) {
            y++;
        }
        if (x >= XonixGame.FIELD_WIDTH-1) x = XonixGame.FIELD_WIDTH-1;
        if (x < 0) x = 0;
        if (y >= XonixGame.FIELD_HEIGHT-1) y = XonixGame.FIELD_HEIGHT-1;
        if (y < 0) y = 0;

        if(checkTraceCrossing(x,y)) XonixGame.gameIsOver = true;

        creatingTrace(x,y);

    }

    public void setDirection(KeyEvent e){
        int dir = e.getKeyCode();
        if (dir >= 37 && dir<=40){
            this.direction = dir;
        }

    }

    public void creatingTrace(int x, int y){
        if(XonixField.field[x][y] == XonixGame.COLOR_WATER){
            XonixField.field[x][y] = XonixGame.COLOR_TRACE;
            isWater = true;
        }
        if (XonixField.field[x][y] == XonixGame.COLOR_LAND && isWater){
            isWater = false;
            XonixGame.gameField.tryingToFillWater();
        }
    }

    boolean checkTraceCrossing(int x, int y){
        if (XonixField.field[x][y] == XonixGame.COLOR_TRACE) return true;
        else return false;
    }

    void paint(Graphics g){
        if (XonixGame.gameField.getGround(x,y)) {
            g.setColor(Color.BLACK);
        } else{
            g.setColor(Color.WHITE);
        }
        g.fillRect(x*XonixGame.POINT_SIZE, y*XonixGame.POINT_SIZE, XonixGame.POINT_SIZE, XonixGame.POINT_SIZE);
    }
}
