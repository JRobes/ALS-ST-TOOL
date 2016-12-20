package aero.alestis.stresstools.lifecycle;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import aero.alestis.stresstools.autodocu.AutodocuForm;

public class LifeCycleManager {
	//@Inject MContext context;
	//@Inject MApplication app;

	/*
	
	@ProcessRemovals
	void postContextCreate(IEclipseContext context, MApplication application, EModelService modelService, EPartService partService){
		
		
		
		System.out.println("Esto debería ser lo primero");
		//System.out.println(org.osgi.framework.FrameworkUtil.getBundle(AutodocuForm.class));

		List<MPart> parts = modelService.findElements(application, null, MPart.class, null);
		//modelService.findElements(searchRoot, id, clazz, tagsToMatch)
		for(MPart elParte: parts){
			if(elParte.getContributionURI().endsWith("AutodocuForm")){
				Path partPath = Paths.get(elParte.getElementId());
				if(Files.exists(partPath, LinkOption.NOFOLLOW_LINKS)){
					System.out.println("EL ARCHIVO EXISTE$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println(elParte.toString());

				}
				else{
					System.out.println("EL ARCHIVO NOOOO EXISTE$");
					System.out.println(elParte.toString());
					//MPartStack parent =(MPartStack) modelService.getContainer(partes);
					MElementContainer<MUIElement> parent = elParte.getParent();
					//MElementContainer<MPartStack> parent = elParte.getParent();
					System.out.println("EL PARENT:\t"+parent.getElementId());
					//partService.hidePart(elParte, true);
					elParte.setToBeRendered(false);
					parent.getChildren().remove(elParte);
				}
			}
			//System.out.println(partes.getElementId());
		}
		//System.out.println(parts.size());

		
		//modelService.
	}
	*/
	
}
