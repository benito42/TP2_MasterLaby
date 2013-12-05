package server;

import client.IClientGameController;

public interface IGameController
{
	public void addObserver(IClientGameController newObserver);
	
	public void addPlayer(Player player);
	public void removePlayer();
	public void nextPlayer();
	
	public void gameOver();
	public Tile getNextTile();
	public Tile[][] getBoard();
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
	
	public void shiftTiles(int index, Direction direction);

	void unregisterObserver();
}
