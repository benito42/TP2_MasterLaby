package server;


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
		model.setGameOver(true);
		System.out.println("fin de partie");
	}

	@Override
	public void moveUp()
	{
		if (model.getActivePlayer().getPositionY() - 1 >= 0)
		{
			model.getActivePlayer().moveUp();
		}
		
		
	}

	@Override
	public void moveDown()
	{
		if (model.getActivePlayer().getPositionY() + 1 <= 6)
		{
			model.getActivePlayer().moveDown();
		}
	}

	@Override
	public void moveLeft()
	{
		if (model.getActivePlayer().getPositionX() - 1 >= 0)
		{
			model.getActivePlayer().moveLeft();
		}
		
	}

	@Override
	public void moveRight()
	{
		if (model.getActivePlayer().getPositionX() + 1 <= 6)
		{
			model.getActivePlayer().moveRight();
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
