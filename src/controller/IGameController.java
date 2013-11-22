package client;

public interface IGameController
{

	
	public void addPlayer(Player player);
	public void removePlayer();
	public void nextPlayer();
	
	public void gameOver();
	
	public void moveUp();
	public void moveDown();
	public void moveLeft();
	public void moveRight();
	
}
