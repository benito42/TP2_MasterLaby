package client;

import java.io.Serializable;

import server.Direction;
import server.Tile;

public interface IClientGameController extends Serializable
{
	public void updateBoard(Tile[][] newBoard);
	public void updateNextTile(Tile nextTile);
	public void shiftTiles(int index, Direction direciton);
	public void nextPlayer();
	
	public Tile[][] getBoard();
	public Tile getNextTile();
}
