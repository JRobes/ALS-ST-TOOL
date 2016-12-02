package aero.alestis.stresstools.projectexplorer;
 

import javax.inject.Inject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;

import aero.alestis.stresstools.events.StressToolsEventConstants;

public class ProjectExplorer {
	private Image imageRoot;
	private Image imageDocument;
	private Tree tree;
	private TreeViewer viewer;
	
	
	
	String[] pep = new String[2];
	RootProjects autodocu;
	RootProjects alpan;
	ModelProjectPath documento1;
		
	RootProjects[] listOfStressTools = new RootProjects[2];
	
	/*
	 *Node<String>[] EMPTY_ARRAY = (Node<String>[])Array.newInstance(Node.class,0);
	 */
	
	
	
	@SuppressWarnings("unchecked")
	@Inject
	public ProjectExplorer() {
		
		autodocu = new RootProjects("AUTODOCU");		
		alpan = new RootProjects("ALPAN");
		setInitialInput();
		
		
		
	}
	
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		URL url = FileLocator.find(bundle, new Path("icons/movingBox.gif"),null);
		ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
		imageRoot = imageDescriptor.createImage();
		URL url2 = FileLocator.find(bundle, new Path("icons/gameboard.gif"),null);
		ImageDescriptor imageDescriptor2 = ImageDescriptor.createFromURL(url);
		imageDocument = imageDescriptor2.createImage();
		
		System.out.println("Project Explorer createControls...");
		///tree = new Tree(parent, SWT.NONE);
		
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(listOfStressTools);
		System.out.println("ANTES DE AÑADIR DOCUMENTO\t"+autodocu.listOpenProjects.size());
		//documento1 = new ModelProjectPath("DOCUMENTO_1",autodocu );
		System.out.println("despues DE AÑADIR DOCUMENTO\t"+autodocu.listOpenProjects.size());
		viewer.expandAll();
		//autodocu.addProject(documento1);
		viewer.refresh();
		//hookDoubleClickCommand();
		
	}
	class ViewContentProvider implements ITreeContentProvider {
		Object[] EMPTY_ARRAY = new Object[0];

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return listOfStressTools;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof RootProjects){
				return ((RootProjects) parentElement).listOpenProjects.toArray();
				
			}
			return EMPTY_ARRAY;
			//return ((Node<String>) parentElement).getChildren().toArray(); 
		}

		@Override
		public Object getParent(Object element) {
			return ((ModelProjectPath) element).getParent();
		}

		@Override
		public boolean hasChildren(Object element) {
			if(element instanceof RootProjects) return true;
			return false;
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}

	}
	
	class ViewLabelProvider extends StyledCellLabelProvider {
		 
		 Bundle bundle = FrameworkUtil.getBundle(this.getClass());
		 URL urlMovingBox = FileLocator.find(bundle, new Path("icons/movingBox.gif"), null);
		 ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(urlMovingBox);
		 Image imageMovingBox = imageDescriptor.createImage();	
		 URL urlGameBoard = FileLocator.find(bundle, new Path("icons/file_obj.gif"), null);
		 ImageDescriptor imageDescriptor2 = ImageDescriptor.createFromURL(urlGameBoard);
		 Image img_autodocu_file = imageDescriptor2.createImage();	
		@Override
		public void update(ViewerCell cell) {
			Object element = cell.getElement();
			StyledString text = new StyledString();
			if(element instanceof ModelProjectPath){
				text.append(((ModelProjectPath) element).getName());
				cell.setText(text.toString());
				cell.setStyleRanges(text.getStyleRanges());
				cell.setImage(img_autodocu_file);
			}
			else if(element instanceof RootProjects){
				text.append(((RootProjects) element).getName());
				cell.setText(text.toString());
				cell.setStyleRanges(text.getStyleRanges());
				cell.setImage(imageMovingBox);
			}
	
			super.update(cell);
		}
		
	}
	
	
	private void setInitialInput(){
		
		//autodocu = new Node<String>("AUTODOCU");
		///alpan = new Node<String>("ALPAN");     
		
		listOfStressTools[0] = autodocu;
		listOfStressTools[1] = alpan;
		//System.out.println("numero de elementos alpan" + alpan.getChildren().size());
		//System.out.println("numero de elementos autodocu" + autodocu.getChildren().size());
	
	}
	
	@Inject
	@Optional
	public void updateListOfFiles(@UIEventTopic(StressToolsEventConstants.FILE_ALL_EVENTS) Event event) {

		String topic = event.getTopic();

		Object data = event.getProperty(IEventBroker.DATA);
		String fileUpdated = (String)data;
		ModelProjectPath name = new ModelProjectPath(fileUpdated, autodocu);
		//node.setParent(autodocu);
		switch(topic){
    		case StressToolsEventConstants.FILE_NEW_AUTODOCU_PART:
    			System.out.println("Entro en el UPDATE LIST OF FILES DE PROJECT EXPLORER, NEW DOC");
    			System.out.println("ARCHIVO A AÑADIR\t"+fileUpdated);
    			listOfStressTools[0].addProject(name);
    			viewer.expandAll();
    			//listOfFiles.add(fileUpdated[0]);
    			viewer.refresh();
    			break;
    		case StressToolsEventConstants.FILE_CLOSE_AUTODOCU_PART:
    			System.out.println("File Close autodocu from broker");
    			listOfStressTools[0].removeProject(name);
    			viewer.expandAll();
    			viewer.refresh();
    			break;
    		
		}
		
	}

	@PreDestroy
	private void predestroy() {
		System.out.println("PRE-DESTROY LLAMADO...");
		List<ModelProjectPath> results;
		results = listOfStressTools[0].getOpenProjects();
		Map<String, String> resultsMap = new HashMap<String, String>();
		int i=0;
		for (ModelProjectPath o : results) {
		    resultsMap.put("AUTODOCU_"+i, o.getName());
		}
		
	}
	
}