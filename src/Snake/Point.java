package Snake;

import java.awt.*;

/**
 * Created by traitorwtf on 06.03.2017.
 */
public class Point {
    int x, y;
    Color color = MainClass.DEFAULT_COLOR;

    public Point(int x, int y){
        this.setXY(x, y);
    }

    int getX() { return x; }
    int getY() { return y; }

    void paint(Graphics g){
        g.setColor(color);
        g.fillRect(x*MainClass.POINT_RADIUS,y*MainClass.POINT_RADIUS,
                MainClass.POINT_RADIUS, MainClass.POINT_RADIUS);
        g.setColor(Color.GRAY);
        g.drawRect(x*MainClass.POINT_RADIUS,y*MainClass.POINT_RADIUS,
                MainClass.POINT_RADIUS, MainClass.POINT_RADIUS);

    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
