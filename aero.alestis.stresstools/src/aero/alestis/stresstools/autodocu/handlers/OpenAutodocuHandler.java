 
package aero.alestis.stresstools.autodocu.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

public class OpenAutodocuHandler {
	@Inject
	EPartService partService;
	@Inject
	EModelService modelService;
	@Inject
	MApplication application;
	@Execute
	public void execute(Composite parent) {
		FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
		//dialog.setOverwrite(true);
		dialog.setFilterExtensions(new String [] {"*.xml"});
		dialog.setText("Open file:");
		String result = dialog.open();
		boolean createFile = false;
		boolean isAlreadyOpen = false;
		if(result!=null){
			if(Files.exists(Paths.get(result))){
				Path path;
				String miString;
				for(MPart losPartes : partService.getParts()){
								
					miString =(String)losPartes.getTransientData().get("File Path");
					if(miString == null){
						//System.out.println("El archivo existeeeeeeeeee");
						//createfile = true;
					}
					else{
						path = Paths.get(miString);
						if(path.compareTo(Paths.get(result))==0){
							partService.showPart(losPartes, PartState.ACTIVATE);
							//createfile = false;
							isAlreadyOpen = true;
							break;
						}
					}
				}
				if(!isAlreadyOpen){
	                  MPart part2 = partService.createPart("aero.alestis.stresstools.partdescriptor.autodocu");
	                  //part2.setLabel(file.getName());
	                  part2.setCloseable(true);
	                  part2.getTransientData().put("File Path", result);
	                  List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
	          	      stacks.get(1).getChildren().add(part2);
	                  partService.showPart(part2, PartState.ACTIVATE);
				}
				
				
				
			}
			else{
				if(MessageDialog.openConfirm(parent.getShell(), "Create file", "The file does not exists.\n Press OK to create.")){
					
					try {
						if(!result.endsWith(".xml")){
							System.out.println("NO TERMINA EN XML.....");
							String aux = result.concat(".xml");
							System.out.println(aux);
						}
						Files.createFile(Paths.get(result));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                  MPart part2 = partService.createPart("aero.alestis.stresstools.partdescriptor.autodocu");
	                  //part2.setLabel(file.getName());
	                  part2.setCloseable(true);
	                  part2.getTransientData().put("File Path", result);
	                  List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
	          	      stacks.get(1).getChildren().add(part2);
	                  partService.showPart(part2, PartState.ACTIVATE);
				}
				
			
			}
			
			
			
			
			
			
			/*
			
			System.out.println("El archivo existeeee\t"+result);
			if(!Files.exists(Paths.get(result))){
				if(MessageDialog.openConfirm(parent.getShell(), "Create file", "The file does not exists.\n Press OK to create.")){
					createFile = true;
				}
				
			}
			if(createFile){
				
			}
			*/
		}
		
		
	}
		
}