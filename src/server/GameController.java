package server;

import java.io.IOException;
import java.net.Socket;

import client.IClientGameController;

import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.handler.CallLookup;
import net.sf.lipermi.net.IServerListener;
import net.sf.lipermi.net.Server;

public class GameController extends Server implements IGameController
{
	public static final int SERVER_PORT = 12345;
	private final Model model;
	
	public GameController()
	{
		this.model = new Model();
		this.buildBoard();
		
		this.connectServer();
	}
	
	public void connectServer()
	{
		CallHandler callHandler = new CallHandler();
		try
		{
			callHandler.registerGlobal(IGameController.class, this);

			this.addServerListener(new MyServerListener());

			this.bind(SERVER_PORT, callHandler);
		}
		catch (LipeRMIException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
	public void addObserver(IClientGameController newObserver)
	{
		Socket socket = CallLookup.getCurrentSocket();
		String clientID = getClientID(socket);
		this.model.addObserver(clientID, newObserver);
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
				this.model.setCurrentTileOnActivePlayer(this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() - 1));
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
				this.model.setCurrentTileOnActivePlayer(this.model.getTile(activePlayerTile.getPositionX(),  activePlayerTile.getPositionY() + 1));
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
				this.model.setCurrentTileOnActivePlayer(this.model.getTile(activePlayerTile.getPositionX() - 1,  activePlayerTile.getPositionY()));
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
				this.model.setCurrentTileOnActivePlayer(this.model.getTile(activePlayerTile.getPositionX() + 1,  activePlayerTile.getPositionY()));
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
	
	@Override
	public Tile getNextTile()
	{
		return this.model.getNextTile();
	}
	
	@Override
	public Tile[][] getBoard()
	{
		return this.model.getBoard();
	}
	
	private class MyServerListener implements IServerListener
	{

		@Override
		public void clientConnected(Socket socket)
		{
			// System.out.println("Client connected: " + socket.getInetAddress() + ":" + socket.getPort());
		}

		@Override
		public void clientDisconnected(Socket socket)
		{
			// This is a fail-safe in case a client terminates the connection without properly unregistering.
			unregisterObserver(socket);
		}
	}

	@Override
	public void unregisterObserver()
	{
		Socket socket = CallLookup.getCurrentSocket();
		unregisterObserver(socket);
	}

	private void unregisterObserver(Socket socket)
	{
		String clientID = getClientID(socket);
		this.model.unregisterObserver(clientID);
	}

	private String getClientID(Socket socket)
	{
		String clientID = socket.getInetAddress() + ":" + socket.getPort();
		return clientID;
	}
}
