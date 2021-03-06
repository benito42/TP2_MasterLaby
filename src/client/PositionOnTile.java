package client;

public enum PositionOnTile
{
		upLeft(0, 0),
		upRight(36, 0),
		downRight(36, 36),
		downLeft(0, 36),
		mid(18, 18);
		
		private final int intX;
		private final int intY;
		
		PositionOnTile(int intX, int intY)
		{
			this.intX = intX;
			this.intY = intY;
		}
		
		public int getX(){return this.intX;}
		public int getY(){return this.intY;}
	
}
