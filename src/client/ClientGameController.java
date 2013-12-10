package client;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import server.Direction;
import server.GameController;
import server.IGameController;
import server.Tile;

import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

public class ClientGameController implements IClientGameController
{
	public static final String SERVER_IP_ADRESS = "127.0.0.1";

	private IGameController myServiceCaller;
	private Client client;
	private IGameView view;

	public ClientGameController()
	{
		this.view = new GameView(this);
		
		this.connectToServer();
		
		this.view.showGameView();
		
		this.view.updateBoard(this.getBoard());
		this.view.updateNextTile(this.getNewNextTile());
	}
	
	private void connectToServer()
	{
		try
		{
			CallHandler callHandler = new CallHandler();

			this.client = new Client(SERVER_IP_ADRESS, GameController.SERVER_PORT, callHandler);

			this.myServiceCaller = (IGameController) this.client.getGlobal(IGameController.class);

			callHandler.registerGlobal(IClientGameController.class, this);
			// callHandler.exportObject(ModelObserver.class, this);

			this.myServiceCaller.addObserver(this);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (LipeRMIException e)
		{
			e.printStackTrace();
		}
	}

	public void closeRequest()
	{
		this.myServiceCaller.unregisterObserver();
		try
		{
			this.client.close();

		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	private class ServerContacterShiftTiles extends TimerTask
	{
		private int index;
		private Direction direction;
		
		public ServerContacterShiftTiles(int index, Direction direction)
		{
			this.index = index;
			this.direction = direction;
		}
		
		@Override
		public void run()
		{
			myServiceCaller.shiftTiles(index, direction);
		}
	}
	
	private class ServerContacterGetBoard extends TimerTask
	{
		private Tile[][] newBoard;
		
		@Override
		public void run()
		{
			this.newBoard = myServiceCaller.getBoard();
		}
		
		public Tile[][] getNewBoard()
		{
			return this.newBoard;
		}
	}
	
	private class ServerContacterGetNextTile extends TimerTask
	{
		private Tile newNextTile;
		
		@Override
		public void run()
		{
			this.newNextTile = myServiceCaller.getNextTile();
		}
		
		public Tile getNewNextTile()
		{
			return this.newNextTile;
		}
	}
	
	private class ServerContacterEndTurn extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.nextPlayer();
		}
	}
	

	@Override
	public Tile[][] getBoard() 
	{
		return new ServerContacterGetBoard().getNewBoard();
	}
	
	@Override
	public Tile getNewNextTile() 
	{
		return new ServerContacterGetNextTile().getNewNextTile();
	}
	
	@Override
	public void nextPlayer()
	{
		new Timer().schedule(new ServerContacterEndTurn(), 1);
	}
	
	@Override
	public void shiftTiles(int index, Direction direction)
	{
		new Timer().schedule(new ServerContacterShiftTiles(index, direction), 1);
	}
	
	@Override
	public void updateBoard(Tile[][] newBoard) 
	{
		this.view.updateBoard(newBoard);
	}

	@Override
	public void updateNextTile(Tile nextTile) 
	{
		this.view.updateNextTile(nextTile);
	}

	@Override
	public void updateNoPlayer(int noPlayer) 
	{
		this.view.updateNoPlayer(noPlayer);
	}

	@Override
	public void updateTurnChange(int noActivePlayer) 
	{
		this.view.updateTurnChange(noActivePlayer);
	}

	private class ServerContacterMoveUp extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.moveUp();
		}
	}
	

	private class ServerContacterMoveDown extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.moveDown();
		}
	}
	

	private class ServerContacterMoveLeft extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.moveLeft();
		}
	}
	

	private class ServerContacterMoveRight extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.moveRight();
		}
	}
	
	private class ServerContacterQuit extends TimerTask
	{
		@Override
		public void run()
		{
			myServiceCaller.unregisterObserver();
		}
	}

	@Override
	public void moveUp() 
	{
		new Timer().schedule(new ServerContacterMoveUp(), 1);
	}

	@Override
	public void moveDown() 
	{
		new Timer().schedule(new ServerContacterMoveDown(), 1);
	}

	@Override
	public void moveLeft() 
	{
		new Timer().schedule(new ServerContacterMoveLeft(), 1);
	}

	@Override
	public void moveRight() 
	{
		new Timer().schedule(new ServerContacterMoveRight(), 1);
	}

	@Override
	public void quitGame() 
	{
		new Timer().schedule(new ServerContacterQuit(), 1);
	}
}
