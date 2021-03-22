package controller;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;
import model.GlobalVariableModel;

public class TimerController extends GlobalVariableModel {
	
	private static Label timer = view.GameView.timer;	
	private static HashMap<String, String> recentMap = model.GlobalVariableModel.recentMap;
	
	// private class constant and some variables
	//private static final Integer STARTTIME = map.get(season);
	public static Timeline timeline;
	// Make timeSeconds a Property
	//private static IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	
	
	public static void startTimer(int startTime) {
		
		IntegerProperty timeSeconds = new SimpleIntegerProperty(startTime);
		
		timer.textProperty().bind(timeSeconds.asString());
		
		
		if (timeline != null) {
			timeline.stop();
		}
		
		
		timeSeconds.set(startTime);
		timeline = new Timeline();
		timeline.getKeyFrames().add(
		new KeyFrame(Duration.seconds(startTime+1),
		new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();
		
		timeline.setOnFinished(e ->{				
			recentMap.put("status", "Timeout");
			controller.ChangeScreenController.changeScreen("Playing","Timeout");
		});
		
	}
	
	public static void pauseTimer() {
		
		timeline.pause();
		
	}
	
	public static void continueTimer() {
		
		timeline.play();
		
		timeline.setOnFinished(e ->{				
			recentMap.put("status", "Timeout");
			controller.ChangeScreenController.changeScreen("Playing","Timeout");
		});
		
	}
	
	public static void stopTimer() {
		
		timeline.stop();
		
	}

}
