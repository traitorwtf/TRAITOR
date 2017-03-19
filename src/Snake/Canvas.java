package Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by traitorwtf on 06.03.2017.
 */
public class Canvas extends JPanel {

    @Override
    public void paint(Graphics g){
        super.paint(g);
        MainClass.snake.paint(g);
        MainClass.food.paint(g);

        if (MainClass.gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 64));
            FontMetrics fm = g.getFontMetrics();
            g.drawString(MainClass.GAME_OVER_MESSAGE,
                    (MainClass.FIELD_WIDTH * MainClass.POINT_RADIUS +
                            MainClass.FIELD_DX - fm.stringWidth(MainClass.GAME_OVER_MESSAGE))/2,
                    (MainClass.FIELD_HEIGHT * MainClass.POINT_RADIUS + MainClass.FIELD_DY)/2);
        }


    }
}
