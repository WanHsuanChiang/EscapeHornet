package controller;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.GlobalVariableModel;
import model.ProbabilityModel;
import view.StingedView;

//https://stackoverflow.com/questions/37472273/detect-single-key-press-in-javafx



public class FindHornetScreenController extends GlobalVariableModel implements EventHandler<InputEvent>{		
	
	private String label;
	private String sourceLabel;
	private Node source;
	private String status = (String )recentMap.get("status"); //recent status
	private Boolean isKeyActive;
	private Boolean isMouseActive;
	private Timeline timeline;
	private AnimationTimer timer;
	private Integer seconds;
	private long lastTime = 0;
	private AnimationTimer cursorTimer;
	
	
	
	
	private Timeline getTimeline(int startTime) {		
		
		IntegerProperty timeSeconds = new SimpleIntegerProperty(startTime);
		timeline = new Timeline();	
		timeline.getKeyFrames().add(
				 new KeyFrame(Duration.seconds(startTime+1),
				 new KeyValue(timeSeconds, 0)));
		timeline.playFromStart();
		
		return timeline;
	}
	
	private void change(String label) {		
		
		Object[] arr = findHornetActionMap.get(label);		
		Float stingedRate = (Float) arr[3];		
		Boolean hasStinged = (Boolean) ProbabilityModel.getResult(stingedRate);		
		
		optionMap.put("findHornet",label);
		
		if(hasStinged) {			
			
			ChangeScreenController.changeScreen(status, "Stinged");
			
		} else {
			
			ChangeScreenController.changeScreen(status, "Break");
			
		}		

	}
	
	private void isFocused(String label) {
		VBox content = view.FindHornetView.content;
		String lookupValue = "#"+label;
		Node target = content.lookup(lookupValue);
		Label actionLabel = (Label) target.lookup(".action-text");		
				
		if( label == "deet") {
			actionLabel.setText("Mouse Pressing...");
		}
		if( label == "squat") {
			actionLabel.setText("Key Pressing...");
		}
	}
	
	private void stopFocused(String label) {
		
		VBox content = view.FindHornetView.content;
		String lookupValue = "#"+label;
		Node target = content.lookup(lookupValue);
		Label actionLabel = (Label) target.lookup(".action-text");		
				
		if( label == "deet") {
			actionLabel.setText("Mouse Pressing...");
		}
		if( label == "squat") {
			actionLabel.setText("Key Pressing...");
		}
	}
	
