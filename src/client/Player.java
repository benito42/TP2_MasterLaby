package client;

public class Player
{
	private String name;
	private String avatar;
	private int positionX;
	private int positionY;
	private Objective[] objectiveList = new Objective[3];
	
	public Player()
	{
		
	}

	public Player(String name, String avatar, int positionX, int positionY)
	{
		this.name = name;
		this.avatar = avatar;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public Objective[] getObjectiveList() {
		return objectiveList;
	}

	public void setObjectiveList(Objective[] objectiveList) {
		this.objectiveList = objectiveList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	
	public void moveUp()
	{
		positionY--;
	}

	public void moveDown()
	{
		positionY++;
		
	}

	public void moveLeft()
	{
		positionX--;
		
	}

	public void moveRight()
	{
		positionX++;
	}
		
}
