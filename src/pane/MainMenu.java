package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Goto;

public class MainMenu extends StackPane {
    private static MainMenu instance;

    public MainMenu(){
//        Button start = new Button("Start");
//        start.setOnMouseClicked(mouseEvent -> {Goto.mapPage();});
//        getChildren().add(start);
        LinearGradient linearGradient = new LinearGradient(
                0, // start X
                0, // start Y
                1, // end X
                1, // end Y
                true, // proportional
                javafx.scene.paint.CycleMethod.NO_CYCLE, // cycle colors
                new Stop(0.0, Color.RED),
                new Stop(1/6.0, Color.ORANGE),
                new Stop(2/6.0, Color.YELLOW),
                new Stop(3/6.0, Color.GREEN),
                new Stop(4/6.0, Color.BLUE),
                new Stop(5/6.0, Color.INDIGO),
                new Stop(1.0, Color.VIOLET)
        );
        VBox vbox = new VBox(20); // Create a VBox layout with a spacing of 20
        vbox.setAlignment(Pos.CENTER); // Center

        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);

        Label titleLabel = new Label("Kitten Chore");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50)); // Set the font to Arial, bold, size 50
        titleLabel.setTextFill(Color.BLANCHEDALMOND); // Set the text color to dark blue

        // Add drop shadow effect to create a stroke-like effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE); // Set the shadow color to white
        dropShadow.setRadius(10); // Set the shadow radius
        titleLabel.setEffect(dropShadow); // Apply the drop shadow effect
        // Position the label a bit upper than the center
        titleLabel.setTranslateY(-50); // Adjust this value as needed

        // Center the label horizontally
        StackPane.setAlignment(titleLabel, Pos.CENTER);

        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(mouseEvent -> {Goto.test();});
        startGame.setMinHeight(100);
        startGame.setMinWidth(200);
        startGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        startGame.setStyle("-fx-background-radius: 20;");

        Button quitGame = new Button("Quit Game");
        quitGame.setOnMouseClicked(mouseEvent -> {System.exit(0);}); // Exit the application when the button is clicked
        quitGame.setMinHeight(50);
        quitGame.setMinWidth(200);
        quitGame.setFont(Font.font("System", FontWeight.BOLD, 24));
        quitGame.setStyle("-fx-background-radius: 20;");

// Add the quit game button to the VBox
        vbox.getChildren().addAll(titleLabel, startGame, quitGame);
        getChildren().add(vbox);
    }

    public static MainMenu getMainMenu(){
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

}
