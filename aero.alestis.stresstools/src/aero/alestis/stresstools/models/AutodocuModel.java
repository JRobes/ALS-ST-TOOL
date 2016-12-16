package aero.alestis.stresstools.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="AutodocuModelTest")
public class AutodocuModel extends ModelObject {
	private boolean pitchingTables;
	private boolean panelTables;
	private boolean metalStructureTables;
	private boolean cfrpStructureTables;
	private String pathWithResults;
	private String pathToStoreResults;
	private String structurePNRelationFile;
	private String panelPNRelationFile;
	private String excludedElementsFile;
	private String excelWithCorrectedTables;
	private String sectionDataFileForPanel;
	private String filtersToIncludeInText;
	private String filtersToExcludeInText;
	
	public boolean isPitchingTables() {
		return pitchingTables;
	}
	
	@XmlElement
	public void setPitchingTables(boolean pitchingTables) {
		this.pitchingTables = pitchingTables;
	}
	public boolean isPanelTables() {
		return panelTables;
	}
	
	@XmlElement
	public void setPanelTables(boolean panelTables) {
		this.panelTables = panelTables;
	}
	public boolean isMetalStructureTables() {
		return metalStructureTables;
	}
	
	@XmlElement
	public void setMetalStructureTables(boolean metalStructureTables) {
		this.metalStructureTables = metalStructureTables;
	}
	public boolean isCfrpStructureTables() {
		return cfrpStructureTables;
	}
	
	@XmlElement
	public void setCfrpStructureTables(boolean cfrpStructureTables) {
		this.cfrpStructureTables = cfrpStructureTables;
	}
	public String getPathWithResults() {
		return pathWithResults;
	}
	
	@XmlElement
	public void setPathWithResults(String pathWithResults) {
		this.pathWithResults = pathWithResults;
	}
	public String getPathToStoreResults() {
		return pathToStoreResults;
	}
	
	@XmlElement
	public void setPathToStoreResults(String pathToStoreResults) {
		this.pathToStoreResults = pathToStoreResults;
	}
	public String getStructurePNRelationFile() {
		return structurePNRelationFile;
	}
	
	@XmlElement
	public void setStructurePNRelationFile(String structurePNRelationFile) {
		this.structurePNRelationFile = structurePNRelationFile;
	}
	public String getPanelPNRelationFile() {
		return panelPNRelationFile;
	}
	
	@XmlElement
	public void setPanelPNRelationFile(String panelPNRelationFile) {
		this.panelPNRelationFile = panelPNRelationFile;
	}
	public String getExcludedElementsFile() {
		return excludedElementsFile;
	}
	
	@XmlElement
	public void setExcludedElementsFile(String excludedElementsFile) {
		this.excludedElementsFile = excludedElementsFile;
	}
	public String getExcelWithCorrectedTables() {
		return excelWithCorrectedTables;
	}
	
	@XmlElement
	public void setExcelWithCorrectedTables(String excelWithCorrectedTables) {
		this.excelWithCorrectedTables = excelWithCorrectedTables;
	}
	public String getSectionDataFileForPanel() {
		return sectionDataFileForPanel;
	}
	
	@XmlElement
	public void setSectionDataFileForPanel(String sectionDataFileForPanel) {
		this.sectionDataFileForPanel = sectionDataFileForPanel;
	}
	public String getFiltersToIncludeInText() {
		return filtersToIncludeInText;
	}
	
	@XmlElement
	public void setFiltersToIncludeInText(String filtersToIncludeInText) {
		this.filtersToIncludeInText = filtersToIncludeInText;
	}
	public String getFiltersToExcludeInText() {
		return filtersToExcludeInText;
	}
	
	@XmlElement
	public void setFiltersToExcludeInText(String filtersToExcludeInText) {
		this.filtersToExcludeInText = filtersToExcludeInText;
	}
	

}
