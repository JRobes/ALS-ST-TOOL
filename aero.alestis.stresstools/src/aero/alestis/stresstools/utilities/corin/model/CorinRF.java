package aero.alestis.stresstools.utilities.corin.model;

public class CorinRF {
	private String Element_ID;
	private double Y_S_RF;
	private double K_S_RF;
	private double min_RF;
	public CorinRF(){
		
	}
	public String getElement_ID() {
		return Element_ID;
	}
	public void setElement_ID(String element_ID) {
		Element_ID = element_ID;
	}
	public double getY_S_RF() {
		return Y_S_RF;
	}
	public void setY_S_RF(double y_S_RF) {
		Y_S_RF = y_S_RF;
	}
	public double getK_S_RF() {
		return K_S_RF;
	}
	public void setK_S_RF(double k_S_RF) {
		K_S_RF = k_S_RF;
	}
	public double getMin_RF() {
		return min_RF;
	}
	public void setMin_RF(double min_RF) {
		this.min_RF = min_RF;
	}
	

}
