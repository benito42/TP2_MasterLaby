package server;

import java.io.Serializable;

public class Objective implements Serializable
{
	private static final long serialVersionUID = -6692899942522212118L;
	private String path;
	private boolean objectiveReached = false;
	
	public Objective(String path)
	{
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	public boolean isObjectiveReached() {
		return objectiveReached;
	}


	public void setObjectiveReached(boolean objectiveReached) {
		this.objectiveReached = objectiveReached;
	}
	

}
