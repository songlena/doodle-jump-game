package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MovingPlatform extends Platform{
    private boolean right;
    /**
     * This method uses inheritance and sets the standard platform to the color blue. Then, the platform is graphically
     * added to the gamePane.
     * **/
    public MovingPlatform(Pane gamePane, double x, double y){
        super(gamePane, x, y);
        super.getPlatform().setFill(Color.BLUE);
        gamePane.getChildren().add(super.getPlatform());

    }
    /**
     * This method is responsible for moving the platform left and right. If the platform is moving right (boolean is
     * true), the platform changes x position by the parameter xChange. Then it sets the boolean to false if the
     * platform has reached the edge of the screen. This triggers the else statement which indicates that the platform
     * is moving to the left, and the boolean is set to true again when the platform reaches the left side of the
     * screen.
     * **/
@Override
    public void move(double xChange){
    if (this.right) {
        this.setX(this.getX() + xChange);
        if (this.getX() > Constants.PANE_EWIDTH - Constants.PLATFORM_WIDTH) {
            this.right = false;
        }
    }
    else {
        this.setX(this.getX() - xChange);
        if (this.getX() < Constants.PANE_SWIDTH) {
            this.right = true;
        }
    }
    }
}
