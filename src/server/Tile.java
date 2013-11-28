package server;

public class Tile
{
	private Objective objective;
	private TILETYPE tileType;
	
	private int positionX;
	private int positionY;
	
	public enum TILETYPE
	{
		horizontal(false, true, false, true, "/img/horizontal.jpg"), 
		vertical(true, false, true, false, "/img/vertical.jpg"),
		horizontal_up(true, true, false, true, "/img/horizontal_up.jpg"),
		horizontal_down(false, true, true, true, "/img/horizontal_down.jpg"),
		vertical_right(true, true, true, false, "/img/vertical_right.jpg"),
		vertical_left(true, false, true, true, "/img/vertical_left.jpg"),
		up_left(true, false, false, true, "/img/up_left.jpg"),
		up_right(true, true, false, false, "/img/up_right.jpg"),
		down_left(false, false, true, true, "/img/down_left.jpg"),
		down_right(false, true, true, false, "/img/down_right.jpg");
		
		private final boolean goUp;
		private final boolean goRight;
		private final boolean goDown;
		private final boolean goLeft;
		private final String path;
		
		TILETYPE(boolean goUp, boolean goRight, boolean goDown, boolean goLeft, String path)
		{
			this.goUp = goUp;
			this.goRight = goRight;
			this.goDown = goDown;
			this.goLeft = goLeft;
			this.path = path;
		}
	}

	public boolean goUp(){return tileType.goUp;}
	public boolean goRight(){return tileType.goRight;}
	public boolean goDown(){return tileType.goDown;}
	public boolean goLeft(){return tileType.goLeft;}
	public String getPath(){return tileType.path;}
	
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
