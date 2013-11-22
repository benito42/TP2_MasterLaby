package server;

import java.util.LinkedList;

public class Model
{
	private int turnNbr = 1;
	private int activePlayer = 0;
	private LinkedList<Player> playerList;
	private boolean gameOver = false;

	public Model()
	{
		playerList = new LinkedList<Player>();
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}

	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}

	public void addPLayer(Player player)
	{
		playerList.add(player);
		activePlayer = playerList.size() - 1;
	}
	
	public void removePLayer()
	{
		playerList.remove(activePlayer);
		activePlayer--;
		
	}
	
	public Player getActivePlayer()
	{
		return playerList.get(activePlayer);
	}
	
	public void nextPlayer()
	{
		activePlayer++;
		
		if (activePlayer + 1 > playerList.size())
		{
			activePlayer = 0;
			turnNbr++;
		}
	}

	public int getNbrPlayer()
	{
		return playerList.size();
	}

	public int getTurnNbr()
	{
		return turnNbr;
	}

}
