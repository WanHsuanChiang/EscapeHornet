package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StingedView {	
	
	public static Pane getStingedView() throws Exception  {
		
		HashMap<String,Object[]> map = model.GlobalVariableModel.stingedActionMap;		
		
		
		
		ArrayList<Object> labelArr = new ArrayList<Object>();		
		ArrayList<Object> descArr = new ArrayList<Object>();
		ArrayList<Object> imgArr = new ArrayList<Object>();
		
		for (Object[] i : map.values()) {
			labelArr.add(i[0]);
			descArr.add(i[1]);
			imgArr.add(i[2]);
		}
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		
		FlowPane optionArea = new FlowPane();
		optionArea.setAlignment(Pos.CENTER);
		
		Label desc = new Label("You are stinged! What you should do next?");
		desc.setAlignment(Pos.CENTER);
		
		
		for(int i = 0; i< labelArr.size(); i++){			
			
			
			ImageView image = new ImageView(new Image(new FileInputStream((String) imgArr.get(i))));
			
			
			Label label = new Label((String) labelArr.get(i));
			desc.setAlignment(Pos.CENTER);
			
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			vbox.getStyleClass().add("option-stinged");
			vbox.getStyleClass().add((String) labelArr.get(i));	
			//vbox.getProperties().put(optionLabel[i], value);
			
			vbox.getChildren().add(image);			
			
			vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new controller.StingedOptionController());
			vbox.setOnMouseEntered(e->{				
				vbox.getChildren().add(label);
			});
			vbox.setOnMouseExited(e->{
				vbox.getChildren().remove(label);
			});
			
			optionArea.getChildren().add(vbox);			

		}		
		
				
		content.getChildren().add(desc);
		content.getChildren().add(optionArea);
		content.setId("stinged-view");
		
		return content;
	}
	

}
