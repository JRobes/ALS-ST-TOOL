 
package aero.alestis.stresstools.utilities.corin;

import javax.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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

public class FromCorinCsvToEls {
	private FormToolkit toolkit;
	private ScrolledForm form;
	private static Text outputText;
	private static Text inputText;

	@Inject
	public FromCorinCsvToEls() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
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
		 url = FileLocator.find(bundle, new Path("icons/copy_edit.gif"), null);
		 imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action copyButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			    	
			    	StringReader cadena =  new StringReader(inputText.getText());
			        BufferedReader linea = new BufferedReader(cadena);
			       // BufferedWriter bffWriter = new BufferedWriter(new PrintWriter(ARCHIVO_DE_SALIDA));
			        String lineIn,aux;
					try {
						while ((lineIn = linea.readLine()) != null) {
							aux = lineIn.trim();

							if(aux.equals("") || aux.charAt(0)=='$'){
								
							}
							else {
								outputText.append(aux);
								outputText.append("\n");
								//bffWriter.write(line);
								//bffWriter.newLine();
							}
							
							
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						linea.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//bffWriter.flush();
					//bffWriter.close(); 
			        
			        
			        
			        //outputText.setText("rrrrr");
			        
			    }
		 };

		 
		 copyButtonAction.setToolTipText("Copy to clipboard");
		 runButtonAction.setToolTipText("Execute");
		
		 toolBarManager.add(copyButtonAction);
		 toolBarManager.add(runButtonAction);
		 form.getToolBarManager().update(true);
		 form.setText("FROM CORIN CSV TO ELS FILES");
		 
		 
		 TableWrapLayout tableWraplayout = new TableWrapLayout();
		 //gridLayout.makeColumnsEqualWidth=true;
		 tableWraplayout.topMargin=20;
		 tableWraplayout.bottomMargin=20;
		 tableWraplayout.numColumns = 2;
		 
		 //form.getb
		 form.getBody().setLayout(tableWraplayout);
		 TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		 td2.colspan = 2;	 
		 
		 Section sectionInputText = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
		 sectionInputText.setLayoutData(td2);
		 sectionInputText.setText("Input text");
		 
		 Composite compositeSectionInputText = toolkit.createComposite(sectionInputText);
		 sectionInputText.setClient(compositeSectionInputText);
		 
		 GridLayout gridLayoutSectionInputText = new GridLayout(2, false);
		 //fillLayout2.marginHeight=5;
		 //fillLayout2.spacing = 30;
		 GridData gd = new GridData();
		 gd.horizontalSpan = 2;
		 compositeSectionInputText.setLayout(gridLayoutSectionInputText);
		 
		 
		 		 
		 Label label8 = toolkit.createLabel(compositeSectionInputText, "Set the CSV input file:");
		
		 label8.setLayoutData(gd);
		 gd = new GridData(GridData.FILL_HORIZONTAL);
		
		 Text csvFileText = toolkit.createText(compositeSectionInputText, "");
		 csvFileText.setLayoutData(gd);
		 Button csvFileButton = toolkit.createButton(compositeSectionInputText, "Browse", SWT.PUSH);
		 csvFileButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {}

			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("boton apretadooooOOOOOOO");
				
			}

			@Override
			public void mouseUp(MouseEvent e) {}
			 
		 });
	
		 
		 
		 
		 GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		 gd2.heightHint=130;
		 gd2.horizontalSpan = 2;

		 inputText = toolkit.createText(compositeSectionInputText, "",SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		 inputText.setLayoutData(gd2);
		 
		 
		 
		 TableWrapData td3 = new TableWrapData(TableWrapData.FILL_GRAB);
		 td3.colspan = 2;
		 Section sectionOutputText = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
		 sectionOutputText.setLayoutData(td3);
		 sectionOutputText.setText("Output text");
		 
		 Composite compositeSectionOutputText = toolkit.createComposite(sectionOutputText);
		 sectionOutputText.setClient(compositeSectionOutputText);
		
		 GridLayout gl4 = new GridLayout();
		 gl4.marginTop=3;
		 compositeSectionOutputText.setLayout(gl4);
		 Label label9 = toolkit.createLabel(compositeSectionOutputText, "Sorted list of elements:");
	 
		 GridData gd5 = new GridData(GridData.FILL_HORIZONTAL);
		 gd5.heightHint=130;

		 outputText = toolkit.createText(compositeSectionOutputText, "",SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		 outputText.setLayoutData(gd5);
	}
	
	
	@PreDestroy
	public void dispose() {
		//System.out.println("\nAutodocuForm:\tdispose...\t" +parte.getLabel()+"\n");
		//broker.post(StressToolsEventConstants.FILE_CLOSE_AUTODOCU_PART, parte.getLabel());
		 if(toolkit!=null) toolkit.dispose();
		 
	}
	
	
}