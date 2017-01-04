package aero.alestis.stresstools.utilities.corin.model;

import java.util.ArrayList;
import java.util.List;

public class CorinLC {
	private String coringLCName;
	private List<CorinRF> listofCorinRF;
	
	public CorinLC(){
		this.setListofCorinRF(new ArrayList<CorinRF>());
	}
	
	public String getCoringLCName() {
		return coringLCName;
	}
	public void setCoringLCName(String coringLCName) {
		this.coringLCName = coringLCName;
	}

	public List<CorinRF> getListofCorinRF() {
		return listofCorinRF;
	}

	private void setListofCorinRF(List<CorinRF> listofCorinRF) {
		this.listofCorinRF = listofCorinRF;
	}
	

}
