package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DisappearingPlatform extends Platform {
    /**
     * This method uses inheritance and sets the standard platform to the color red. Then, the platform is graphically
     * added to the gamePane.
     * **/
    public DisappearingPlatform(Pane gamePane, double x, double y){
        super(gamePane, x, y);
        super.getPlatform().setFill(Color.RED);
        gamePane.getChildren().add(super.getPlatform());
    }
    /**
     * This method logically and graphically removes the platform from the screen if the doodle has collided with it.
     * **/
@Override
    public void collide(){
    this.removePlatPane();
    super.getPlatform().setY(700);
}

}

