 
package aero.alestis.stresstools.utilities.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class FromCorinCsvToElsHandler {
	@Inject
	EPartService partService;
	@Inject
	MApplication application;
	@Inject
	EModelService modelService;
	@Execute
	public void execute() {
		 List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
		 int index = 0;
		 for (MPartStack element : stacks) {
			 String jander = "aero.alestis.stresstools.partstack.utilities";
			 if(jander.equals(element.getElementId()))	{
				 System.out.println("ES_ELE ELEMENTO .....");
				 index = stacks.indexOf(element);
				 System.out.println("Número de elementos del stack:\t:" + element.getChildren().size()); 
				  stacks.get(index).setVisible(true);
				  //stacks.get(index).setToBeRendered(true);
				  MPart part2 = partService.createPart("aero.alestis.stresstools.partdescriptor.corincsvtoels");
				  stacks.get(index).getChildren().add(part2);
				  stacks.get(index).setSelectedElement(part2);
				  partService.showPart(part2, PartState.CREATE);
				  System.out.println("STACK VISIBLE??\t"+element.isVisible()+"\t"+element.getElementId());
				  break;

			 }
			 else{
				 
			
			 System.out.println("STACK VISIBLE??\t"+element.isVisible()+"\t"+element.getElementId());
			 System.out.println("El indice del elemento es:\t"+stacks.indexOf(element));
			 }
		  }
			 
		

		

	}
		
}