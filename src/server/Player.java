package server;

import java.io.Serializable;


public class Player implements Serializable
{
	private static final long serialVersionUID = 7243623938818882012L;
	private Tile currentTile;
	private String name = "DEFAULT";
	private Avatar avatar = Avatar.commando;
	private Objective[] objectiveList = new Objective[3];
	private int playerNumber;

	public Player(Tile currentTile)
	{
		this.currentTile = currentTile;
	}
	
	public Player(String name, Avatar avatar, Tile currentTile, int playerNumber)
	{
		this.name = name;
		this.avatar = avatar;
		this.currentTile = currentTile;
		this.playerNumber = playerNumber;
		objectiveList[0] = new Objective(avatar.getObj1());
		objectiveList[1] = new Objective(avatar.getObj2());
		objectiveList[2] = new Objective(avatar.getObj3());
		
	}
	
	public Objective[] getObjectiveList() {
		return objectiveList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Tile getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(Tile currentTile) {
		this.currentTile = currentTile;
		
	}
		
}
