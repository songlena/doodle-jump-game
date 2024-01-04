package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StandardPlatform extends Platform {
/**
 * This method uses inheritance and sets the standard platform to the color black. Then, the platform is graphically
 * added to the gamePane.
 * **/
    public StandardPlatform(Pane gamePane, double x, double y){
        super(gamePane, x, y);
        super.getPlatform().setFill(Color.BLACK);
        gamePane.getChildren().add(super.getPlatform());
    }
}
