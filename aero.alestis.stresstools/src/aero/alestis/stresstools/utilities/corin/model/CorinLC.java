package aero.alestis.stresstools.utilities.corin.model;

import java.util.HashMap;
import java.util.Map;

public class CorinLC {
	private String coringLCName;
	private Map<String,CorinRF> mapOfCorinRF;
	
	public CorinLC(){
		this.setMapOfCorinRF(new HashMap<String, CorinRF>());
		
	}
	public CorinLC(String name){
		this.setCoringLCName(name);
		this.setMapOfCorinRF(new HashMap<String, CorinRF>());
	}
	
	public String getCoringLCName() {
		return coringLCName;
	}
	
	public void setCoringLCName(String coringLCName) {
		this.coringLCName = coringLCName;
	}

	public Map<String,CorinRF> getMapOfCorinRF() {
		return mapOfCorinRF;
	}

	public void setMapOfCorinRF(Map<String,CorinRF> mapOfCorinRF) {
		this.mapOfCorinRF = mapOfCorinRF;
	}
	public void addCorinRF(CorinRF corinRF){
		if(this.mapOfCorinRF.containsKey(corinRF.getElement_ID())){
			CorinRF oldCorinRF = this.mapOfCorinRF.get(corinRF.getElement_ID());
			if(oldCorinRF.getMin_RF() > corinRF.getMin_RF()) this.mapOfCorinRF.put(corinRF.getElement_ID(), corinRF);
			
		}
		else{
			this.mapOfCorinRF.put(corinRF.getElement_ID(), corinRF);
		}
	}
	

}
