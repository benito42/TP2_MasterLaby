package server;

import java.util.LinkedList;

import server.Tile.TILETYPE;


public class Tile
{
	private LinkedList<Player> playerList;
	private Objective objective;
	private TILETYPE tileType;
	
	public enum TILETYPE
	{
		horizontal(false, true, false, true), 
		vertical(true, false, true, false),
		horizontal_up(true, true, false, true),
		horizontal_down(false, true, true, true),
		vertical_right(true, true, true, false),
		vertical_left(true, false, true, true),
		up_left(true, false, false, true),
		up_right(true, true, false, false),
		down_left(false, false, true, true),
		down_right(false, true, true, false);
		
		private final boolean goUp;
		private final boolean goRight;
		private final boolean goDown;
		private final boolean goLeft;
		
		TILETYPE(boolean goUp, boolean goRight, boolean goDown, boolean goLeft)
		{
			this.goUp = goUp;
			this.goRight = goRight;
			this.goDown = goDown;
			this.goLeft = goLeft;
		}
	}
	
	public Tile(TILETYPE tileType)
	{
		this.tileType = tileType;
	}

	public TILETYPE getTileType() {
		return tileType;
	}


	public void setPlayerList(LinkedList<Player> playerList) {
		this.playerList = playerList;
	}

	public LinkedList<Player> getPlayerList()
	{
		return playerList;
	}

	public void addPlayer(Player player)
	{
		playerList.add(player);
	}
	
	public void removePlayer(Player player)
	{
		playerList.remove(player);
	}
	
	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}

	public void setTileType(TILETYPE tileType) {
		this.tileType = tileType;
		
	}
	
}
