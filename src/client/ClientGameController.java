package client;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import server.Direction;
import server.GameController;
import server.IGameController;

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
		private String[][] newBoard;
		
		@Override
		public void run()
		{
			this.newBoard = myServiceCaller.getBoard();
		}
		
		public String[][] getNewBoard()
		{
			return this.newBoard;
		}
	}
	
	private class ServerContacterGetNextTile extends TimerTask
	{
		private String newNextTile;
		
		@Override
		public void run()
		{
			this.newNextTile = myServiceCaller.getNextTile();
		}
		
		public String getNewNextTile()
		{
			return this.newNextTile;
		}
	}
	

	@Override
	public String[][] getBoard() 
	{
		return new ServerContacterGetBoard().getNewBoard();
	}
	
	@Override
	public String getNewNextTile() 
	{
		return new ServerContacterGetNextTile().getNewNextTile();
	}
	
	@Override
	public void nextPlayer()
	{
		this.myServiceCaller.nextPlayer();
	}
	
	@Override
	public void shiftTiles(int index, Direction direction)
	{
		new Timer().schedule(new ServerContacterShiftTiles(index, direction), 1);
	}
	
	@Override
	public void updateBoard(String[][] newBoard) 
	{
		this.view.updateBoard(newBoard);
	}

	@Override
	public void updateNextTile(String nextTile) 
	{
		this.view.updateNextTile(nextTile);
	}
}
