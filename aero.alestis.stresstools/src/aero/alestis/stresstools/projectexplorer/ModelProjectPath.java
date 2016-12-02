package aero.alestis.stresstools.projectexplorer;

import java.nio.file.Path;

public class ModelProjectPath {
	protected Path path;
	protected String fileName;
	protected RootProjects root;
	public ModelProjectPath(String fileName, RootProjects root){
		this.fileName = fileName;
		root.addProject(this);
		this.root = root;
		
	}
	public String getName(){
		return fileName;
	}
	public RootProjects getParent(){
		return this.root;
	}
	
	public boolean equals(Object other) {
	    if (!(other instanceof ModelProjectPath)) {
	        return false;
	    }
	    ModelProjectPath that = (ModelProjectPath) other;
	    // Custom equality check here.
	    //return this.path.equals(that.path)&& this.fileName.equals(that.fileName);
	    return this.fileName.equals(that.fileName);

	}
	
}
