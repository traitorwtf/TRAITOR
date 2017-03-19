package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Created by traitorwtf on 06.03.2017.
 */
public class GameSnake {

    void go(){
        JFrame frame = new JFrame(MainClass.TITLE_OF_PROGRAM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MainClass.POINT_RADIUS*MainClass.FIELD_WIDTH+MainClass.FIELD_DX,
                MainClass.POINT_RADIUS*MainClass.FIELD_HEIGHT+MainClass.FIELD_DY);
        frame.setLocation(MainClass.START_LOCATION, MainClass.START_LOCATION);
        frame.setResizable(false);

        Canvas canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.black);

        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                MainClass.snake.setDirection(e.getKeyCode());
                //System.out.println(e.getKeyCode());
            }
        });
        frame.setVisible(true);

        while (!MainClass.gameOver){
            MainClass.snake.move();
            if (MainClass.food.isEaten()){
                MainClass.food.next();
            }
            canvasPanel.repaint();
            try{
                Thread.sleep(MainClass.SHOW_DELAY);
            } catch (Exception e){
                System.err.println("Interrupted Exception");
                e.printStackTrace();
            }
        }
    }
}
