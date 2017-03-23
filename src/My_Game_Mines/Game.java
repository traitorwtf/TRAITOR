package My_Game_Mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by traitorwtf on 22.03.2017.
 */
public class Game {

    final String GAME_TITLE = "Игра Сапёр";
    static final String GAME_OVER_MSG = "Game Over";
    static final String YOU_WON_MSG = "You Won!";

    static final int BLOCK_SIZE = 25; // in pix
    static final int FIELD_SIZE = 9; // in blocks (for x and y)
    static final int FIELD_DX = 7;
    static final int FIELD_DY = 30;
    static final int MOUSE_BUTTON_LEFT = 1;
    static final int MOUSE_BUTTON_RIGHT = 3;
    static final int NUMBER_OF_MINES = 10;

    static boolean gameOver = false;
    static boolean youWon = false;

    static Cell[][] field = new Cell[FIELD_SIZE][FIELD_SIZE];

    int neighbourBombs = 0;


    JFrame mainFrame;
    Canvas canvas;
    Random random = new Random();

    void go(){
        mainFrame = new JFrame(GAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(BLOCK_SIZE*FIELD_SIZE+FIELD_DX, BLOCK_SIZE*FIELD_SIZE+FIELD_DY);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setBackground(Color.darkGray);

        initializeField();

        mainFrame.addMouseListener(new clickButtonListener());

        mainFrame.getContentPane().add(BorderLayout.CENTER, canvas);
        mainFrame.setVisible(true);
    }

    void initializeField(){
        int minesCount = 0;

        // creating field
        for (int y = 0; y < FIELD_SIZE; y++){
            for(int x = 0; x < FIELD_SIZE; x++){
                field[x][y] = new Cell();
            }
        }

        // creating mines
        int x = -1; int y = -1;
        while(minesCount < NUMBER_OF_MINES){
            do{
                x = random.nextInt(FIELD_SIZE);
                y = random.nextInt(FIELD_SIZE);
            } while (field[x][y].mine());
            minesCount++;
        }
    }

    int countNeighbours(int x, int y){
        int count = 0;
        for (int i = y-1; i <= y+1;i++){
            for (int j = x-1; j <= x+1; j++){
                if(i>=0 && i<=FIELD_SIZE-1 && j>=0 && j<=FIELD_SIZE-1){
                    if (field[j][i].isAlreadyMined()) count++;
                }
            }
        }
        return count;
    }

    //Creating recursion for opening empty cells
    void openCells(int x, int y) {
        if (x < 0 || x > FIELD_SIZE - 1 || y < 0 || y > FIELD_SIZE - 1) return;
        if (field[x][y].isAlreadyOpened()) return;
        field[x][y].openCell();
        if (countNeighbours(x,y) > 0 || field[x][y].isAlreadyMined()) return;
        for (int dx = -1; dx < 2; dx++)
            for (int dy = -1; dy < 2; dy++) openCells(x + dx, y + dy);
    }

    void checkWinCondition(){
        int winningCount = 0;
        for (int y = 0; y < FIELD_SIZE; y++){
            for(int x = 0; x < FIELD_SIZE; x++){
                if (field[x][y].isFlagged && field[x][y].isMined) winningCount++;
            }
        }
        if (winningCount == NUMBER_OF_MINES) youWon = true;
    }

    private class clickButtonListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseClicked(e);
            int x = e.getX()/BLOCK_SIZE;
            int y = (e.getY()/BLOCK_SIZE)-1; /// ??? Почему так???
            //System.out.println(x + " "+ y);
            if (gameOver){ x = -10; y = -10;}
            if (youWon){ x = -10; y = -10;}

            try {
                if (e.getButton() == MOUSE_BUTTON_LEFT) {
                    //field[x][y].openCell();
                    if (!field[x][y].isAlreadyFlagged()) {
                        if (field[x][y].isAlreadyMined()) {
                            gameOver = true;
                        }
                        openCells(x, y);
                        neighbourBombs = countNeighbours(x, y);
                        if (Cell.openCellsCount == FIELD_SIZE * FIELD_SIZE - NUMBER_OF_MINES) {
                            youWon = true;
                        }
                    }
                }

                if (e.getButton() == MOUSE_BUTTON_RIGHT) {
                    if (!field[x][y].isAlreadyOpened()){
                        field[x][y].setFlag();
                        checkWinCondition();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("Игра завершена!");
            }
            canvas.repaint();
        }
    }
}