	@Override
	public void handle(InputEvent e) {
		
		source = (Node) e.getSource();
		sourceLabel = (String) source.getProperties().get("label");
		
		if(recentMap.get("status") != "Find Hornet") {
			
		}
		
		
		
		//For squat (press down)
		if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {			
			
			KeyCode keyCode = ((KeyEvent) e).getCode();		
			if( keyCode.equals(KeyCode.DOWN) || (keyCode.equals(KeyCode.KP_DOWN))){		
				
								
				label = "squat";
				
				isKeyActive = true;	
				
				
				//add timeline for "Down" Long Press				
				timeline = getTimeline(3);				
				
				//Detect finished					
				timeline.setOnFinished(new EventHandler() {

					@Override
					public void handle(Event e) {
						if(isKeyActive) {
							change(label);
						} 						
					}
					
				});
				
				//determine key release for "down"
				application.Main.scene.addEventFilter(KeyEvent.KEY_RELEASED,new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent e) {
						
						KeyCode keyCode = ((KeyEvent) e).getCode();
						if( keyCode.equals(KeyCode.DOWN) || (keyCode.equals(KeyCode.KP_DOWN))){
							isKeyActive = false;
						}					
						
					}
					
				});
			}
		}
		
		
				
		
		
		
		if(e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			
			label = sourceLabel;
			
			//for Long Press: Deet
			//Reference: https://stackoverflow.com/questions/38717690/stopwatch-counting-seconds-javafx
			if(label == "deet") {				
				
				Integer pressTime = 120;
				lastTime = 0;
				timer = new AnimationTimer() {					

					@Override
			        public void handle(long now) {
			            lastTime = lastTime +1;
			            
			            if (lastTime > pressTime) {
							change(label);
							super.stop();
						}
			        }
					
					@Override
			        public void stop() {
			            super.stop();
			        }
					
				};
				
				
				timer.start();
				//isFocused(label);
				
				
				source.setOnMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						timer.stop();
						if(lastTime > pressTime) {
							change(label);
						}
					}
					
				});
				
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		/*
		if(e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			
			System.out.print("Drag Detected");
			Node source = (Node) e.getSource();
			String targetLabel = (String) source.getProperties().get("label");
			
			if( targetLabel != "clothes" ){		
				return;				
			} else {
				label = targetLabel;
				
				Scene scene = source.getScene();			
				Image img;
				try {
					img = new Image(new FileInputStream(imgMap.get(label)));
					scene.setCursor(new ImageCursor(img,img.getWidth()/2,img.getHeight()/2));
					ImageCursor.getBestSize(500, 500);
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		*/		
				/*
				source.addEventHandler(MouseEvent.MOUSE_MOVED,(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent e) {
						
						
						
						
						
						change(label);
					} 
				}));
				*/
				/*			
				source.addEventHandler(MouseEvent.MOUSE_PRESSED ,(new EventHandler<MouseEvent>() {					
					
					@Override
					public void handle(MouseEvent e) {
						
											
						dragDelta.x = label.getLayoutX() - mouseEvent.getSceneX();
					    dragDelta.y = label.getLayoutY() - mouseEvent.getSceneY();
					    label.setCursor(Cursor.MOVE);
					} 
				}));
				
				
				label.setOnMousePressed(new EventHandler<MouseEvent>() {
					  @Override public void handle(MouseEvent mouseEvent) {
					    // record a delta distance for the drag and drop operation.
					    dragDelta.x = label.getLayoutX() - mouseEvent.getSceneX();
					    dragDelta.y = label.getLayoutY() - mouseEvent.getSceneY();
					    label.setCursor(Cursor.MOVE);
					  }
				});				
				
				
				
				
				
				source.addEventHandler(MouseEvent.MOUSE_EXITED ,(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent e) {						
						change(label);
					} 
				}));
			


	private AnimationTimer new AnimationTimer() {
		// TODO Auto-generated method stub
		return null;
	}	*/
	}
		
		
		//change(label);
}
		
		
		
		


	
	
	
	
	/*
	private HashMap map = model.GlobalVariableModel.findHornetActionMap;
	private static HashMap<String, Boolean> currentlyActiveKeys = new HashMap<>();
	
	
	private static boolean removeActiveKey(String codeString) {
        Boolean isActive = currentlyActiveKeys.get(codeString);

        if (isActive != null && isActive) {
            currentlyActiveKeys.put(codeString, false);
            return true;
        } else {
            return false;
        }
    }
	
	public static void handler(Pane pane) {
		
		
		
		pane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				/*
				String codeString = event.getCode().toString();
				if (!currentlyActiveKeys.containsKey(codeString)) {
	                currentlyActiveKeys.put(codeString, true);
	            }
	            
				
				if(event.getCode().equals(KeyCode.DOWN) || event.getCode().equals(KeyCode.KP_DOWN) ) {					
					//TODO
					System.out.print("Event Fire");
					Integer STARTTIME = 5;
					Timeline timeline = new Timeline();
					IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
					timeSeconds.set(STARTTIME);					
					timeline.getKeyFrames().add(
							new KeyFrame(Duration.seconds(STARTTIME+1),	new KeyValue(timeSeconds, 0))
							);
					timeline.playFromStart();
					timeline.setOnFinished(e -> System.out.print("COUNTDOWN FINISHED"));
				}
				
			}
			
		});
		
		pane.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				currentlyActiveKeys.remove(event.getCode().toString());
				
			}
			
		});
		
		new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (removeActiveKey("LEFT")) {
                    System.out.println("left");
                }

                if (removeActiveKey("RIGHT")) {
                    System.out.println("right");
                }

                if (removeActiveKey("UP")) {
                    System.out.println("up");
                }

                if (removeActiveKey("DOWN")) {
                    System.out.println("down");
                }
            }
        }.start();
		
		/*
		EventHandler<>
		
		keyLRHandler = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		*/
		
		/*
		
		Node target = (Node) event.getSource();
		String label = (String) target.getProperties().get("label");
		Object[] targetArr = (Object[]) map.get(label);
		Float stingedRate = (Float) targetArr[3];
		Boolean hasStinged = model.ProbabilityModel.getResult(stingedRate);
		
		if( hasStinged) {
			
			//go to next step: Stinged View
			
			try {				
				Pane stingedView = view.StingedView.getStingedView();
				stingedView.setId("stinged-view");
				model.GlobalVariableModel.recentStatus = "Stinged";
				controller.ChangeScreenController.changeScreen(stingedView);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			//back to game
			
			
		}

		
		
	}
*/

