package server;


public class GameController implements IGameController
{

	private Model model;
	
	public GameController()
	{
		model = new Model();
		buildBoard();
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
		model.setGameOver(true);
		System.out.println("fin de partie");
	}

	@Override
	public void moveUp()
	{
		Player activePlayer = model.getActivePlayer();
		
		if (activePlayer.getPositionY() - 1 >= 0)
		{
			if (activePlayer.getCurrentTile().goUp() && 
					model.getTile(activePlayer.getPositionX(),  activePlayer.getPositionY()).goDown())
			{
				activePlayer.moveUp();
			}
		}
	}

	@Override
	public void moveDown()
	{
		Player activePlayer = model.getActivePlayer();
		
		if (activePlayer.getPositionY() + 1 <= 6)
		{
			if (activePlayer.getCurrentTile().goDown() && 
					model.getTile(activePlayer.getPositionX(),  activePlayer.getPositionY()).goUp())
			{
				activePlayer.moveDown();
			}
		}
	}

	@Override
	public void moveLeft()
	{
		Player activePlayer = model.getActivePlayer();
		
		if (activePlayer.getPositionX() - 1 >= 0)
		{
			if (activePlayer.getCurrentTile().goLeft() && 
					model.getTile(activePlayer.getPositionX(),  activePlayer.getPositionY()).goRight())
			{
				activePlayer.moveLeft();
			}
		}
		
	}

	@Override
	public void moveRight()
	{
		Player activePlayer = model.getActivePlayer();
		
		if (activePlayer.getPositionX() + 1 <= 6)
		{
			if (activePlayer.getCurrentTile().goRight() && 
					model.getTile(activePlayer.getPositionX(),  activePlayer.getPositionY()).goLeft())
			{
				activePlayer.moveRight();
			}
		}
		
	}

	public Model getModel()
	{
		return model;
	}

	public void buildBoard()
	{
		model.buildBoard();
	}

	
	
}
