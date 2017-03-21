package My_Game_Snake;

import java.util.Random;

/**
 * Created by traitorwtf on 21.03.2017.
 */
public class Food extends Block {
    Random random = new Random();

    public Food(){
        super(-1,-1);
        this.color = Game.FOOD_COLOR;
        next();
    }


    void next(){
            setXY(-1,-1);
            int x = random.nextInt(Game.FIELD_WIDTH);
            int y = random.nextInt(Game.FIELD_HEIGHT);
            if (Game.snake.snakeAteHerself(x,y) && x>=1 && y>=1) setXY(x-1,y-1);
            else setXY(x, y);

        /*do{
            x = random.nextInt(Game.FIELD_WIDTH);
            y = random.nextInt(Game.FIELD_HEIGHT);
        } while (Game.snake.snakeAteHerself(x,y));
        this.setXY(x,y);*/
    }


}
