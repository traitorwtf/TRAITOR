package My_Game_Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by traitorwtf on 21.03.2017.
 */
public class Canvas extends JPanel {

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Game.snake.paint(g);
        Game.food.paint(g);

        if (Game.isOver){
            g.setColor(Color.red);
            Font font = new Font("Comic Sans MS", Font.BOLD, 64);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(Game.GAME_OVER_MSG,(Game.FIELD_WIDTH*Game.BLOCK_SIZE-fm.stringWidth(Game.GAME_OVER_MSG))/2 ,Game.FIELD_HEIGHT*Game.BLOCK_SIZE/2);
        }
    }
}
