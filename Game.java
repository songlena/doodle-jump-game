package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;

public class Game {
    private Doodle doodle;
    private Pane gamePane;
    private Platform platform;
    private ArrayList<Platform> platformArrayList;
    private double distance;
    private int score;
    private Label label;
    private Timeline timeline;
    private boolean generated = false;

    public Game(Pane gamePane) {
        this.gamePane = gamePane;
        this.doodle = new Doodle(this.gamePane);
        this.platform = new StandardPlatform(this.gamePane, Constants.PLATFORM_X, Constants.PLATFORM_Y);
        this.platformArrayList = new ArrayList<>();
        this.platformArrayList.add(this.platform);

        this.setUpTimeline();
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.onKeyPressed(e));
        this.label = new Label();
    }

    /** this method creates the timeline which calls gameupdate*/
    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.gameUpdate());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /** game update is constanly called since it is in the timeline, and generates the platforms, moves the doodle,
     * checks for collision and scrolling, and graphically and logically removes the platforms. it also checks for
     * when the game is over*/
    private void gameUpdate() {
        if (!this.generated) {
            this.generated = true;
        }
        this.genPlatform();
        this.doodle.moveVertically();
        this.checkCollision();
        this.checkScroll();
        this.removePlatforms();
        for(int i = 0; i<this.platformArrayList.size(); i++){
            this.platformArrayList.get(i).move(1);
        }
        this.gameOver();
    }

    /** this method is our eventhandler for our methods. When A is pressed, the doodle moves left 35 pixels. When D
     * is pressed, it moves right 35 pixels.*/
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case A:
                this.doodle.moveHorizontally(-Constants.MOVE_H);
                break;
            case D:
                this.doodle.moveHorizontally(Constants.MOVE_H);
                break;
            default:
                break;
        }
        keyEvent.consume();
    }

    /** This method generates new platforms when the doodle has reached a certain point on the screen. It also moves
     * the platforms down when we jump on one and reach a certain point.*/
    private void checkScroll() {
        if (this.doodle.getY() < (double) (Constants.PANE_HEIGHT - Constants.PANE_OFFSET)/2) {
            this.distance = (Constants.PANE_HEIGHT - Constants.PANE_OFFSET)/2 - this.doodle.getY();
            this.doodle.setY((Constants.PANE_HEIGHT - Constants.PANE_OFFSET)/2);
            this.updateLabel(Constants.INCREMENT_SCORE);
            for (Platform platform : platformArrayList) {
                platform.scrollPlat(this.distance);
            }
            this.genPlatform();
        }
    }

    /** this method contains a for loop that removes our platforms logically and graphically when they are below the
     * screen*/
    private void removePlatforms() {
        for (int i = 0; i < platformArrayList.size(); i++) {
            if (this.platformArrayList.get(i).getY() > Constants.PANE_HEIGHT - Constants.PANE_OFFSET) {
                this.platformArrayList.get(i).removePlatPane();
                this.platformArrayList.remove(i);
            }
        }
    }

/** this method is a for each loop. In each Platform of the platformArrayList, it checks for collision between
 * the platform and doodle. If the platform is a bouncy platform, the velocity of the doodle is set to bouncy
 * Velocity. If the doodle bounces on any other playform, the doodle is set to regular reboundVelocity.*/
    private void checkCollision() {
        for (Platform platform : this.platformArrayList) {
            if (platform.hasCollided(this.doodle) && this.doodle.getCurrentVelocity() > 0 && platform.isBouncy()) {
                this.doodle.bouncyVelocity();
                platform.collide();
                break;

            } else if (platform.hasCollided(this.doodle) && this.doodle.getCurrentVelocity() > 0) {
                this.doodle.reboundVelocity();
                platform.collide();
                break;
                    }
                }
            }

            /** this is the code that semirandomly generates the location for the platforms. Platforms are given a
             * random X and Y, which is then added to the platform. the platform is then added to the arraylist.*/
            private void genPlatform () {
                while (this.platform.getY() > 10) {
                    double xLow = Math.max(0, this.platform.getX() - 75);
                    double xHigh = Math.min(400 - Constants.PLATFORM_WIDTH, this.platform.getX() + 75);
                    double xRand = (Math.random() * (xHigh - xLow) + xLow);

                    double yLow = this.platform.getY() - 100;
                    double yHigh = this.platform.getY() - 20;
                    double yRand = (Math.random() * (yHigh - yLow) + yLow);

                    Platform newPlatform = this.platformRandomize(xRand, yRand);
                    this.platformArrayList.add(newPlatform);
                    this.platform = newPlatform;
                }
            }

            /** this method randomly creates a subclass of class Platform. Those four classes are randomly added
             * to the screen and makes the game more fun. We use a switch statement and Javas random number generator
             * to achieve this.*/
            private Platform platformRandomize ( double x, double y){
                int random = (int) (Math.random() * 4);
                Platform plat;
                switch (random) {
                    case 0:
                        plat = new MovingPlatform(this.gamePane, x, y);
                        break;
                    case 1:
                        plat = new ExtraBouncePlatform(this.gamePane, x, y);
                        break;
                    case 2:
                        plat = new DisappearingPlatform(this.gamePane, x, y);
                        break;
                    default:
                        plat = new StandardPlatform(this.gamePane, x, y);
                        break;
                }
                return plat;
            }

            /** this method creates a label that keeps track of our score. THe label is then added to a label pane*/
            public void updateLabel (int increment){
                this.score += increment;
                this.label.setText(" Score: " + this.score);
                Pane pane = new Pane();
                this.gamePane.getChildren().add(pane);
                pane.getChildren().add(this.label);
            }

            /** this method is called when the doodle falls below the screen. The timeline is stopped and a label shows
             * up on the screen. On the label is a message that tells us that the game is over.*/
            public void gameOver () {
                if (this.doodle.gameOver()) {
                    this.timeline.stop();
                    Label gameOver = new Label("Game Over!");
                    gameOver.setTranslateX(170);
                    gameOver.setTranslateY(300);
                    Pane pane = new Pane();
                    this.gamePane.getChildren().add(pane);
                    pane.getChildren().add(gameOver);
                    this.gamePane.setFocusTraversable(false);
                }
            }
        }
