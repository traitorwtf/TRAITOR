package My_Game_Xonix;

import java.awt.*;

/**
 * Created by traitorwtf on 26.03.2017.
 */
public class XonixField {

    boolean isWater;
    boolean isGround;
    static int groundSize = 4;
    int tempWaterArea;
    int totalWaterArea = (XonixGame.FIELD_WIDTH-groundSize)*(XonixGame.FIELD_HEIGHT-groundSize);

    static int percentOfFilledWater;

    static int field [][] = new int[XonixGame.FIELD_WIDTH][XonixGame.FIELD_HEIGHT];

    XonixField(){
        initialize();
    }

    void initialize(){
        for (int y = 0; y < XonixGame.FIELD_HEIGHT; y++){
            for (int x = 0; x < XonixGame.FIELD_WIDTH; x++){
                if (x<=groundSize || x>= XonixGame.FIELD_WIDTH-groundSize || y<=groundSize || y>= XonixGame.FIELD_HEIGHT-groundSize){
                    setGround(x, y);
                } else{
                    setWater(x, y);
                }
            }
        }
        tempWaterArea = totalWaterArea;
    }

    boolean getGround(int x, int y){
        if (x >= XonixGame.FIELD_WIDTH-1) x = XonixGame.FIELD_WIDTH-1;
        if (y >= XonixGame.FIELD_HEIGHT-1) y = XonixGame.FIELD_HEIGHT-1;
        return (field[x][y] == XonixGame.COLOR_LAND);
    }

    void setGround(int x, int y){
        isGround = true;
        field[x][y] = XonixGame.COLOR_LAND;
    }

    void setWater(int x, int y){
        isWater = true;
        field[x][y] = XonixGame.COLOR_WATER;
    }

    void fillingWater(int x, int y){
        if (field[x][y] != XonixGame.COLOR_WATER) return;

        field[x][y] = XonixGame.TEMP_COLOR;
        for (int dy = -1; dy <= 1; dy++){
            for (int dx = -1; dx <= 1; dx++ ){
                fillingWater(x+dx,y+dy);
            }
        }

    }

    void tryingToFillWater(){
        tempWaterArea = 0;
        fillingWater(XonixGame.ball.getX(), XonixGame.ball.getY());
        for (int y = 0; y < XonixGame.FIELD_HEIGHT; y++) {
            for (int x = 0; x < XonixGame.FIELD_WIDTH; x++) {
                if (field[x][y] == XonixGame.COLOR_TRACE || field[x][y] == XonixGame.COLOR_WATER) {
                    field[x][y] = XonixGame.COLOR_LAND;
                }
                if (field[x][y] == XonixGame.TEMP_COLOR) {
                    field[x][y] = XonixGame.COLOR_WATER;
                    tempWaterArea++;
                }
            }
        }
        checkWinCondition();

    }

    void clearTrace(){
        for (int y = 0; y < XonixGame.FIELD_HEIGHT; y++) {
            for (int x = 0; x < XonixGame.FIELD_WIDTH; x++) {
                if (field[x][y] == XonixGame.TEMP_COLOR) field[x][y] = XonixGame.COLOR_WATER;
            }
        }
    }

    void checkWinCondition(){
        percentOfFilledWater = ((totalWaterArea - tempWaterArea-300)*100/totalWaterArea);
        if (percentOfFilledWater >= XonixGame.PERCENT_OF_WATER_CAPTURE){
            XonixGame.youWon = true;
            //System.out.println(XonixGame.youWon);
        }

    }


    void paint(Graphics g){
        for (int y = 0; y < XonixGame.FIELD_HEIGHT; y++) {
            for (int x = 0; x < XonixGame.FIELD_WIDTH; x++) {
                g.setColor(new Color(field[x][y]));
                g.fillRect(x*XonixGame.POINT_SIZE,y*XonixGame.POINT_SIZE, XonixGame.POINT_SIZE, XonixGame.POINT_SIZE);
            }
        }
    }
}
