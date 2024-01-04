package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class Doodle {
    private Rectangle doodle;
    double updatedVelocity;
    double currentVelocity;
    double updatedPosition;

    public Doodle(Pane gamePane) {
        this.doodle = new Rectangle(Constants.DOODLE_XPOSITION, Constants.DOODLE_YPOSITION, Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        this.doodle.setFill(Color.LIGHTBLUE);
        gamePane.getChildren().add(this.doodle);
    }

    /** this method is in control of moving our doodle up and down. It also uses physics to ensure that our doodle is
     * actually bouncing instead of simply moving up and down.*/
    public void moveVertically(){
        this.updatedVelocity = this.currentVelocity + Constants.GRAVITY * Constants.DURATION;
        this.updatedPosition = this.doodle.getY() + this.updatedVelocity * Constants.DURATION;
        this.doodle.setY(this.updatedPosition);
        this.currentVelocity = this.updatedVelocity;
    }

    /** this method sets the current velocity of the doodle to the rebound velocity. this method is called when the
     * doodle collides with a platform*/
    public void reboundVelocity(){
        this.currentVelocity = Constants.REBOUND_VELOCITY;
    }

    /** this method sets up the bouncy velocity. It is called when the doodle intersects an extrabouncy platform*/
    public void bouncyVelocity(){
        this.currentVelocity = Constants.BOUNCY_REBOUND_VELOCITY;
    }

    /**this method moves our doodle horizontally. It increments the doodle left and right by the parameter xChange
     *  It uses an if statement to wrap our doodle around the screen if it reaches the edge. */
    public void moveHorizontally(double xChange) {
        double newDoodleLoc = this.doodle.getX();
        newDoodleLoc += xChange;
        this.doodle.setX(newDoodleLoc);
        if (newDoodleLoc >= Constants.PANE_EWIDTH) {
            this.doodle.setX(Constants.PANE_SWIDTH);
        }
        if (newDoodleLoc < Constants.PANE_SWIDTH) {
            this.doodle.setX(Constants.PANE_EWIDTH - Constants.PLATFORM_OFFSET);
        }
    }

    /** this is a getter method that returns the x coordinate of the doodle*/
    public double getX(){
        return this.doodle.getX();
    }

    /** this is a getter method that returns the y coordinate of the doodle*/
    public double getY(){
        return this.doodle.getY();
    }

    /** this is a setter method that sets the y coordinate of the doodle*/
    public void setY(double distance){
        this.doodle.setY(distance);
    }

    /** this is a getter method that returns the current of the doodle. it is used in checking collision  between
     * the doodle and platforms.*/
    public double getCurrentVelocity(){
        return this.currentVelocity;
    }

    /** this is a boolean method that returns true when the doodle has fallen off the screen. It is used in the
     * gameOver method in the Game class.*/
    public boolean gameOver(){
        if (this.doodle.getY() > Constants.GAME_OVER) {
            return true;
        }
        else {
            return false;
        }
    }
}
