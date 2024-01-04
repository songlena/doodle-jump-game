package doodlejump;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DoodleOrganizer {
    private BorderPane root;
    private HBox buttonPane;
    private Game game;
    private Pane gamePane;

    public DoodleOrganizer(){
        this.root = new BorderPane();
        this.gamePane = new Pane();

        this.buttonPane = new HBox();
        this.buttonPane.setStyle("-fx-background-color: lightgrey");
        this.buttonPane.setPrefSize(Constants.PANE_EWIDTH, Constants.BUTTONHEIGHT);
        this.root.getChildren().add(this.gamePane);
        this.setUpButton(this.buttonPane);

        Pane doodlePane = new Pane();
        this.root.setCenter(doodlePane);
        this.root.setBottom(this.buttonPane);
        this.game = new Game(this.root);
    }

    /** This method returns the root which we use to set the scene */
    public Pane getRoot(){
        return this.root;
    }

    /** this method creates the quit button and aligns it*/
    private void setUpButton(HBox buttonPane) {
        Button b1 = new Button("Quit");
        buttonPane.getChildren().addAll(b1);
        b1.setOnAction((ActionEvent e) -> System.exit(0));
        buttonPane.setAlignment(Pos.CENTER);
    }
}
