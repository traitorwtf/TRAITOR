package My_Game_Xonix;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixBall {

    Random random = new Random();

    int dx, dy;
    int x, y;


    XonixBall(){
        initialize();
        dx = random.nextBoolean() ? 1 : -1;
        dy = random.nextBoolean() ? 1 : -1;

    }
    void initialize(){
        int x0 = ThreadLocalRandom.current().nextInt(XonixField.groundSize+1,
                XonixGame.FIELD_WIDTH-XonixField.groundSize-1);
        int y0 = ThreadLocalRandom.current().nextInt(XonixField.groundSize+1,
                XonixGame.FIELD_HEIGHT-XonixField.groundSize-1);
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
        if (XonixField.field[x+dx][y] == XonixGame.COLOR_LAND) dx = -dx;
        if (XonixField.field[x][y+dy] == XonixGame.COLOR_LAND) dy = -dy;
        x = x+dx;
        y = y+dy;
        if(new XonixPlayer().checkTraceCrossing(x,y)) XonixGame.gameIsOver = true;
    }

    void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x*XonixGame.POINT_SIZE, y*XonixGame.POINT_SIZE, XonixGame.POINT_SIZE, XonixGame.POINT_SIZE);
    }
}
