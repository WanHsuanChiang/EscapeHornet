package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.TreeMap;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.GlobalVariableModel;

public class HornetButtonController extends GlobalVariableModel implements EventHandler<InputEvent> {
	
	public void handle(InputEvent event) {	
		
		
		Node btn  = (Node) event.getSource();		
		Integer r = (Integer) btn.getProperties().get("row");		
		Integer c = (Integer) btn.getProperties().get("column");
		Integer btnOrder = (Integer) btn.getProperties().get("#");		
		Boolean hasHornet = (Boolean) btn.getProperties().get("hasHornet");		
		
		//set button disabled
		btn.setDisable(true);
		
		
		if(!hasHornet) {
			
			Integer nearbyHornet = model.HornetCount.getNearbyHornet(btn, r, c, btnOrder, hasHornet);
			if(nearbyHornet != 0) {
				((Labeled) btn).setText(nearbyHornet.toString());
			}
			
			
		}
		else {
			
			//if the user click on the button with hornet
			
			controller.TimerController.stopTimer();
			btn.toFront();						
			
			ImageView img;
			try {
				img = new ImageView(new Image(new FileInputStream("resources/hornet.png")));
				img.setFitHeight(model.GlobalVariableModel.cellSize-20);
			    img.setPreserveRatio(true);
			    ((Labeled) btn).setGraphic(img); 
			    img.toFront();
			    
			    ScaleTransition transition = new ScaleTransition(Duration.millis(2000),img);
				transition.setFromX(1); // original x
				transition.setFromY(1); // original y
				transition.setToX(60); 
				transition.setToY(60);
				transition.play(); 					
				
				
				transition.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {						
						ChangeScreenController.changeScreen(recentMap.get("status"),"Find Hornet");
					}
					
				});
			    
			    
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			
			
			
			//((Labeled) btn).setText("M");			
			
			
			
			//Pane findHornetView = view.FindHornetView.getFindHornetView();
			
			
		
		}
		
				
		
	}

	

}
