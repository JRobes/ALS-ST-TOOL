 
package aero.alestis.stresstools.autodocu;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
//import java.nio.file.Path;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.PersistState;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import aero.alestis.stresstools.events.StressToolsEventConstants;
import aero.alestis.stresstools.models.AutodocuModel;

public class AutodocuForm {
	private FormToolkit toolkit;
	private ScrolledForm form;
	private java.nio.file.Path partPath;
	private static AutodocuModel modelo;
	@Inject MDirtyable dirty;
	@Inject MPart parte;
	@Inject IEventBroker broker;
	@Inject EPartService partService;
	
	@Inject
	public AutodocuForm() {
		//if((Path)parte.getTransientData().get("File Path")!= null){
			//System.out.println(partPath.toString());

    }
		
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		boolean autodispose = false;
		System.out.println("\nAutodocuForm:\tpostConstruct\t" +parte.getLabel());
		//parte.getTags().add(EPartService.REMOVE_ON_HIDE_TAG);
		//parte.setElementId("EEEELEMENTOTTTTTTTT");
		//System.out.println("LAS TAGS:\t"+parte.getTags().toString());
		//System.out.println("EL container DAta:\t"+parte.getContainerData());
		if(parte.getTransientData().get("File Path") == null){

			partPath = Paths.get(parte.getElementId());
			parte.getTransientData().put("File Path", parte.getElementId());
			if(Files.exists(partPath, LinkOption.NOFOLLOW_LINKS)){
				System.out.println("EL ARCHIVO EXISTE$$$$$$$$$$$$$$$$$$$$$$$$");
				//System.out.println("El icon URI:\t"+parte.getIconURI());
				//System.out.println("trasient data\t"+ parte.getTransientData().toString());

			}
			else{
				autodispose= true;
				String tempDir = System.getProperty("java.io.tmpdir");
				System.out.println("DIRECTIORIO TEMPORAL:\t"+tempDir);
				//Bundle bundle = FrameworkUtil.getBundle(this.getClass());
				//URL url22 = FileLocator.find(bundle, new Path("icons/lrun_obj.png"), null);
				//ImageDescriptor imageDescriptorll2 = ImageDescriptor.createFromURL(url22);
				//System.out.println("trasient data\t"+ parte.getTransientData().toString());
				//partService.findPart(parte.getElementId()).setIconURI("platform:/plugin/aero.alestis.stresstools/icons/lrun_obj.png");;
				//partService.hidePart(partService.findPart(parte.getElementId()), true);
				//System.out.println("El icon URI:\t"+parte.getIconURI());
				//parte.getTransientData().remove(IPresentationEngine.OVERRIDE_ICON_IMAGE_KEY);
				//parte.setIconURI("platform:/plugin/aero.alestis.stresstools/icons/lrun_obj.png");
				
			}
	
		}
		else{
			
			String cadena = (String)parte.getTransientData().get("File Path");
			System.out.println("LA PUTA CADENA\t"+ cadena);
			partPath = Paths.get(cadena);
			parte.setLabel(partPath.getFileName().toString());
			parte.setElementId(partPath.toString());
			

		}
		
		 
		 if(autodispose){
				partService.hidePart(partService.findPart(parte.getElementId()), true);

		 }
		 else{
			 createSWTControls(parent);
			 broker.post(StressToolsEventConstants.FILE_NEW_AUTODOCU_PART, parte.getLabel());
 
		 }
	 
	}





	/**
	 * @param parent
	 */
	private void createSWTControls(Composite parent) {
		//parte.getTags().add(EPartService.REMOVE_ON_HIDE_TAG);
		 toolkit = new FormToolkit(parent.getDisplay());
		 form = toolkit.createScrolledForm(parent);
		 
		 toolkit.decorateFormHeading(form.getForm());
		 
		 IToolBarManager toolBarManager =form.getToolBarManager();
		 
		 Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		 URL url = FileLocator.find(bundle, new Path("icons/lrun_obj.png"), null);
		 ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action runButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			        // Perform action
			    }
		 };
		 url = FileLocator.find(bundle, new Path("icons/import_wiz.gif"), null);
		 imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action importButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			        // Perform action
			    }
		 };
		 url = FileLocator.find(bundle, new Path("icons/export_wiz.gif"), null);
		 imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action exportButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			        // Perform action
			    }
		 }; 
		 exportButtonAction.setToolTipText("Export configuration");
		 importButtonAction.setToolTipText("Import configuration");
		 runButtonAction.setToolTipText("Execute");
		 toolBarManager.add(exportButtonAction);
		 toolBarManager.add(importButtonAction);
		 toolBarManager.add(runButtonAction);
		 form.getToolBarManager().update(true);
		 form.setText("AUTODOCU v2");
		 
		 TableWrapLayout tableWraplayout = new TableWrapLayout();
		 //gridLayout.makeColumnsEqualWidth=true;
		 tableWraplayout.topMargin=20;
		 tableWraplayout.bottomMargin=20;
		 tableWraplayout.numColumns = 2;
		 //form.getb
		 form.getBody().setLayout(tableWraplayout);
		 TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		 
		 td2.colspan = 2;
	
		 Section sectionAnalysisType = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);

		 sectionAnalysisType.setLayoutData(td2);
		 sectionAnalysisType.setText("Analysis type");
		 sectionAnalysisType.setDescription("Select the analysis to perform\n");
		 Composite sectionClient = toolkit.createComposite(sectionAnalysisType);

		 FillLayout fillLayout = new FillLayout();
		 fillLayout.marginHeight=15;
		 fillLayout.type = SWT.HORIZONTAL;
		 sectionClient.setLayout(fillLayout);
		 
		 Button pitchingTablesRadioButton = toolkit.createButton(sectionClient, "Pitching tables", SWT.RADIO);
		 pitchingTablesRadioButton.setSelection(true);
		 Button panelTablesRadioButton = toolkit.createButton(sectionClient, "Panel tables", SWT.RADIO);
		 Button metalStructTablesRadioButton = toolkit.createButton(sectionClient, "Metal structure tables", SWT.RADIO);
		 Button cfrpStructTablesRadioButton = toolkit.createButton(sectionClient, "CFRP structure tables", SWT.RADIO);

		 sectionAnalysisType.setClient(sectionClient);
		 
		 Section sectionPaths = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
		 Section sectionFilters = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
	 		 
		 td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		 td2.colspan = 1;
		 sectionPaths.setLayoutData(td2);

		 td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		 td2.colspan = 1;
		 sectionFilters.setLayoutData(td2);
		
		 sectionPaths.setText("Paths");
		 sectionFilters.setText("Filters");

		 
		 Composite sectionPathsClient = toolkit.createComposite(sectionPaths);
		 Composite sectionFiltersClient = toolkit.createComposite(sectionFilters);

		 //FillLayout fillLayout2 = new FillLayout(SWT.VERTICAL);
		 GridLayout gridLayoutSectionPaths = new GridLayout(2, false);
		 //fillLayout2.marginHeight=5;
		 //fillLayout2.spacing = 30;
		 GridData gd = new GridData();
		 gd.horizontalSpan = 2;
		 
		 sectionPathsClient.setLayout(gridLayoutSectionPaths);
		 sectionPaths.setClient(sectionPathsClient);
		 
		 
		 Label label = toolkit.createLabel(sectionPathsClient, "Path with results:");
		 label.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text pathWithResultsText = toolkit.createText(sectionPathsClient, "");
		 pathWithResultsText.setLayoutData(gd);
		 Button pathWithResultsButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);
		
		 Label label2 = toolkit.createLabel(sectionPathsClient, "Path to store results:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label2.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text pathToStoreResultsText = toolkit.createText(sectionPathsClient, "");
		 pathToStoreResultsText.setLayoutData(gd);
		 Button pathToStoreResultsButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 
		 
		 Label label3 = toolkit.createLabel(sectionPathsClient, "Structure P/N relation file:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label3.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text pathStructurePNText = toolkit.createText(sectionPathsClient, "");
		 pathStructurePNText.setLayoutData(gd);
		 Button pathStructurePNButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 
	
		 Label label4 = toolkit.createLabel(sectionPathsClient, "Panel P/N relation file:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label4.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text pathPanelPNText = toolkit.createText(sectionPathsClient, "");
		 pathPanelPNText.setLayoutData(gd);
		 Button pathPanelPNButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 
		 	 
		 Label label5 = toolkit.createLabel(sectionPathsClient, "Excluded elements file:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label5.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text excludedElementsFileText = toolkit.createText(sectionPathsClient, "");
		 excludedElementsFileText.setLayoutData(gd);
		 Button excludedElementsFileButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 
		 	  
		 Label label6 = toolkit.createLabel(sectionPathsClient, "Excel with corrected tables:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label6.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text excelWithCorrectedTeblesText = toolkit.createText(sectionPathsClient, "");
		 excelWithCorrectedTeblesText.setLayoutData(gd);
		 Button excelWithCorrectedTeblesButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 
		 	 
		 Label label7 = toolkit.createLabel(sectionPathsClient, "Section data file for panel:");
		 gd = new GridData();
		 gd.horizontalSpan = 2; 
		 label7.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		 Text sectionDataFilePanelText = toolkit.createText(sectionPathsClient, "");
		 sectionDataFilePanelText.setLayoutData(gd);
		 Button sectionDataFilePanelButton = toolkit.createButton(sectionPathsClient, "Browse", SWT.PUSH);	 

		 GridLayout glFiltersPaths = new GridLayout();
		 glFiltersPaths.marginTop = 3;
		 sectionFiltersClient.setLayout(glFiltersPaths);
		 sectionFilters.setClient(sectionFiltersClient);
		 
		 
		 Label label8 = toolkit.createLabel(sectionFiltersClient, "Filters to INCLUDE in text:");
	 
		 GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		 gd2.heightHint=130;
		 //gd2.verticalSpan
		 //gd2.verticalSpan = 4; 	 
		 Text includeFiltersText = toolkit.createText(sectionFiltersClient, "",SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		 
		 includeFiltersText.setLayoutData(gd2);
		 Label label9 = toolkit.createLabel(sectionFiltersClient, "");
		 Label label10 = toolkit.createLabel(sectionFiltersClient, "Filters to EXCLUDE in text:");

		 
		 GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
		 gd3.heightHint = 130;

		 Text excludeFiltersText = toolkit.createText(sectionFiltersClient, "",SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		 excludeFiltersText.setLayoutData(gd3);
	}
	
	@Inject
	@Optional
	public void partIsActivePart(@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {
		if (parte.equals(activePart)) {
			broker.post(StressToolsEventConstants.STATUSBAR, (String)parte.getTransientData().get("File Path"));
		}
		
		
	}
	
	@PersistState
	public void persistState(MPart part) {
		  parte.setElementId(partPath.toString());
	      Map<String, String> state = part.getPersistedState();
	      //state.put(partPath.toString(), "AUTODOCU");
	      //System.out.println("@PersistState\tClase:\t"+ this.getClass().getSimpleName());
	}
	
	
	@Focus
	public void setFocus() {
		form.setFocus();
	}
	@PreDestroy
	public void dispose() {
		System.out.println("\nAutodocuForm:\tdispose...\t" +parte.getLabel()+"\n");
		broker.post(StressToolsEventConstants.FILE_CLOSE_AUTODOCU_PART, parte.getLabel());
		 if(toolkit!=null) toolkit.dispose();
		 
	}
	
}