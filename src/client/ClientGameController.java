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
	private static final long serialVersionUID = 7471895685769620206L;

	public static final String SERVER_IP_ADRESS = "127.0.0.1";

	private IGameController myServiceCaller;
	private Client client;
	private final IGameView view;

	public ClientGameController()
	{
		this.connectToServer();
		
		this.view = new GameView(this);
		this.view.showGameView();
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

	private class ServerContacter extends TimerTask
	{
		private int index;
		private Direction direction;
		
		public ServerContacter(int index, Direction direction)
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
	
	@Override
	public void nextPlayer()
	{
		this.myServiceCaller.nextPlayer();
	}
	
	@Override
	public void shiftTiles(int index, Direction direction)
	{
		new Timer().schedule(new ServerContacter(index, direction), 1);
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
	public Tile[][] getBoard() 
	{
		return this.myServiceCaller.getBoard();
	}

	@Override
	public Tile getNextTile() 
	{
		return this.myServiceCaller.getNextTile();
	}
}
