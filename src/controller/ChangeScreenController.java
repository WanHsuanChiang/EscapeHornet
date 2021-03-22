package controller;

import java.io.FileNotFoundException;
import java.time.Duration;

import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import model.GlobalVariableModel;

public class ChangeScreenController extends GlobalVariableModel {
	
	private static Pane root = application.Main.root;
	
	
	public static void changeScreen(String preStatus, String nexStatus)  {	
		
		System.out.print("* Change Screen Controller Fired:" + preStatus + "/" + nexStatus);
		recentMap.put("status", nexStatus);
		String season = recentMap.get("season");
		
		if(nexStatus == "Playing") {
			
			Object[] arr = seasonMap.get(season);
			Integer startTime = (Integer) arr[3];
			
			Pane gameView = view.GameView.getGame();
			
			root.getChildren().clear();
			root.getChildren().add(gameView);			
		}
		
		//Playing -> Find Hornet
		if(nexStatus == "Find Hornet") {
			controller.TimerController.pauseTimer();
			Pane findHornetView;
			try {
				findHornetView = view.FindHornetView.getFindHornetView();
				root.getChildren().add(findHornetView);
				root.lookup("#game-view").setVisible(false);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
		}
		
		
		// Find Hornet -> Stinged
		if(nexStatus == "Stinged") {
			
			try {				
				Pane general = view.GeneralView.getGeneral(nexStatus);				
				root.getChildren().clear();
				root.getChildren().add(general);							
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
			
			
		}
		
		// Find Hornet -> Break
		if(nexStatus == "Break") {
			
			try {
				Pane general = view.GeneralView.getGeneral(nexStatus);		
				root.getChildren().clear();
				root.getChildren().add(general);
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
			
			
			
		}
		//Playing -> Timeout
		if(nexStatus == "Timeout") {
			
			Pane general = view.GeneralView.getGeneral(nexStatus);
			root.getChildren().clear();
			root.getChildren().add(general);
			
			controller.TimerController.timeline = null;
		}
		
		//Playing -> Success
		if(nexStatus == "Success") {
			
			Pane general = view.GeneralView.getGeneral(nexStatus);
			root.getChildren().clear();
			root.getChildren().add(general);
			
			controller.TimerController.timeline = null;
			
		}		
		
	}	
	
}