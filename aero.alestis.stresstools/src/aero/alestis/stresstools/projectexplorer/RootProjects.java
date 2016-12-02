package aero.alestis.stresstools.projectexplorer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RootProjects {
	protected String rootProjectName;
	protected List<ModelProjectPath> listOpenProjects;
	public RootProjects(){
		listOpenProjects = new ArrayList();
	}
	
	public String getName(){
		return rootProjectName;
	}
	public RootProjects(String name){
		this();
		this.rootProjectName = name;
	}
	public List<ModelProjectPath> getOpenProjects() {
		return listOpenProjects;
	}
	
	@SuppressWarnings("unchecked")
	public void addProject(ModelProjectPath modelPath){
		 System.out.println("demtro de addProject\t");

		 boolean check = true;
		 //int index =0;
		 Iterator<ModelProjectPath> it = listOpenProjects.iterator();
		 if(listOpenProjects.isEmpty()){
			 listOpenProjects.add(modelPath);
		 }else{
			 while(it.hasNext()){
				 ModelProjectPath user = it.next();
	        	 if(user.equals(modelPath))	 {
	        		 check = false;
	        		 
	        	 }
			 }
			 if(check) listOpenProjects.add(modelPath);
		 }
         
	}
	
	public void removeProject(ModelProjectPath modelPath){

		 boolean check = false;
		 int index =0;
		 Iterator<ModelProjectPath> it = listOpenProjects.iterator();
		 if(listOpenProjects.isEmpty()){
			 //listOpenProjects.add(modelPath);
		 }else{
			 while(it.hasNext()){
				 ModelProjectPath user = it.next();
	        	 if(user.equals(modelPath))	 {
	        		 it.remove();
	        		 //check = true;
	        		 
	        	 }
			 }
			 //if(check) listOpenProjects.add(modelPath);
		 }
	}
}
