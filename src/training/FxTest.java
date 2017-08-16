package training;/**
 * Created by traitorwtf on 28.03.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxTest extends Application {
    private Stage stage;
    private Scene scene;
    private Pane pane;
    private Label label;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        pane = new Pane();
        scene = new Scene(pane, 640, 480);
        stage.setScene(scene);

        label = new Label("Hello World");


        Button button = new Button("Push");
        button.setLayoutX(100);
        button.setLayoutY(100);
        button.setOnAction(e ->{
            label.setText("Ya krevedko");
        });

        pane.getChildren().addAll(label,button);
        stage.show();

    }


}
