package My_Game_Mines;

import java.awt.*;

/**
 * Created by traitorwtf on 22.03.2017.
 */
public class Cell {
    private int x,y;
    static int openCellsCount;
    boolean isOpened;
    boolean isMined;
    boolean isFlagged;

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

    void paint(Graphics g, int x, int y){
        this.x = x;
        this.y = y;
        g.setColor(Color.lightGray);
        g.drawRect(x*Game.BLOCK_SIZE,y*Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE);

        if (!isFlagged) {
            if (isOpened) {
                g.setColor(Color.lightGray);
                g.fill3DRect(x * Game.BLOCK_SIZE, y * Game.BLOCK_SIZE, Game.BLOCK_SIZE, Game.BLOCK_SIZE, false);
                if (isMined) {
                    g.setColor(Color.red);
                    g.fillOval(x * Game.BLOCK_SIZE + 4, y * Game.BLOCK_SIZE + 4, Game.BLOCK_SIZE - 8, Game.BLOCK_SIZE - 8);
                } else {
                    int GG = new Game().countNeighbours(x, y);
                    if (GG > 0) {
                        switch (GG) {
                            case 1:
                                g.setColor(Color.green);
                                break;
                            case 2:
                                g.setColor(Color.blue);
                                break;
                            case 3:
                                g.setColor(Color.magenta);
                                break;
                            default:
                                g.setColor(Color.yellow);
                                break;
                        }
                        Font font = new Font("Comic Sans MS", Font.BOLD, 16);
                        String neighbours = String.valueOf(GG);
                        g.setFont(font);
                        g.drawString(neighbours, x * Game.BLOCK_SIZE + 8, y * Game.BLOCK_SIZE + 18);
                    }
                }
            }
        } else{
            Font font = new Font("Comic Sans MS", Font.BOLD, 16);
            g.setColor(Color.red);
            g.setFont(font);
            g.drawString("f", x * Game.BLOCK_SIZE + 8, y * Game.BLOCK_SIZE + 18);
        }
    }

    void openCell(){
        if (isOpened){
            return;
        }
        openCellsCount++;
        isOpened = true;
    }

    boolean mine() {
        if (isMined) return true;
        isMined = true;
        return false;
    }

    void setFlag(){
        if (isFlagged){
            isFlagged = false;
            return;
        }
        isFlagged = true;
    }


    boolean isAlreadyMined(){
        return (isMined);
    }

    boolean isAlreadyOpened(){
        return (isOpened);
    }

    boolean isAlreadyFlagged(){
        return (isFlagged);
    }
}
