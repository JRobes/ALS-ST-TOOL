package aero.alestis.stresstools.console;
 

import javax.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.event.Event;

import aero.alestis.stresstools.events.StressToolsEventConstants;


public class ConsoleView {
	public Text tv;
	public Date date;
	private Calendar cal;
	private SimpleDateFormat sdf;
	@Inject
	public ConsoleView() {

	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		tv  = new Text(parent,SWT.MULTI | SWT.V_SCROLL |SWT.H_SCROLL);
		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		cal = Calendar.getInstance();
		tv.setText(sdf.format(cal.getTime())+ "\t\tStress Tools started...\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tv.append(sdf.format(cal.getTime())+"\t\tReady...\n");
	
		
	}
	
	
	@Inject
	@Optional
	public void recieveLogMessages(@UIEventTopic(StressToolsEventConstants.FILE_ALL_EVENTS) Event event) {
		String topic = event.getTopic();
		//	System.out.println("Entro en el event:\t" +message);
		
		String data = (String)event.getProperty(IEventBroker.DATA);
		//File[] fileUpdated = (File[])data;
		switch(topic){
		case StressToolsEventConstants.FILE_NEW_AUTODOCU_PART:
			cal = Calendar.getInstance();
			tv.append(sdf.format(cal.getTime())+ "\t\tAutodocu document created:\t"+data+"\n");
			break;
		case StressToolsEventConstants.FILE_CLOSE_AUTODOCU_PART:
			cal = Calendar.getInstance();
			tv.append(sdf.format(cal.getTime())+ "\t\tAutodocu document closed:\t"+data+"\n");
			break;
	
		}
		
	}

	
}	
