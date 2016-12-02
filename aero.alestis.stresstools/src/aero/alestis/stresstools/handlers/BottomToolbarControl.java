package aero.alestis.stresstools.handlers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import aero.alestis.stresstools.events.StressToolsEventConstants;

public class BottomToolbarControl {
	Label label;
	Composite comp ;
		
	@PostConstruct
	public void createGui(Composite parent) {
		
		//System.out.println(parent.getData().toString());
		comp= new Composite(parent, SWT.NONE);
		//RowLayout layout = new RowLayout(SWT.HORIZONTAL|SWT.FILL,false);
		GridLayout layout = new GridLayout(2,false);
	
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		//button1.setLayoutData(gridData);
		
		layout.marginTop=5;
		layout.marginBottom=5;
		layout.marginLeft =10;
		layout.marginRight =10;
		//layout.wrap= true;
		comp.setLayout(layout);
		label = new Label(comp,SWT.HORIZONTAL);
		//label.setLayoutData(gridData);
		label.setText("__________  ");
		GridDataFactory.fillDefaults().hint(1200, SWT.DEFAULT).applyTo(label);
		System.out.println(comp.getSize().x);
		
		GridData gd2 = new GridData();
		gd2.grabExcessHorizontalSpace = true;
		Label label2 = new Label(comp,SWT.NONE);
		label2.setText("TTTjandemore nirghtL");
		label2.setLayoutData(gd2);
		
	}
	
	
	@Inject
	@Optional
	public void  getEvent(@UIEventTopic(StressToolsEventConstants.STATUSBAR) String message) {
	    updateInterface(message); 
	}
	
	public void updateInterface(final String message)
    {
        try{
            Display.getDefault().asyncExec(new Runnable() {
              @Override
              public void run() {
                 try{
                        label.setText(message);
                		//label.setSize(label.computeSize(SWT.DEFAULT, SWT.DEFAULT));

                        label.redraw();
                        comp.layout();
                        comp.redraw();
                        comp.update();
                		System.out.println("X ce comp\t"+ comp.getShell().getSize().x+"\tY de comp\t"+ comp.getShell().getSize().y);
                		

                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }               
              }
            });
        }
        catch(Exception exception){
            System.out.println(exception);
        }   
    }
}