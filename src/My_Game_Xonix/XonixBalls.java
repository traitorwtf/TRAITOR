package My_Game_Xonix;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixBalls {
    static ArrayList<XonixBall> Balls = new ArrayList<>();

    XonixBalls(){
        Balls.add(new XonixBall());
    }

    void initialize(){
        for(XonixBall ball : Balls){
            ball.initialize();
        }
    }

    void move(){
        for(XonixBall ball : Balls){
            ball.move();
        }
    }

    void paint(Graphics g){
        for(XonixBall ball : Balls){
        ball.paint(g);
        }
    }
}
