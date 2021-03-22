package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import model.GlobalVariableModel;

public class GameView extends GlobalVariableModel {		
	
	public static BorderPane borderpane;	
	public static Label timer;
	/*
	private static String season = model.GlobalVariableModel.recentSeason;
	private static HashMap<String, Integer> map = model.GlobalVariableModel.seasonTimeMap;
	
	
	
	// private class constant and some variables
	private static final Integer STARTTIME = map.get(season);
	private static Timeline timeline;
	// Make timeSeconds a Property
	private static IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	
	*/	
	
	private static void setBackground() {
		
		// create background image
		// Reference: https://www.geeksforgeeks.org/javafx-background-class/		
		String bgImgPath = "resources/hornet-nest.jpg";	  
		FileInputStream input;
		try {
			input = new FileInputStream(bgImgPath);			
	        Image img = new Image(input,screenSize[0]+200,screenSize[1]+200,true,true); 
	        BackgroundImage backgroundimage = new BackgroundImage(img,  
	                                         BackgroundRepeat.NO_REPEAT,  
	                                         BackgroundRepeat.NO_REPEAT,  
	                                         BackgroundPosition.DEFAULT,  
	                                         BackgroundSize.DEFAULT); 	       
	        Background background = new Background(backgroundimage);	        
	        borderpane.setBackground(background);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
		
	}
		
	
	//Reference: https://stackoverflow.com/questions/40530899/honeycomb-layout-in-javafx-with-flowpane
	private static GridPane createHoneyComb(int rows, int columns, double size) {
		
			
	    double[] points = new double[12];
	    for (int i = 0; i < 12; i += 2) {
	        double angle = Math.PI * (0.5 + i / 6d);
	        points[i] = Math.cos(angle);
	        points[i + 1] = Math.sin(angle);
	    }
	    Polygon polygon = new Polygon(points);

	    GridPane result = new GridPane();
	    RowConstraints rc1 = new RowConstraints(size / 4);
	    rc1.setFillHeight(true);
	    RowConstraints rc2 = new RowConstraints(size / 2);
	    rc2.setFillHeight(true);

	    double width = Math.sqrt(0.75) * size;
	    ColumnConstraints cc = new ColumnConstraints(width/2);
	    cc.setFillWidth(true);
	    
	    
	    for (int i = 0; i < columns; i++) {
	        result.getColumnConstraints().addAll(cc, cc);
	    }
	    
	    
	    for (int r = 0; r < rows; r++) {
	        result.getRowConstraints().addAll(rc1, rc2);
	        int offset = r % 2;
	        int count = columns - offset;
	        for (int c = 0; c < count; c++) {
	        	
	        	ToggleButton btn = new ToggleButton();
	        	      	
	        	Integer btnNumber = model.HornetCount.getOrder(r, c); //The order of button
	        	//String btnText = String.valueOf(btnNumber);
	            //List<String> hornetList = model.HornetCount.getHornetList();//determine which btn is hornet
	        	List<Integer> hornetList = model.HornetCount.getHornetIntList();
	        	Boolean hasHornet = (hornetList.contains(btnNumber))? true : false;	        	
	            //btnText = (hornetList.contains(btnText))?"1":"0";        
	        	       	
	        			
	        	btn.getStyleClass().add("button-hornet");
	        	
	        	//if(btnText == "1") {btn.getProperties().put("hasHornet", true);}
	        	//else{btn.getProperties().put("hasHornet", false);}      	   	
	        	btn.getProperties().put("hasHornet", hasHornet);
	        	btn.getProperties().put("#", btnNumber);
	        	btn.getProperties().put("row", r);
	        	btn.getProperties().put("column", c);
	        	btn.setId(btnNumber.toString());
	        	model.GlobalVariableModel.order.put(btnNumber,hasHornet);
	        	btn.addEventFilter(MouseEvent.MOUSE_CLICKED, new controller.HornetButtonController());
	        		        	
	            btn.setPrefSize(width, size);
	            btn.setShape(polygon);
	            result.add(btn, 2 * c + offset, 2 * r, 2, 3);        
	            
	            
	        }
	    }
	    result.getRowConstraints().add(rc1);    
	       
	    return result;
	}
	
	public static Pane getGame() {		
		
		
		
		String season = recentMap.get("season");		
		recentHornetParam = (Integer) seasonColMap.get(season);
		
		//timer
		Integer startTime = (int) seasonTimeMap.get(season);
		timer = new Label();
		timer.getStyleClass().add("timer");
		controller.TimerController.startTimer(startTime);
		
		HBox heading = new HBox();
		heading.setAlignment(Pos.CENTER);
		heading.setPadding(insets);
		heading.getChildren().add(timer);		
		
				
		
		
		//honeycomb
		GridPane honeyComb = new GridPane();		
		
		honeyComb = createHoneyComb(recentHornetParam,recentHornetParam,cellSize);
		honeyComb.setAlignment(Pos.CENTER);	
		
		VBox vbox = new VBox();	
		vbox.getChildren().addAll(honeyComb);
		vbox.setAlignment(Pos.CENTER);
		
		borderpane = new BorderPane();
		BorderPane.setAlignment(borderpane, Pos.CENTER);
		
		borderpane.setCenter(honeyComb);		
		borderpane.setTop(heading);	
		setBackground();
		
		borderpane.setId("game-view");
				
		return borderpane;
	}

}
