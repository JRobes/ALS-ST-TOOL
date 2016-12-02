package aero.alestis.stresstools.models;

public class AutodocuModel extends ModelObject {
	private boolean pitchingTables;
	private boolean panelTables;
	private boolean metalStructureTables;
	private boolean cfrpStructureTables;
	public boolean isPitchingTables() {
		return pitchingTables;
	}
	public void setPitchingTables(boolean pitchingTables) {
		this.pitchingTables = pitchingTables;
	}
	public boolean isPanelTables() {
		return panelTables;
	}
	public void setPanelTables(boolean panelTables) {
		this.panelTables = panelTables;
	}
	public boolean isMetalStructureTables() {
		return metalStructureTables;
	}
	public void setMetalStructureTables(boolean metalStructureTables) {
		this.metalStructureTables = metalStructureTables;
	}
	public boolean isCfrpStructureTables() {
		return cfrpStructureTables;
	}
	public void setCfrpStructureTables(boolean cfrpStructureTables) {
		this.cfrpStructureTables = cfrpStructureTables;
	}
}
