package doodlejump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your DoodleJump game will start.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below. You will need to fill
 * in the start method to start your game!
 *
 * Class comments here...
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Doodle Jump");
        DoodleOrganizer organizer = new DoodleOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.PANE_EWIDTH, Constants.PANE_HEIGHT - Constants.PANE_HOFFSET);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] argv) {
        // launch is a static method inherited from Application.
        launch(argv);
    }
}
