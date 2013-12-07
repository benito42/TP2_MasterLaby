package client;

import server.Direction;

public interface IClientGameController 
{
	public void updateBoard(String[][] newBoardPaths);
	public void updateNextTile(String nextTilePath);
	public void shiftTiles(int index, Direction direciton);
	public void nextPlayer();
	
	public String[][] getBoard();
	public String getNewNextTile();
}
