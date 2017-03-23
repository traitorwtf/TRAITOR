package My_Game_Mines;

import javax.swing.*;
import java.awt.*;

/**
 * Created by traitorwtf on 22.03.2017.
 */
public class Canvas extends JPanel{

    public void paint(Graphics g){
        super.paint(g);
        for (int y = 0; y  < Game.FIELD_SIZE; y++){
            for (int x = 0; x < Game.FIELD_SIZE; x++){
                Game.field[x][y].paint(g,x,y);
            }
        }
        if (Game.gameOver){
            g.setColor(Color.red);
            Font font = new Font("Comic Sans MS", Font.BOLD, 32);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(Game.GAME_OVER_MSG,(Game.FIELD_SIZE*Game.BLOCK_SIZE-fm.stringWidth(Game.GAME_OVER_MSG))/2 ,Game.FIELD_SIZE*Game.BLOCK_SIZE/2);
        } else {

            if (Game.youWon) {
                g.setColor(Color.red);
                Font font = new Font("Comic Sans MS", Font.BOLD, 32);
                g.setFont(font);
                FontMetrics fm = g.getFontMetrics();
                g.drawString(Game.YOU_WON_MSG, (Game.FIELD_SIZE * Game.BLOCK_SIZE - fm.stringWidth(Game.YOU_WON_MSG)) / 2, Game.FIELD_SIZE * Game.BLOCK_SIZE / 2);
            }
        }
    }
}
