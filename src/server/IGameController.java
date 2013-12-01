package server;

import client.IGameView;


public interface IGameController
{
	public void addView(IGameView newView);
	
	public void addPlayer(Player player);
	public void removePlayer();
	public void nextPlayer();
	
	public void gameOver();
	public Model getModel();
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
	
	public void shiftTiles(int index, Direction direction);
}
