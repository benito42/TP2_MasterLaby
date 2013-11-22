package server;

public class Objective
{
	private String name;
	private int positionX;
	private int positionY;
	private boolean objectiveReached = false;
	
	public Objective()
	{
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPositionX() {
		return positionX;
	}


	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}


	public int getPositionY() {
		return positionY;
	}


	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}


	public boolean isObjectiveReached() {
		return objectiveReached;
	}


	public void setObjectiveReached(boolean objectiveReached) {
		this.objectiveReached = objectiveReached;
	}
	

}
