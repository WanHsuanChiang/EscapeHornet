package model;

//import java.util.HashMap;

public interface ProbabilityModel {	
	
	/*
	public static void main(String args[]) {
		
		HashMap<String,Float> surviveProb = new HashMap<String, Float>();
		surviveProb.put("doctor",(float) 0.05);
		surviveProb.put("drug",(float) 0.95);
		
	}
	*/
	
	public static Boolean getResult(double p) {
		
		//return result by probability
		Boolean result = ( Math.random() <= p)? true : false;			
		return result;
	}
	
	/*
	public static Boolean hasSurvive(String option){
		
		Boolean result = null;	
		
		if(option == "doctor") {result = getResult(0.05);}
		if(option == "drug") {result = getResult(0.95);}
		
		return result;
	}
	
	public static Boolean hasStinged(String option){
		
		Boolean result = null;
		
		if(option == "clothes") {result = getResult(1);}
		if(option == "scream") {result = getResult(1);}
		if(option == "deet") {result = getResult(0.2);}
		if(option == "squat") {result = getResult(1);}
		
		return result;
	}
	*/

}
