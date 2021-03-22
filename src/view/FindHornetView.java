package view;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import controller.OptionController;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class FindHornetView extends OptionController {	
	
	public static VBox content;	
	//public static ScaleTransition st;
	
	public static Pane getFindHornetView() throws Exception {
		
		HashMap<String,Object[]> map = model.GlobalVariableModel.findHornetActionMap;
		
		ArrayList<Object> labelArr = new ArrayList<Object>();
		ArrayList<Object> descArr = new ArrayList<Object>();
		ArrayList<Object> imgArr = new ArrayList<Object>();
		ArrayList<Object> actionArr = new ArrayList<Object>();
		
		for (Object[] i : map.values()) {
			labelArr.add(i[0]);
			descArr.add(i[1]);
			imgArr.add(i[2]);
			actionArr.add(i[6]);
		}
		
		content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setSpacing(10);
		
		
		
		
		/*
		Start Heading Area
		*/
		
		VBox heading = new VBox();
		heading.setAlignment(Pos.CENTER);
		heading.setId("heading");
		
		//title
		Label title = new Label("The hornets are going to attack you");
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		//desc
		Label desc = new Label("Try to do something to avoid being stung.");
		desc.setAlignment(Pos.CENTER);
		
		heading.getChildren().add(title);
		heading.getChildren().add(desc);
		
		/*
		End Heading Area
		*/
		
		/*
		Start OptionArea
		*/
		FlowPane optionArea = new FlowPane();
		optionArea.setAlignment(Pos.CENTER);
		optionArea.setPadding(insets);		
		
		
		for(int i = 0; i< labelArr.size(); i++){
			
			String label = (String) labelArr.get(i);
			
			//image and text
			VBox option = new VBox();
			option.setAlignment(Pos.CENTER);
			
			ImageView image = new ImageView(new Image(new FileInputStream((String) imgArr.get(i))));
			image.setFitHeight(100);
			image.setPreserveRatio(true);
			image.getStyleClass().add("image");	
			/*
			ToggleButton imgBtn = new ToggleButton();
			imgBtn.setPadding(new Insets(20,20,20,20));
			String btnText = new String((String) descArr.get(i));
			imgBtn.setGraphic(image);
			imgBtn.setText(btnText);
			imgBtn.setContentDisplay(ContentDisplay.TOP);
			*/
			Label optionText = new Label(((String) descArr.get(i)));
			optionText.setAlignment(Pos.CENTER);
			
			/*
			st = new ScaleTransition(Duration.millis(3000), image); 
			st.setFromX(1); // original x
			st.setFromY(1); // original y
			st.setToX(15); // final x is 15 times the original
			st.setToY(15); // final y is 15 times the original
			*/
			option.getChildren().add(image);
			option.getChildren().add(optionText);
			
			//action text for interaction			
			String actionText = new String((String) actionArr.get(i));
			Label actionLabel = new Label(actionText);
			actionLabel.getStyleClass().add("action-text");
			actionLabel.setMaxWidth(150);// hard code
			actionLabel.setWrapText(true);
			actionLabel.setTextAlignment(TextAlignment.JUSTIFY);		
			
			
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.TOP_CENTER);
			vbox.getStyleClass().add("option-find");
			vbox.getStyleClass().add(label);
			vbox.getProperties().put("label",label);
			vbox.setId(label);
			//vbox.getProperties().put(optionLabel[i], value);
			
			vbox.getChildren().add(option);	
			vbox.getChildren().add(actionLabel);
			
					
			vbox.addEventFilter(MouseEvent.ANY,new controller.OptionController());			
			
			optionArea.getChildren().add(vbox);			

		}		
		
		/*
		End OptionArea
		*/
		
		content.getChildren().add(heading);		
		content.getChildren().add(optionArea);
		
		
		content.addEventFilter(MouseEvent.MOUSE_PRESSED, new OptionController());		
		application.Main.scene.addEventFilter(KeyEvent.KEY_PRESSED,new OptionController());
				
		content.setId("find-hornet-view");
		
		return content;	
		
	}	
	

}
