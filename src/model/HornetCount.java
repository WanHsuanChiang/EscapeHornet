package model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javafx.scene.Node;



public class HornetCount  {
	
	private static Integer columns = model.GlobalVariableModel.recentHornetParam;
	private static Integer totalComb = model.GlobalVariableModel.totalComb;
	private static Integer HornetCount = model.GlobalVariableModel.totalHornet;
	private static HashMap order = model.GlobalVariableModel.order;
	
	/*
	public static List<String> getHornetList()  {
		
		
		
		/*
		 * randomly generate integer
		 * Reference: https://stackoverflow.com/a/8115744
		 */
	/*
		List<Integer> list = ThreadLocalRandom.current()
		        .ints(1, totalComb)
		        .boxed()
		        .distinct()
		        .limit(HornetCount)
		        .collect(Collectors.toList());
		
		List<String> strList = list.stream().map(Object::toString)
                .collect(Collectors.toList());
		
        return strList;
        
	}
	 */
	
	public static List<Integer> getHornetIntList()  {		
		
		/*
		 * randomly generate integer
		 * Reference: https://stackoverflow.com/a/8115744
		 */
		List<Integer> list = ThreadLocalRandom.current()
		        .ints(1, totalComb)
		        .boxed()
		        .distinct()
		        .limit(HornetCount)
		        .collect(Collectors.toList());		
		
        return list;
        
	}
	
	public static Integer getOrder(int r, int c) {
		
		Integer result = (r % 2 == 0) ? (c+1)+((columns*2-1)*(r/2)) : (c+1)+((columns*2-1)*((int)r/2) )+columns ;
		return result;
	}
	
	
	private static Integer getValue(int o){
		
		/* detect whether the button has hornet or not.
		 * input: the order(index) of the button
		 * output: whether it has hornet or not. 1 represents has hornet.
		*/
		
		Integer result = (boolean) (order.get(o))? 1:0;
		return result;
		
	}
	

	public static Integer getNearbyHornet(Node btn, Integer r, Integer c, Integer btnOrder, Boolean hasHornet) {
		Integer nearbyHornet = null;
		Integer preOrder,preValue;
		Integer nexOrder,nexValue;
		Integer upperLeftOrder,upperLeftValue,upperLeftColumn;
		Integer upperRightOrder,upperRightValue,upperRightColumn;
		Integer bottomLeftOrder,bottomLeftValue,bottomLeftColumn;
		Integer bottomRightOrder,bottomRightValue,bottomRightColumn;
		
		Boolean rowEven = (r % 2 == 0)? true:false;
		
		//if users clicks on the button without hornet
		
		//determine the left button of target button
		preOrder = btnOrder-1;
		preValue = (c == 0 ) ? 0 : getValue(preOrder);
		//determine the right button of target button
		nexOrder = btnOrder+1;
		if(rowEven) {
			nexValue = (c+1 == columns) ? 0 : getValue(nexOrder);
		}else {
			nexValue = (c+1 == columns-1) ? 0 : getValue(nexOrder);
		}				
		//determine the upper left button of target button
		upperLeftColumn = (rowEven)? c-1 : c;
		upperLeftOrder = model.HornetCount.getOrder(r-1, upperLeftColumn);
		if (r == 0) {
			upperLeftValue = 0;
		} else if (rowEven && c == 0) {
			upperLeftValue = 0;
		} else {
			upperLeftValue = getValue(upperLeftOrder);
		}
		//determine the upper right button of target button
		upperRightColumn = (rowEven)? c : c+1;
		upperRightOrder = model.HornetCount.getOrder(r-1, upperRightColumn);
		if (r == 0) {
			upperRightValue = 0;
		} else if (rowEven && c+1 == columns) {
			upperRightValue = 0;
		} else {
			upperRightValue = getValue(upperRightOrder);
		}
		//determine the bottom left button of target button
		bottomLeftColumn = (rowEven)? c-1 : c;
		bottomLeftOrder = model.HornetCount.getOrder(r+1, bottomLeftColumn);
		if (r+1 == columns ) {
			bottomLeftValue = 0;
		} else if (rowEven && c == 0) {
			bottomLeftValue = 0;
		} else {
			bottomLeftValue = getValue(bottomLeftOrder);
		}
		//determine the bottom right button of target button
		bottomRightColumn = (rowEven)? c: c+1;
		bottomRightOrder = model.HornetCount.getOrder(r+1, bottomRightColumn);
		if (r+1 == columns ) {
			bottomRightValue = 0;
		} else if (r % 2 == 0 && c+1 == columns) {
			bottomRightValue = 0;
		} else {
			bottomRightValue = getValue(bottomRightOrder);
		}
		
		nearbyHornet = preValue + nexValue + upperLeftValue + upperRightValue + bottomLeftValue + bottomRightValue;		
		
		
		return nearbyHornet;
	}
	
}

