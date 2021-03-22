package controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.GlobalVariableModel;
import model.ProbabilityModel;

//Reference: https://stackoverflow.com/questions/38717690/stopwatch-counting-seconds-javafx



public class OptionController extends GlobalVariableModel implements EventHandler<InputEvent>{		
	
	private String label;
	private String sourceLabel;
	private Node source;
	private String status = (String )recentMap.get("status"); //recent status
	private Boolean isMouseActive;
	private Boolean isKeyActive;
		
	private long lastTime = 0;	
	private Integer pressTime = 120;
	
	private void isFocused(String label) {
		
		//find the target node
		VBox content = view.FindHornetView.content;
		String lookupValue = "#"+label;
		Node target = content.lookup(lookupValue);
		Label actionLabel = (Label) target.lookup(".action-text");
		
		// set effect
		// Reference: source (Example2): https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.Button&method=setGraphic
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(0.8);
		target.setEffect(colorAdjust);
		target.setEffect(new DropShadow(2.0, Color.BLACK));
		
		//change the text to processing
		if( label == "deet") {
			actionLabel.setText("Mouse Pressing...");
		}
		if( label == "squat") {
			actionLabel.setText("Key Pressing...");
		}
	}
	
	private void stopFocused(String label) {
		
		//find the target node
		VBox content = view.FindHornetView.content;
		String lookupValue = "#"+label;
		Node target = content.lookup(lookupValue);
		Label actionLabel = (Label) target.lookup(".action-text");
		
		// set effect
		// Reference: source (Example2): https://www.programcreek.com/java-api-examples/?class=javafx.scene.control.Button&method=setGraphic
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(0);
		target.setEffect(colorAdjust);
		
		//change the text to default
		Object[] arr = findHornetActionMap.get(label);
		actionLabel.setText((String) arr[6]);
		
	}
	
	
	private void change(String label) {		
		
		Object[] arr = findHornetActionMap.get(label);		
		Float stingedRate = (Float) arr[3];	
		Boolean hasStinged = (Boolean) ProbabilityModel.getResult(stingedRate);
		optionMap.put("findHornet", label);
		resultMap.put("findHornet", hasStinged);	
		
		
		isMouseActive = null;
		isKeyActive = null;
		lastTime = 0;
		
		
		if(hasStinged) {
			
			ChangeScreenController.changeScreen(status, "Stinged");
			
		} else {
			
			ChangeScreenController.changeScreen(status, "Break");
			
		}		

	}
	
	private AnimationTimer timer ; 
	
	
	
	
	@Override
	public void handle(InputEvent e) {		
		
		
		if(recentMap.get("status") != "Find Hornet") {
			return;
		}			
		
		
			
		
		
		//For squat (press down)
		if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {	
			
			
			
			KeyCode keyCode = ((KeyEvent) e).getCode();		
			
			
			if( keyCode.equals(KeyCode.DOWN) || (keyCode.equals(KeyCode.KP_DOWN))){		
												
				label = "squat";
				isKeyActive = true;			
				
				
				lastTime = 0;
				timer = new AnimationTimer() {					

					@Override
			        public void handle(long now) {
			            lastTime = lastTime +1;
			            
			            if (lastTime > pressTime ) {			            	
			            	super.stop();
			            	change(label);
						}
			        }
					
					@Override
			        public void stop() {
						lastTime = 0;
			            super.stop();
			            timer = null;
			        }
					
				};
				
				
				timer.start();	
				isFocused(label);
				
				application.Main.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent e) {
						
						KeyCode keyCode = ((KeyEvent) e).getCode();
						if( keyCode.equals(KeyCode.DOWN) || (keyCode.equals(KeyCode.KP_DOWN))) {
							if(lastTime > pressTime) {
								change(label);
							} else {
								isKeyActive = false;
							}
							timer.stop();													
							stopFocused(label);							
						}						
					}
					
				});		
				
			}
		}
		
		
				
		
		
		//long press (deet)
		if(e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			
			source = (Node) e.getSource();
			sourceLabel = (String) source.getProperties().get("label");
			
			label = sourceLabel;			
					
			if(label == "deet") {				
				
				isMouseActive = true;
				
				lastTime = 0;
				timer = new AnimationTimer() {					

					@Override
			        public void handle(long now) {
			            lastTime = lastTime +1;
			            
			            if (lastTime > pressTime && isMouseActive) {			            	
			            	super.stop();
			            	change(label);														
						}
			        }
					
					@Override
			        public void stop() {
						lastTime = 0;
			            super.stop();
			            timer = null;
			        }
					
				};
				
				
				timer.start();
				isFocused(label);
				
				source.setOnMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						if(lastTime > pressTime) {
							change(label);
						} else {
							isMouseActive = false;
						}
						timer.stop();													
						stopFocused(label);	
					}
					
				});
				
			}
			
			
		}		

	}		
	
}
