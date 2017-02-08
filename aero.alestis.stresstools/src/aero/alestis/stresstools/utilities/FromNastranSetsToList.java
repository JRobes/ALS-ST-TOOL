package aero.alestis.stresstools.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
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

public class FromNastranSetsToList {
	private FormToolkit toolkit;
	private ScrolledForm form;
	private static Text outputText;
	private static Text inputText;


	public FromNastranSetsToList() {
		
    }
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		//parte.getTags().add(EPartService.REMOVE_ON_HIDE_TAG);
		 toolkit = new FormToolkit(parent.getDisplay());
		 form = toolkit.createScrolledForm(parent);
		 
		 toolkit.decorateFormHeading(form.getForm());
		 
		 IToolBarManager toolBarManager =form.getToolBarManager();
		 
		 Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		 URL url = FileLocator.find(bundle, new Path("icons/copy_edit.gif"), null);
		 ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action copyButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			        // Perform action
			    }
		 };
		 url = FileLocator.find(bundle, new Path("icons/lrun_obj.png"), null);
		 imageDescriptor = ImageDescriptor.createFromURL(url);
		 Action runButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			    	List<Integer> elementList=new ArrayList<Integer>();
			        HashSet<Integer> hashSet = new HashSet<Integer>();
			    	boolean test;
			    	outputText.setText("");
			    	StringReader cadena =  new StringReader(inputText.getText());
			        BufferedReader linea = new BufferedReader(cadena);
			        BufferedReader laLinea = new BufferedReader(cadena);
			        String[] stringArray,stringArray2;
			       // BufferedWriter bffWriter = new BufferedWriter(new PrintWriter(ARCHIVO_DE_SALIDA));
			       String laLineIn,aux;
			       int inicio = 0,fin = 0, incr= 0;
					//test = busca_dos_puntos(linea);
			       // outputText.setText("input test es de Patran:\t"+test);
			        
					try {
						while ((laLineIn = laLinea.readLine()) != null) {
							if(busca_caracter2(":",laLineIn)){
								aux = laLineIn.trim();
								
								System.out.println("linea con : ");
								stringArray = aux.split("\\s+");
								//stringArray = laLineIn.split("\\s+","");
								System.out.println(stringArray.length);
								for(int i = 0; i < stringArray.length; i++){
									if(isPositiveInteger(stringArray[i])){
										System.out.println("Elemento suelto.... ");
										//System.out.println(Integer.parseInt(stringArray[i]));
										elementList.add(Integer.parseInt(stringArray[i]));
									}
									else{
										stringArray2 = stringArray[i].split(":");
										switch (stringArray2.length){
										
										case 2:
											System.out.println("EXPRESION CON DOS PARTES con dos partes");
											if(isPositiveInteger(stringArray2[0]) && isPositiveInteger(stringArray2[1])){
												if(Integer.parseInt(stringArray2[0]) < Integer.parseInt(stringArray2[1])){
													inicio = Integer.parseInt(stringArray2[0]);
													fin = Integer.parseInt(stringArray2[1]);
												}
												else{
													fin = Integer.parseInt(stringArray2[0]);
													inicio = Integer.parseInt(stringArray2[1]);
												}
												System.out.println("inicio:\t"+inicio);
												System.out.println("fin:\t"+fin);
												for(int aj = inicio; aj <= fin; aj++){
													//System.out.println("añadir elemento:\t"+aj);
													elementList.add(aj);
												}
											}
											break;
										case 3:
											System.out.println("EXPRESION CON 3 3 3 3 PARTES con dos partes");
											if(isPositiveInteger(stringArray2[0]) && isPositiveInteger(stringArray2[1]) && isInteger(stringArray2[2])){
												if(Integer.parseInt(stringArray2[2]) < 0){
													fin = Integer.parseInt(stringArray2[0]);
													inicio = Integer.parseInt(stringArray2[1]);
												}
												else{
													inicio = Integer.parseInt(stringArray2[0]);
													fin = Integer.parseInt(stringArray2[1]);
												}
												incr = Math.abs(Integer.parseInt(stringArray2[2]));
												System.out.println("inicio:\t"+inicio);
												System.out.println("fin:\t"+fin);
												System.out.println("incremento:\t"+incr);
												
												for(int aj = inicio; aj <= fin; aj = aj + incr){
													//System.out.println("añadir elemento:\t"+aj);
													elementList.add(aj);
												}
											}
											break;
										case 0:
											System.out.println("Errorrrrr!!!!");
																							
											break;
										}
									}
								}
							}
							else if(busca_caracter2(",",laLineIn)){
								System.out.println("linea con ,");
							}else{
								System.out.println("linea que se supone con espacios");
							}
							
						}
						
						
						
						cadena.reset();
						BufferedReader linea2 = new BufferedReader(cadena);
						//String jander;
						//jander = linea2.readLine();
						//System.out.println("linea2...."+ jander.toString());
						//while ((lineIn = linea2.readLine()) != null) {
						//	stringArray = lineIn.split("\\s+");
							//System.out.println("Linea con elementos\t" + stringArray.length);													
						//}
						linea2.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				    Collections.sort(elementList);

				    hashSet.addAll(elementList);
				    elementList.clear();
				    elementList.addAll(hashSet);
				    Collections.sort(elementList);
				    System.out.println(elementList.toString());
				    for (Integer element : elementList) {
				    	outputText.append(element +"\n");
				    }
			        
			        
			        
			        /*
			        
					if(busca_caracter(":",linea)){
						divideCadenaPatran(cadena);
						
					}
					else if(busca_caracter(",",linea)){
						System.out.println("Linea con elementos COMA");
					}
					else{
						System.out.println("Linea con elementos ESPACIOS EN BLANCO");
					}
					try {
						linea.close();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
			        
			    }
			    public boolean isPositiveInteger(String s) {
			        boolean isValidInteger = false;
			        try
			        {
			           int j;
			        	j= Integer.parseInt(s);
			        	if(j>0)isValidInteger = true;
			        	
			         
			   
			           
			        }
			        catch (NumberFormatException ex)
			        {
			        	System.out.println("dentro de la excepcioón... %%%%%%%%%%%5");
			        }
			   
			        return isValidInteger;
			     }
			    public boolean isInteger(String s) {
			        boolean isValidInteger = false;
			        try
			        {
			           int j;
			           j= Integer.parseInt(s);
			        	//if(j>0)isValidInteger = true;
			           isValidInteger = true;
			         
			   
			           
			        }
			        catch (NumberFormatException ex)
			        {
			        	System.out.println("dentro de la excepcioón... %%%%%%%%%%%5");
			        }
			   
			        return isValidInteger;
			     }
				/**
				 * @param cadena
				 */
				private void divideCadenaPatran(StringReader cadena) {
					String lineIn;
					String aux;
					String[] stringArray;
					System.out.println("DENTRO DEL IF....");
					try {
						
						cadena.reset();
						BufferedReader linea2 = new BufferedReader(cadena);
						//String jander;
						//jander = linea2.readLine();
						//System.out.println("linea2...."+ jander.toString());
						while ((lineIn = linea2.readLine()) != null) {
							stringArray = lineIn.split("\\s+");
							System.out.println("Linea con elementos\t" + stringArray.length);													
						}
						linea2.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				/**
				 * @param string 
				 * @param linea
				 */
				private boolean busca_caracter(String string, BufferedReader linea) {
					String lineIn;
					String aux;
					boolean result = false;
					try {
						while ((lineIn = linea.readLine()) != null) {
							aux = lineIn.trim();
							if(aux.contains(string)){
								result = true;
								break;
							}

												
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					return result;
					
				}
				
				/**
				 * @param string 
				 * @param laLineIn
				 */
				private boolean busca_caracter2(String string, String laLineIn) {
					if(laLineIn.contains(string))return true;
					return false;
				}
					
				
		 };

		 
		 copyButtonAction.setToolTipText("Copy to clipboard");
		 runButtonAction.setToolTipText("Execute");
		
		 toolBarManager.add(copyButtonAction);
		 toolBarManager.add(runButtonAction);
		 form.getToolBarManager().update(true);
		 form.setText("FROM NASTRAN SETS TO SORTED LIST");
		 
		 
		 TableWrapLayout tableWraplayout = new TableWrapLayout();
		 //gridLayout.makeColumnsEqualWidth=true;
		 tableWraplayout.topMargin=20;
		 tableWraplayout.bottomMargin=20;
		 tableWraplayout.numColumns = 1;
		 //form.getb
		 form.getBody().setLayout(tableWraplayout);
		 TableWrapData td2 = new TableWrapData(TableWrapData.FILL_GRAB);
		 
		 
		 Section sectionInputText = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
		 sectionInputText.setLayoutData(td2);
		 sectionInputText.setText("Input text");
		 
		 Composite compositeSectionInputText = toolkit.createComposite(sectionInputText);
		 sectionInputText.setClient(compositeSectionInputText);
		
		 GridLayout gl2 = new GridLayout();
		 gl2.marginTop=3;
		 compositeSectionInputText.setLayout(gl2);
		 @SuppressWarnings("unused")
		Label label8 = toolkit.createLabel(compositeSectionInputText, "Nastran SET / Patran elements:");
	 
		 GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
		 gd2.heightHint=130;
 
		 inputText = toolkit.createText(compositeSectionInputText, "",SWT.MULTI|SWT.WRAP|SWT.V_SCROLL);
		 inputText.setLayoutData(gd2);
		 
		 
		 
		 TableWrapData td3 = new TableWrapData(TableWrapData.FILL_GRAB);
 
		 Section sectionOutputText = toolkit.createSection(form.getBody(), Section.DESCRIPTION|Section.TITLE_BAR| Section.TWISTIE|Section.EXPANDED);
		 sectionOutputText.setLayoutData(td3);
		 sectionOutputText.setText("Output text");
		 
		 Composite compositeSectionOutputText = toolkit.createComposite(sectionOutputText);
		 sectionOutputText.setClient(compositeSectionOutputText);
		
		 GridLayout gl4 = new GridLayout();
		 gl4.marginTop=3;
		 compositeSectionOutputText.setLayout(gl4);
		 @SuppressWarnings("unused")
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
