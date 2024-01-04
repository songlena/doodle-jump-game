package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ExtraBouncePlatform extends Platform {
    /**
     * This method uses inheritance and sets the standard platform to the color green. Then, the platform is graphically
     * added to the gamePane.
     * **/
    public ExtraBouncePlatform(Pane gamePane, double x, double y){
        super(gamePane, x, y);
        super.getPlatform().setFill(Color.GREEN);
        gamePane.getChildren().add(super.getPlatform());
    }
    /**
     * This method is used in checkCollision(), where if isBouncy() is true and the other collisions are also true,
     * it will double the rebound velocity to make the platform extra bouncy.
     * **/
    @Override
    public boolean isBouncy(){
        return true;
    }
}
