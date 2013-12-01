package server;

import client.GameView;
import client.IGameView;


public class GameController implements IGameController
{

	private Model model;
	private IGameView gameView;
	
	public GameController()
	{
		this.model = new Model();
		this.buildBoard();
		this.gameView = new GameView(this);
		this.model.addView(gameView);
		this.gameView.showGameView();
	}

	@Override
	public void addPlayer(Player player)
	{
		this.model.addPlayer(player);
		
	}

	@Override
	public void removePlayer()
	{
		this.model.removePlayer();
		
		if (this.model.getNbrPlayer() < 2)
		{
			this.gameOver();
		}
		
	}

	public void nextPlayer()
	{
		this.model.nextPlayer();
	}
	
	@Override
	public void addView(IGameView newView)
	{
		this.model.addView(newView);
		
	}

	public void gameOver()
	{
		this.model.setGameOver(true);
		System.out.println("fin de partie");
	}

	@Override
	public void moveUp()
	{
		Tile activePlayerTile = this.model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionY() - 1 >= 0)
		{
			if (activePlayerTile.goUp() && 
					this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() - 1).goDown())
			{
				this.model.getActivePlayer().setCurrentTile(this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() - 1));
			}
		}
	}

	@Override
	public void moveDown()
	{
		Tile activePlayerTile = this.model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionY() + 1 <= 6)
		{
			if (activePlayerTile.goDown() && 
					this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() + 1).goUp())
			{
				this.model.getActivePlayer().setCurrentTile(this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() + 1));
			}
		}
	}

	@Override
	public void moveLeft()
	{
		Tile activePlayerTile = this.model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionX() - 1 >= 0)
		{
			if (activePlayerTile.goLeft() && 
					this.model.getTile(activePlayerTile.getPositionX() - 1,  activePlayerTile.getPositionY()).goRight())
			{
				this.model.getActivePlayer().setCurrentTile(this.model.getTile(activePlayerTile.getPositionX() - 1,  activePlayerTile.getPositionY()));
			}
		}
		
	}

	@Override
	public void moveRight()
	{
		Tile activePlayerTile = this.model.getActivePlayer().getCurrentTile();
		
		if (activePlayerTile.getPositionX() + 1 <= 6)
		{
			if (activePlayerTile.goRight() && 
					this.model.getTile(activePlayerTile.getPositionX() + 1,  activePlayerTile.getPositionY()).goLeft())
			{
				this.model.getActivePlayer().setCurrentTile(this.model.getTile(activePlayerTile.getPositionX() + 1,  activePlayerTile.getPositionY()));
			}
		}
		
	}

	public Model getModel()
	{
		return this.model;
	}

	public void buildBoard()
	{
		this.model.buildBoard();
	}
	
	@Override
	public void shiftTiles(int index, Direction direction)
	{
		this.model.shiftTiles(index, direction);
	}
	
}
