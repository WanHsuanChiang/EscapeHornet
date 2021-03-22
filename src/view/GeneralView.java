package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.ChangeScreenController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.GlobalVariableModel;

public class GeneralView extends GlobalVariableModel {	
	
	private static VBox vbox;
	
	private static String bgImgPath;
	private static FileInputStream input;
	
	private static void setBackground() {
		
		// create background image
		// Reference: https://www.geeksforgeeks.org/javafx-background-class/		
		bgImgPath = (String) (seasonMap.get(recentMap.get("season")))[6];		
        
		try {
			input = new FileInputStream(bgImgPath);			
	        Image img = new Image(input); 	       
	        BackgroundImage backgroundimage = new BackgroundImage(img,  
	                                         BackgroundRepeat.NO_REPEAT,  
	                                         BackgroundRepeat.NO_REPEAT,  
	                                         BackgroundPosition.DEFAULT,  
	                                            BackgroundSize.DEFAULT); 	       
	        Background background = new Background(backgroundimage);	        
	        vbox.setBackground(background);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
		
	}
	
	public static VBox getGeneral(String status) { //status represents the recent status
		
		String[] statusArr = statusMap.get(status);
		Object[] springArr = seasonMap.get("spring");
		Object[] autumnArr = seasonMap.get("autumn");
		
		vbox = new VBox();
		vbox.setSpacing(20);
		
		
		setBackground();		
		
	
		
		/*
		Start Heading Area
		*/
		
		VBox heading = new VBox();
		heading.setAlignment(Pos.CENTER);
		
		//title
		Label title;
		String titleText = statusArr[0];
		title = new Label(titleText);
		title.getStyleClass().add("title");
		title.setAlignment(Pos.CENTER);
		title.setEffect(new DropShadow(2.0, Color.BLACK));
		
		//desc		
		Label desc = new Label();
		desc.getStyleClass().add("desc");
		desc.setEffect(new DropShadow(2.0, Color.BLACK));
		String descText = null;
		if(status == "Success" || status == "Timeout" || status == "Initial") {
			descText = statusArr[1];
		} else if (status == "Stinged" ) {
			String label = optionMap.get("findHornet");
			descText = (String) findHornetActionMap.get(label)[4];
		} else if (status == "Break") {
			String label = optionMap.get("findHornet");
			descText = (String) findHornetActionMap.get(label)[5];
		}
		desc.setText(descText);
		desc.setWrapText(true);
		
		heading.getChildren().add(title);
		heading.getChildren().add(desc);
		
		/*
		End Heading Area
		*/
		/*
		Start Level Area
		*/
		FlowPane levelArea = new FlowPane();
		levelArea.setAlignment(Pos.CENTER);
		levelArea.setHgap(10);
		
		String[] labelArr = {
				"spring",
				"autumn",
		};
		
		ToggleGroup levelGroup = new ToggleGroup(); 
		
		for(int i=0;i<labelArr.length;i++) {			
			
			String label = labelArr[i];
			Object[] arr = seasonMap.get(label);
			String imgPath = (String) arr[4];
			String tooltipText = (String) arr[5];
			ImageView image;
			try {
				
				//set image
				image = new ImageView(new Image(new FileInputStream(imgPath)));
				image.setFitHeight(100);
				image.setPreserveRatio(true);
				
				//set button
				ToggleButton imgBtn = new ToggleButton();
				imgBtn.getStyleClass().add("button-level");
				imgBtn.setId(label);
				imgBtn.setGraphic(image);
				//capitalize button text
				//source: https://stackoverflow.com/questions/5725892/how-to-capitalize-the-first-letter-of-word-in-a-string-using-java/42024503
				String labelCap = label.substring(0, 1).toUpperCase() + label.substring(1); 
				imgBtn.setText(labelCap);
				imgBtn.setContentDisplay(ContentDisplay.TOP);
				imgBtn.setOnMouseClicked(e->{				 
					 recentMap.put("season", label);
					 setBackground();
					 });				
				
				//set toggle group
				imgBtn.setToggleGroup(levelGroup);
				
				//set Tooltip
				Tooltip tooltip = new Tooltip();
				tooltip.setText(tooltipText);
				tooltip.setMaxWidth(200);
				tooltip.setWrapText(true);				
				
				imgBtn.setTooltip(tooltip);
				
				levelArea.getChildren().add(imgBtn);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		
		String seasonLookUp = "#" + recentMap.get("season");		
		((ToggleButton)levelArea.lookup(seasonLookUp)).setSelected(true);
		
		/*
		End Level Area
		*/
		
		
		/*
		Start button Area
		*/
		FlowPane btnArea = new FlowPane();
		btnArea.setPadding(insets);
		btnArea.setHgap(10);
		btnArea.setAlignment(Pos.CENTER);
		
		Insets btnInsets = new Insets(5,20,5,20);
		
		//start button
		Button btnStart = new Button();
		if(status == "Initial") {
			btnStart.setText("Start");
		} else {
			btnStart.setText("Restart");
		}			
		btnStart.setOnMouseClicked(e ->{
			ChangeScreenController.changeScreen(status, "Playing");
			System.out.print("Start Button Fired");
		});	
		btnStart.getStyleClass().add("primaryBtn");
		btnStart.setPadding(btnInsets);

		
		//exit button
		Button btnExit = new Button("Exit");
		btnExit.setOnMouseClicked(e ->{
				Platform.exit();
			});
		btnExit.setPadding(btnInsets);
		
		btnArea.getChildren().addAll(btnStart,btnExit);			
		/*
		End button Area
		*/

		
		vbox.getChildren().add(heading);
		vbox.getChildren().add(levelArea);
		vbox.getChildren().add(btnArea);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10,100,10,100));
		vbox.setId("general-view");		
		
		 
		/*
		application.Main.root.setStyle("-fx-background-image: url('" + bgImgPath + "'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");
		*/
		return vbox;
	}

}
