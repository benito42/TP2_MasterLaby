package server;

import java.util.LinkedList;

import server.Tile.TILETYPE;


public class Tile
{
	private Objective objective;
	private TILETYPE tileType;
	
	private int positionX;
	private int positionY;
	
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

	public boolean goUp(){return tileType.goUp;}
	public boolean goRight(){return tileType.goRight;}
	public boolean goDown(){return tileType.goDown;}
	public boolean goLeft(){return tileType.goLeft;}
	
	public Tile(TILETYPE tileType, int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.tileType = tileType;
	}

	public TILETYPE getTileType() {
		return tileType;
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
