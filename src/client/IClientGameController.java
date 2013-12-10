package client;

import server.Direction;
import server.Tile;

public interface IClientGameController 
{
	public void updateBoard(Tile[][] newBoardPaths);
	public void updateNextTile(Tile nextTilePath);
	public void updateNoPlayer(int noPlayer);
	public void updateTurnChange(int noActivePlayer);
	public void shiftTiles(int index, Direction direciton);
	public void nextPlayer();
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
	public void quitGame();
	public void connect(IClientGameController controller);
	
	public Tile[][] getBoard();
	public Tile getNewNextTile();
}
