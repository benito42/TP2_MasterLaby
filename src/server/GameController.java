package controller;

import client.Model;
import client.Player;

public class GameController implements IGameController
{

	private Model model;
	
	public GameController()
	{
		model = new Model();
	}

	@Override
	public void addPlayer(Player player)
	{
		model.addPLayer(player);
		
	}

	@Override
	public void removePlayer()
	{
		model.removePLayer();
		
		if (model.getNbrPlayer() < 2)
		{
			gameOver();
		}
		
	}

	public void nextPlayer()
	{
		model.nextPlayer();
	}

	public void gameOver()
	{
		System.out.println("fin de partie");
		
	}

	@Override
	public void moveUp()
	{
		model.getActivePlayer().moveUp();
		
	}

	@Override
	public void moveDown()
	{
		model.getActivePlayer().moveDown();
	}

	@Override
	public void moveLeft()
	{
		model.getActivePlayer().moveLeft();
	}

	@Override
	public void moveRight()
	{
		model.getActivePlayer().moveRight();
	}
	
	
}
