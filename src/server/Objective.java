package server;

public class Objective
{
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
