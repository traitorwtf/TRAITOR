package Snake;

/**
 * Created by traitorwtf on 06.03.2017.
 */
public class Food extends Point {

    public Food() {
        super(-1,-1);
        this.color = MainClass.FOOD_COLOR;

    }

    void eat(){
        this.setXY(-1,-1);
    }

    boolean isEaten(){
        return this.getX() == -1;
    }

    void next(){
        int x, y;
        do{
            x = MainClass.random.nextInt(MainClass.FIELD_WIDTH);
            y = MainClass.random.nextInt(MainClass.FIELD_HEIGHT);
        } while (MainClass.snake.isInsideSnake(x,y));
        this.setXY(x,y);
    }
}
