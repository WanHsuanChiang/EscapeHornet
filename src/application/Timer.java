package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Timer extends Application {

    private AnimationTimer timer;
    private Label          lblTime = new Label("0 .s");
    private int            seconds;

    @Override
    public void start(Stage primaryStage) {
        timer = new AnimationTimer() {

        private long lastTime = 0;

        @Override
        public void handle(long now) {
            if (lastTime != 0) {
                if (now > lastTime + 1_000_000_000) {
                    seconds++;
                    lblTime.setText(Integer.toString(seconds) + " .s");
                    lastTime = now;
                }
            } else {
                lastTime = now;

            }
        }

        @Override
        public void stop() {
            super.stop();
            lastTime = 0;
            seconds = 0;
        }
    };

    Button btnStart = new Button("START");
    btnStart.setOnAction(e ->
    {
        lblTime.setText("0 .s");
        timer.start();
    });

    Button btnStop = new Button("STOP");
    btnStop.setOnAction(e -> timer.stop());


        VBox box = new VBox(16, lblTime, btnStart, btnStop);
        box.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(new StackPane(box)));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}