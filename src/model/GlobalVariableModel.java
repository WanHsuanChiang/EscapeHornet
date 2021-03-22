package model;

import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.geometry.Insets;

public class GlobalVariableModel {
	
	//public static TreeMap<String,Object[]> levelMap = new TreeMap<String, Object[]>();
	public static HashMap<String,Object[]> findHornetActionMap = new HashMap<String, Object[]>();
	public static HashMap<String,Object[]> stingedActionMap = new HashMap<String, Object[]>();
	public static HashMap<String,Object[]> seasonMap;
	public static HashMap<Integer, Boolean> order = new HashMap<Integer, Boolean>(); //order,hasHornet
	public static HashMap<String,Integer> seasonColMap = new HashMap<String,Integer>();
	public static HashMap<String,Integer> seasonTimeMap = new HashMap<String,Integer>();
	public static HashMap<String, String> recentMap = new HashMap<String, String>();
	public static HashMap<String, String> imgMap;
	public static HashMap<String, String> optionMap  = new HashMap<String, String>(); // <"findHornet", {Find Hornet Option}>
	public static HashMap<String, Boolean> resultMap = new HashMap<String, Boolean>(); // <"findHornet", hasStinged>
	public static HashMap<String, String[]> statusMap;
	
	public static Integer[] screenSize = new Integer[2];
	private static String defaultImg = "resources/hornet.png";
		
	
	//public static Integer duration = 60;
	//public static Integer level = 1; //recent level
	//public static String recentSeason = "spring"; //recent level
	//public static String recentStatus;//Initial, Playing,Find Hornet, Stinged, Timeout, Failed, Success, Escaped,Survive
	
	//public static final String status = "";
		
	//public static HashMap<String,ArrayList<Object>> levelMap = new HashMap<String, ArrayList<Object>>();
	
	static Integer springCol = 10;
	static Integer autumnCol = 15;
	static Integer springTime = 60;
	static Integer autumnTime = 120;
	
	
	/*
	
	Shows the array related to season setting.
	
	Index:
	0: the label
	1: the level in Integer
	2: the column and rows for honeycomb
	3: the time duration
	4: the image icon
	5: the description/tooltip
	6: background image path
	
	*/	
	
	
	private static Object[] springSetting = {
			"spring",
			(Integer)1,
			(Integer)10,//the column and row of honeycomb for level spring.
			(Integer)60,//the game duration for level spring in seconds.
			"resources/spring.png",
			"Hornets are. less aggressive in spring because they are busy building their nests.",
			"resources/hornet-spring.jpg",// Source: https://www.flickr.com/photos/awei750/15857635811
	};
	
	private static Object[] autumnSetting = {
			"autumn",
			(Integer)2,
			(Integer)15,//the column and row of honeycomb for level autumn.
			(Integer)120,//the game duration for level autumn in seconds.
			"resources/autumn.png",
			"Hornets are more aggressive in autumn due to reproduction. Be more careful!",
			"resources/hornet-autumn.jpg",// Source: https://pixabay.com/photos/hornets-larvae-brood-mortality-3550483/
	};
	
	
	
	public static Integer recentHornetParam;
	public static Integer totalComb;  
	public static Integer totalHornet ;
	public static Integer cellSize = 40;
	public static Insets insets;
	
	
	
	/*
	
	Shows the array related to actions after finding the hornet.
	
	Index:
	0: the label
	1: the text shown
	2: the image resource
	3: the probability to get stinged
	4: the string for hasStinged = true
	5: the string for hasStinged = false
	6: the action text
	
	*/
		
	private static Object[] fClothes = {
			"clothes",
			"Wave Clothes",
			"resources/jacket.png",
			(float)1,
			"Your should not wave your clothes because hornets believes this is a signal to attack them. Therefore, they will become more aggressive.",
			"Waving clothes is a bad action while facing hornets because they can be irrirated. You are lucky that you didn't get stung.",
			"Move your mouse to wave clothes.",
	};
	
	private static Object[] fScream = {
			"scream",
			"Scream",
			"resources/scream.png",
			(float)1,
			"Hornets can be irritated by voice. Never try to make noise while seeing hornets.",
			"Screaming is a bad action while facing hornets because they can be irritated by voice. You are lucky that you didn't get stung.",
			"Use your microphone to scream."
	};
	
	private static Object[] fDeet = {
			"deet",
			"Apply DEET",
			"resources/spray-bottle.png",
			(float)0.2,			
			"Since spraying DEET is the right solution, you still have chance to be stung. This is life. This is Wild. Respect.",
			"Hornets do not like this chemicals, so appling DEET is proved to be the best solution while facing hornets. However, you still get chance to be stung.",
			"Long press the spray bottele to spray DEET."
	};
	
