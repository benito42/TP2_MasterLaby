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
		Tile activePlayerTile = model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionY() - 1 >= 0)
		{
			if (activePlayerTile.goUp() && 
					model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() - 1).goDown())
			{
				model.getActivePlayer().setCurrentTile(model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() - 1));
			}
		}
	}

	@Override
	public void moveDown()
	{
		Tile activePlayerTile = model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionY() + 1 <= 6)
		{
			if (activePlayerTile.goDown() && 
					model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() + 1).goUp())
			{
				model.getActivePlayer().setCurrentTile(model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() + 1));
			}
		}
	}

	@Override
	public void moveLeft()
	{
		Tile activePlayerTile = model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionX() - 1 >= 0)
		{
			if (activePlayerTile.goLeft() && 
					model.getTile(activePlayerTile.getPositionX() - 1,  activePlayerTile.getPositionY()).goRight())
			{
				model.getActivePlayer().setCurrentTile(model.getTile(activePlayerTile.getPositionX() - 1,  activePlayerTile.getPositionY()));
			}
		}
		
	}

	@Override
	public void moveRight()
	{
		Tile activePlayerTile = model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionX() + 1 <= 6)
		{
			if (activePlayerTile.goRight() && 
					model.getTile(activePlayerTile.getPositionX() + 1,  activePlayerTile.getPositionY()).goLeft())
			{
				model.getActivePlayer().setCurrentTile(model.getTile(activePlayerTile.getPositionX() + 1,  activePlayerTile.getPositionY()));
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
