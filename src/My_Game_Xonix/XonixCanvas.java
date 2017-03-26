package My_Game_Xonix;

import javax.swing.*;
import java.awt.*;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixCanvas extends JPanel {

    @Override
    public void paint(Graphics g){
        super.paint(g);
        XonixGame.gameField.paint(g);
        XonixGame.player.paint(g);
        XonixGame.ball.paint(g);

        if (XonixGame.gameIsCompletlyOver){
            g.setColor(Color.red);
            Font font = new Font("Comic Sans MS", Font.BOLD, 64);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(XonixGame.GAME_OVER_MSG,
                    (XonixGame.FIELD_WIDTH*XonixGame.POINT_SIZE-fm.stringWidth(XonixGame.GAME_OVER_MSG))/2 ,
                    XonixGame.FIELD_HEIGHT*XonixGame.POINT_SIZE/2);
        }
    }
}
