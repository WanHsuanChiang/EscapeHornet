package application;

import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application   {
	
	public static Scene scene;
	public static Pane root;
	public static Stage primaryStage;


	public static void main(String[] args){
		
		System.out.println("We are starting ...");
		launch(args);
		System.out.println("Are we stopping?");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root = new StackPane();
		scene = new Scene(root);
		
		/*
		
		HashMap<String, Object[]> levelMap = model.GlobalVariableModel.levelMap;
		Object[] autumnList = model.GlobalVariableModel.autumnSetting;
		Object[] springList = model.GlobalVariableModel.springSetting;
		levelMap.put("autumn",autumnList);
		levelMap.put("spring",springList);	
		
		/*
		Object[] test = levelMap.get("autumn");
		String test1 = (String) test[0];
		System.out.println(test1);
		*/
		/*
		HashMap<String, Object[]> fMap = model.GlobalVariableModel.findHornetActionMap;
		Object[] f0 = model.GlobalVariableModel.findHornetActionClothes;
		Object[] f1 = model.GlobalVariableModel.findHornetActionScream;		
		Object[] f2 = model.GlobalVariableModel.findHornetActionDeet;		
		Object[] f3 = model.GlobalVariableModel.findHornetActionSquat;
		fMap.put((String)f0[0], f0);
		
		*/
		
		
		
		model.GlobalVariableModel.Setting();
		/*
		StringProperty recentWatched = new SimpleStringProperty(model.GlobalVariableModel.recentMap.get("status"));
		recentWatched.addListener(new controller.StatusChangeListener());
		System.out.print("added");
		
		model.GlobalVariableModel.recentMap.put("status", "Playing");
		System.out.print(model.GlobalVariableModel.recentMap.get("status"));
		*/
		Integer[] screenSize = model.GlobalVariableModel.screenSize;
		root.setPrefSize(screenSize[0],screenSize[1]);
		
		Pane generalView = view.GeneralView.getGeneral(model.GlobalVariableModel.recentMap.get("status"));
		root.getChildren().add(generalView);
		
			
		scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
		
						
			
		
		
		
		
		
		
		
		
		primaryStage.setTitle("Avoid Hornet");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
