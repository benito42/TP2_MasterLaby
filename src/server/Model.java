package server;

import java.util.LinkedList;

public class Model
{
	private int turnNbr = 1;
	private int activePLayer = 0;
	private LinkedList<Player> playerList;

	public Model()
	{
		
	}
	
	public void addPLayer(Player player)
	{
		playerList.add(player);
	}
	
	public void removePLayer()
	{
		playerList.remove(activePLayer);
		
	}
	
	public Player getActivePlayer()
	{
		return playerList.get(activePLayer);
	}
	
	public void nextPlayer()
	{
		activePLayer++;
		
		if (activePLayer + 1 > playerList.size())
		{
			activePLayer = 0;
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
	
	public void getGameState()
	{
		System.out.println("turn number: " + turnNbr);
		System.out.println("number of player: " + playerList.size());
		System.out.println("activeplayer: " + activePLayer);
	}
}
