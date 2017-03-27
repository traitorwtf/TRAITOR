package My_Game_Xonix;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by traitorwtf on 27.03.2017.
 */
public class XonixCube {
    Random random = new Random();

    int dx, dy;
    int x, y;


    XonixCube(){
        initialize();
        dx = random.nextBoolean() ? 1 : -1;
        dy = random.nextBoolean() ? 1 : -1;

    }
    void initialize(){
        int x0 = ThreadLocalRandom.current().nextInt(0,
                XonixField.groundSize-1);
        int y0 = ThreadLocalRandom.current().nextInt(0,
                XonixGame.FIELD_HEIGHT-1);
        x = x0;
        y = y0;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    void move(){
        if ((x+dx) < 0 || (x+dx) >= XonixGame.FIELD_WIDTH
                || XonixField.field[x+dx][y] == XonixGame.COLOR_WATER) dx = -dx;
        if ((y+dy) < 0 || (y+dy) >= XonixGame.FIELD_HEIGHT
                || XonixField.field[x][y+dy] == XonixGame.COLOR_WATER) dy = -dy;
        x = x+dx;
        y = y+dy;
        if(isCrossedWithXonix(x,y)) XonixGame.gameIsOver = true;
    }

    boolean isCrossedWithXonix(int x, int y){
        return (x == XonixGame.player.getX() && y == XonixGame.player.getY());
    }

    void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.draw3DRect(x*XonixGame.POINT_SIZE, y*XonixGame.POINT_SIZE, XonixGame.POINT_SIZE, XonixGame.POINT_SIZE, true);
    }
}