	private static Object[] fSquat = {
			"squat",
			"Squat Down",
			"resources/squat.png",//Squat by Gan Khoon Lay from the Noun Project
			(float)1,
			"People get attacked because they are in hornets' colonies, so it's a bad idea to stay there.",
			"Hornets attack animals in their colonies. You should quickly and silently leave away. Don't stay! You are lucky this time.",
			"Press \"Down\" on keyboard to squat down."
	};
	
	
	
	/*
	Shows the array related to actions after stinged by hornet.
	
	Index:
	0: the label
	1: the text shown
	2: the image resource
	3: the probability to survive
	
	*/
	
	private static Object[] sDoctor = {
			"doctor",
			"Find Doctors",
			"resources/doctor.png",
			(float)0.05,
	};
	
	private static Object[] sDrug = {
			"drug",
			"Take Antihistamine",
			"resources/syringe-drug.png",
			(float)0.95,
	};
		
	public static Integer getTotalComb(Integer recentHornetParam) {
		
		Integer totalComb = (recentHornetParam % 2 == 0)? (recentHornetParam*2-1)*(recentHornetParam/2) : recentHornetParam*recentHornetParam-((int)recentHornetParam/2+1) ;	
		return totalComb;
	}
	
	public static Integer getTotalHornet(Integer recentHornetParam) {
		
		Integer totalComb = (recentHornetParam % 2 == 0)? (recentHornetParam*2-1)*(recentHornetParam/2) : recentHornetParam*recentHornetParam-((int)recentHornetParam/2+1) ;	
		Integer totalHornet = (int)totalComb/10;
		return totalHornet;
	}
	

	public static void Setting() {
		
		screenSize[0] = 800;//width
		screenSize[1] = 600;//height
		
		insets = new Insets(10,10,10,10);
		
		//findHornetActionMap.put((String)fClothes[0], fClothes);
		//findHornetActionMap.put((String)fScream[0], fScream);
		findHornetActionMap.put((String)fDeet[0], fDeet);
		findHornetActionMap.put((String)fSquat[0], fSquat);
		stingedActionMap.put((String)sDoctor[0], sDoctor);
		stingedActionMap.put((String)sDrug[0], sDrug);
		
		
		seasonColMap.put("spring",springCol);
		seasonColMap.put("autumn",autumnCol);
		seasonTimeMap.put("spring",springTime);
		seasonTimeMap.put("autumn",autumnTime);
		
		recentMap.put("season", "spring");
		recentMap.put("status","Initial");
		//StringProperty recentWatched = new SimpleStringProperty(recentMap.get("progess"));
		//recentWatched.addListener(new controller.StatusChangeListener());
		
		recentHornetParam = seasonColMap.get(recentMap.get("season"));
		totalComb = getTotalComb(recentHornetParam);
		totalHornet = getTotalHornet(recentHornetParam);
		
		
		imgMap = new HashMap<String, String>();
		imgMap.put("clothes", "resources/jacket.png");
		imgMap.put("scream", "resources/scream.png");
		imgMap.put("deet", "resources/spray-bottle.png");
		imgMap.put("squat", "resources/squat.png");
		imgMap.put("doctor", "resources/doctor.png");
		imgMap.put("drug", "resources/syringe-drug.png");
		imgMap.put("spring", "resources/spring.png");
		imgMap.put("autumn", "resources/autumn.png");
		
		String[] initialArr = {
				"Avoid Hornet",
				"Check the honeycomb to see if there's hornets in the wild."
		};
		String[] stingedArr = {
				"You are stung by hornets!",
				""
		};
		String[] breakArr = {
				"You successfully escaped from hornets!",
				""
		};
		String[] successArr = {
				"Success!",
				"Amazing! You complete your adventure."
		};
		String[] timeoutArr = {
				"Timeout!",
				"You run out of your time. Restart the game."
		};
		
		
		statusMap = new HashMap<String, String[]>();
		statusMap.put("Initial", initialArr);
		statusMap.put("Stinged", stingedArr);
		statusMap.put("Break", breakArr);
		statusMap.put("Success", successArr);
		statusMap.put("Timeout", timeoutArr);
		
		seasonMap = new HashMap<String, Object[]>();
		seasonMap.put("spring", springSetting);
		seasonMap.put("autumn", autumnSetting);
		
	}




	
	

}
