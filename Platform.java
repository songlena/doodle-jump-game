package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Platform {
    private Rectangle platform;
    private Pane gamePane;
    private Doodle doodle;
    /**
     * Constructor for the platform class that creates a basic platform so that the subclasses can inherit.
     * **/
    public Platform(Pane gamePane, double x, double y){
        this.gamePane = gamePane;
        this.platform = new Rectangle(x, y, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
    }
    /**
     * returns a true or false if the platform has collided with the doodle. This method is used in the method
     * checkCollision() in game.
     * **/
    public boolean hasCollided(Doodle doodle){
        return this.platform.intersects(doodle.getX(), doodle.getY(), Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
    }
    /**
     * This method gets the x position of platform;
     * **/
    public double getX(){
        return this.platform.getX();
    }
    /**
     * This method gets the y position of platform;
     * **/
    public double getY(){
        return this.platform.getY();
    }
    /**
     * This method returns platform and is used in another class to access platform;
     * **/
    public Rectangle getPlatform(){
        return this.platform;
    }
    /**
     * This method scrolls the platform a certain distance given a double parameter. It is used in the checkScroll()
     * method of the Game class.
     * **/
    public void scrollPlat(double distance){
        this.platform.setY(this.getY() + distance);
    }
    /**
     * This method removes the platform from the pane.
     * **/
    public void removePlatPane(){
        this.gamePane.getChildren().remove(this.platform);
    }
    /**
     * This method sets the x location of the platform to a passed in double parameter.
     * **/
    public void setX(double location){
        this.platform.setX(location);
    }

    /**
     * This method is used by a subclass of Platform
     * **/
    public void collide(){
    }
    /**
     * This method is used by a subclass of Platform to move the platforms
     * **/
    public void move(double xChange){
    }
    /**
     * This method is used in the extra bouncy platform and returns true.
     * **/
    public boolean isBouncy() {
        return false;
    }
}
