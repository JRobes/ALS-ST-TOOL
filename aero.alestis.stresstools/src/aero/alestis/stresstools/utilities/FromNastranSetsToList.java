package aero.alestis.stresstools.utilities;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class FromNastranSetsToList {
	private FormToolkit toolkit;
	private ScrolledForm form;

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
		 Action copyButtonAction = new Action("", imageDescriptor) {
			    @Override
			    public void run() {
			        // Perform action
			    }
		 };

		 
		 copyButtonAction.setToolTipText("Copy to clipboard");
		 runButtonAction.setToolTipText("Execute");
		
		 toolBarManager.add(copyButtonAction);
		 toolBarManager.add(runButtonAction);
		 form.getToolBarManager().update(true);
		 form.setText("FROM NASTRAN SETS TO SORTED LIST");
	}
	
	@PreDestroy
	public void dispose() {
		//System.out.println("\nAutodocuForm:\tdispose...\t" +parte.getLabel()+"\n");
		//broker.post(StressToolsEventConstants.FILE_CLOSE_AUTODOCU_PART, parte.getLabel());
		 if(toolkit!=null) toolkit.dispose();
		 
	}
	
	
}
