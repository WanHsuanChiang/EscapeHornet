package application;



import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerTimeline extends Application {

	 // private class constant and some variables
	 private static final Integer STARTTIME = 15;
	 private Timeline timeline;
	 // Make timeSeconds a Property
	 private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

	 @Override
	 public void start(Stage primaryStage) {

	 // Setup the Stage and the Scene (the scene graph)
	 primaryStage.setTitle("Timer");
	 Group root = new Group();
	 Scene scene = new Scene(root, 80, 30);

	 // Bind the timerLabel text property to the timeSeconds property
	 Label timerLabel = new Label();
	 timerLabel.textProperty().bind(timeSeconds.asString());

	 // a Button that controls the timer
	 Button button = new Button();
	 button.setText("start");
	 button.setOnAction(e-> {
	 if (timeline != null) {
	 timeline.stop();
	 }
	 timeSeconds.set(STARTTIME);
	 timeline = new Timeline();
	 timeline.getKeyFrames().add(
			 new KeyFrame(Duration.seconds(STARTTIME+1),
			new KeyValue(timeSeconds, 0)));
	 timeline.playFromStart();
	 });

	 // layout
	 FlowPane panel = new FlowPane();
	 panel.getChildren().addAll(button, timerLabel);
	 root.getChildren().add(panel);

	 primaryStage.setScene(scene);
	 primaryStage.show();
	 }
	}