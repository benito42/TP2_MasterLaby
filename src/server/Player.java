package server;


public class Player
{
	private Tile currentTile;
	private String name = "DEFAULT";
	private String avatar = "DEFAULT";
	private Objective[] objectiveList = new Objective[3];


	public Player(Tile currentTile)
	{
		this.currentTile = currentTile;
	}
	
	public Player(String name, String avatar, Tile currentTile)
	{
		this.name = name;
		this.avatar = avatar;
		this.currentTile = currentTile;
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

	public Tile getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(Tile currentTile) {
		this.currentTile = currentTile;
	}
		
}
