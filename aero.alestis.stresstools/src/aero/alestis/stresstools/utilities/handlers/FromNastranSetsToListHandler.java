package aero.alestis.stresstools.utilities.handlers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Composite;

public class FromNastranSetsToListHandler {
	@Inject
	EPartService partService;
	@Inject
	MApplication application;
	@Inject
	EModelService modelService;
	@Execute
	public void execute(Composite parent) {
		
		 MPart part2 = partService.createPart("aero.alestis.stresstools.partdescriptor.nastransettolist");
		 List<MPartStack> stacks = modelService.findElements(application, null, MPartStack.class, null);
		 part2.setCloseable(true);
		 
		 stacks.get(2).getChildren().add(part2);
		 stacks.get(2).setVisible(true);
		 //stacks.setVisible(true);
		 //stack.getChildren().add(part2);
		 
	}
}
