package server;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import client.IClientGameController;
import server.Tile.TILETYPE;

public class Model
{
	private int turnNbr = 1;
	private int activePlayer = 0;
	private LinkedList<Player> playerList;
	private final Map<String, IClientGameController> observers = new HashMap<String, IClientGameController>();
	private boolean isGameOver = false;
	private Tile[][] board = new Tile[7][7];
	private Tile nextTile = new Tile(TILETYPE.horizontal_up, -1, -1);

	public Model()
	{
		this.playerList = new LinkedList<Player>();
	}
	
	public boolean isGameOver()
	{
		return this.isGameOver;
	}

	public void setGameOver(boolean gameOver)
	{
		this.isGameOver = gameOver;
	}
	
	public Tile[][] getBoard()
	{
		return this.board;
	}

	public void setCurrentTileOnActivePlayer(Tile tile)
	{
		Player playerTemp = this.playerList.get(this.activePlayer);
		playerTemp.getCurrentTile().playerList.remove(playerTemp);
		playerTemp.setCurrentTile(tile);
		playerTemp.getCurrentTile().playerList.add(playerTemp);
		
		this.checkIfObjectiveReached(playerTemp);
		
		this.notifyNewBoard();
		
	}
	
	private void checkIfObjectiveReached(Player player)
	{
		Objective obj = player.getCurrentTile().getObjective();
		if(obj != null)
		{
			for(int i = 0; i < 3; i++)
			{
				if(player.getObjectiveList()[i] == obj)
				{
					obj.setObjectiveReached(true);
					if(this.checkIfWinningTurn(player.getObjectiveList()));
					{
						//TODO: Programmer une fin de partie plus développée
						System.exit(0);
					}
				}
			}
		}
	}
	
	private boolean checkIfWinningTurn(Objective[] objectives)
	{
		for(int j = 0; j < objectives.length; j++)
		{
			if(!objectives[j].isObjectiveReached())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void addPlayer(Player player)
	{
		this.playerList.add(player);
		this.buildGameForPlayersAndObjectives();
		//this.activePlayers = playerList.size() - 1;
	}
	
	public void removePlayer()
	{
		this.playerList.remove(this.activePlayer);
		this.nextPlayer();
	}
	
	public Player getActivePlayer()
	{
		return this.playerList.get(this.activePlayer);
	}
	
	public void nextPlayer()
	{
		this.activePlayer++;
		
		if (this.activePlayer + 1 > this.playerList.size())
		{
			this.activePlayer = 0;
			this.turnNbr++;
		}
		
		this.notifyTurnChange();
	}

	public int getNbrPlayer()
	{
		return this.playerList.size();
	}

	public int getTurnNbr()
	{
		return this.turnNbr;
	}
	
	public void buildBoard()
	{
		this.buildTiles();
		this.buildGameForPlayersAndObjectives();
	}
	
	private void buildTiles()
	{		
		//first row
		//rd.dl.rdl.ud.rdl.rd.dl
		Tile tempTile00 = new Tile(TILETYPE.down_right, 0, 0);
		this.board[0][0] = tempTile00;
		
		Tile tempTile10 = new Tile(TILETYPE.down_left, 1, 0);
		this.board[1][0] = tempTile10;

		Tile tempTile20 = new Tile(TILETYPE.horizontal_down, 2, 0);
		this.board[2][0] = tempTile20;

		Tile tempTile30 = new Tile(TILETYPE.vertical, 3, 0);
		this.board[3][0] = tempTile30;

		Tile tempTile40 = new Tile(TILETYPE.horizontal_down, 4, 0);
		this.board[4][0] = tempTile40;

		Tile tempTile50 = new Tile(TILETYPE.down_right, 5, 0);
		this.board[5][0] = tempTile50;

		Tile tempTile60 = new Tile(TILETYPE.down_left, 6, 0);
		this.board[6][0] = tempTile60;

		//second row
		//ur.ud.rd.dl.ud.rl.rl
		Tile tempTile01 = new Tile(TILETYPE.up_right, 0, 1);
		this.board[0][1] = tempTile01;

		Tile tempTile11 = new Tile(TILETYPE.vertical, 1, 1);
		this.board[1][1] = tempTile11;

		Tile tempTile21 = new Tile(TILETYPE.down_right, 2, 1);
		this.board[2][1] = tempTile21;

		Tile tempTile31 = new Tile(TILETYPE.down_left, 3, 1);
		this.board[3][1] = tempTile31;

		Tile tempTile41 = new Tile(TILETYPE.vertical, 4, 1);
		this.board[4][1] = tempTile41;

		Tile tempTile51 = new Tile(TILETYPE.horizontal, 5, 1);
		this.board[5][1] = tempTile51;

		Tile tempTile61 = new Tile(TILETYPE.up_left, 6, 1);
		this.board[6][1] = tempTile61;

		//third row
		//urd.rl.urd.rl.rdl.dl.udl
		Tile tempTile02 = new Tile(TILETYPE.vertical_right, 0, 2);
		this.board[0][2] = tempTile02;

		Tile tempTile12 = new Tile(TILETYPE.horizontal, 1, 2);
		this.board[1][2] = tempTile12;

		Tile tempTile22 = new Tile(TILETYPE.vertical_right, 2, 2);
		this.board[2][2] = tempTile22;

		Tile tempTile32 = new Tile(TILETYPE.horizontal, 3, 2);
		this.board[3][2] = tempTile32;

		Tile tempTile42 = new Tile(TILETYPE.horizontal_down, 4, 2);
		this.board[4][2] = tempTile42;

		Tile tempTile52 = new Tile(TILETYPE.down_left, 5, 2);
		this.board[5][2] = tempTile52;

		Tile tempTile62 = new Tile(TILETYPE.vertical_left, 6, 2);
		this.board[6][2] = tempTile62;

		//forth row
		//rl.rd.ud.ul.ud.ul.rl
		Tile tempTile03 = new Tile(TILETYPE.horizontal, 0, 3);
		this.board[0][3] = tempTile03;

		Tile tempTile13 = new Tile(TILETYPE.down_right, 1, 3);
		this.board[1][3] = tempTile13;

		Tile tempTile23 = new Tile(TILETYPE.vertical, 2, 3);
		this.board[2][3] = tempTile23;

		Tile tempTile33 = new Tile(TILETYPE.up_left, 3, 3);
		this.board[3][3] = tempTile33;

		Tile tempTile43 = new Tile(TILETYPE.vertical, 4, 3);
		this.board[4][3] = tempTile43;

		Tile tempTile53 = new Tile(TILETYPE.up_left, 5, 3);
		this.board[5][3] = tempTile53;

		Tile tempTile63 = new Tile(TILETYPE.horizontal, 6, 3);
		this.board[6][3] = tempTile63;

		//fifth row
		//urd.ur.url.rl.udl.rl.udl
		Tile tempTile04 = new Tile(TILETYPE.vertical_right, 0, 4);
		this.board[0][4] = tempTile04;

		Tile tempTile14 = new Tile(TILETYPE.up_right, 1, 4);
		this.board[1][4] = tempTile14;

		Tile tempTile24 = new Tile(TILETYPE.horizontal_up, 2, 4);
		this.board[2][4] = tempTile24;

		Tile tempTile34 = new Tile(TILETYPE.horizontal, 3, 4);
		this.board[3][4] = tempTile34;

		Tile tempTile44 = new Tile(TILETYPE.vertical_left, 4, 4);
		this.board[4][4] = tempTile44;

		Tile tempTile54 = new Tile(TILETYPE.horizontal, 5, 4);
		this.board[5][4] = tempTile54;

		Tile tempTile64 = new Tile(TILETYPE.vertical_left, 6, 4);
		this.board[6][4] = tempTile64;

		//sixth row
		//rd.rl.ud.ur.ul.ud.dl
		Tile tempTile05 = new Tile(TILETYPE.down_right, 0, 5);
		this.board[0][5] = tempTile05;

		Tile tempTile15 = new Tile(TILETYPE.horizontal, 1, 5);
		this.board[1][5] = tempTile15;

		Tile tempTile25 = new Tile(TILETYPE.vertical, 2, 5);
		this.board[2][5] = tempTile25;

		Tile tempTile35 = new Tile(TILETYPE.up_right, 3, 5);
		this.board[3][5] = tempTile35;

		Tile tempTile45 = new Tile(TILETYPE.up_left, 4, 5);
		this.board[4][5] = tempTile45;

		Tile tempTile55 = new Tile(TILETYPE.vertical, 5, 5);
		this.board[5][5] = tempTile55;

		Tile tempTile65 = new Tile(TILETYPE.down_left, 6, 5);
		this.board[6][5] = tempTile65;

		//seventh row
		//ur.ul.url.ud.url.ur.ul
		Tile tempTile06 = new Tile(TILETYPE.up_right, 0, 6);
		this.board[0][6] = tempTile06;

		Tile tempTile16 = new Tile(TILETYPE.up_left, 1, 6);
		this.board[1][6] = tempTile16;

		Tile tempTile26 = new Tile(TILETYPE.horizontal_up, 2, 6);
		this.board[2][6] = tempTile26;

		Tile tempTile36 = new Tile(TILETYPE.vertical, 3, 6);
		this.board[3][6] = tempTile36;

		Tile tempTile46 = new Tile(TILETYPE.horizontal_up, 4, 6);
		this.board[4][6] = tempTile46;

		Tile tempTile56 = new Tile(TILETYPE.up_right, 5, 6);
		this.board[5][6] = tempTile56;

		Tile tempTile66 = new Tile(TILETYPE.up_left, 6, 6);
		this.board[6][6] = tempTile66;
	}
	
	public Tile getTile(int xPosition, int yPosition)
	{
		return this.board[xPosition][yPosition];
	}
	
	public void buildGameForPlayersAndObjectives()
	{
		for (int i = 0; i < playerList.size(); i ++)
		{
			board[playerList.get(i).getCurrentTile().getPositionX()]
					[playerList.get(i).getCurrentTile().getPositionY()].addPlayer(playerList.get(i));
			/*Tile tempTile = board[playerList.get(i).getCurrentTile().getPositionX()][playerList.get(i).getCurrentTile().getPositionY()];
			Player tempPlayer = playerList.get(i);
			tempTile.addPlayer(tempPlayer);*/
			
			switch (i)
			{
			case 0:
				board[6][2].setObjective(playerList.get(i).getObjectiveList()[0]);
				board[4][4].setObjective(playerList.get(i).getObjectiveList()[1]);
				board[2][6].setObjective(playerList.get(i).getObjectiveList()[2]);
				break;
			case 1:
				board[4][6].setObjective(playerList.get(i).getObjectiveList()[0]);
				board[2][4].setObjective(playerList.get(i).getObjectiveList()[1]);
				board[0][2].setObjective(playerList.get(i).getObjectiveList()[2]);
				break;
			case 2:
				board[0][4].setObjective(playerList.get(i).getObjectiveList()[0]);
				board[2][2].setObjective(playerList.get(i).getObjectiveList()[1]);
				board[4][0].setObjective(playerList.get(i).getObjectiveList()[2]);
				break;
			case 3:
				board[2][0].setObjective(playerList.get(i).getObjectiveList()[0]);
				board[4][2].setObjective(playerList.get(i).getObjectiveList()[1]);
				board[6][4].setObjective(playerList.get(i).getObjectiveList()[2]);
				break;
			
			}
		}
	}
	
	public Tile getNextTile()
	{
		return this.nextTile;
	}

	public void shiftTiles(int index, Direction direction)
	{
		Tile tempTile;
		int x = 0;
		int y = 0;
		
		switch(direction)
		{
		case LEFT:
				y = index;
				x = 0;
				
				tempTile = this.getTile(x, y);
				tempTile.setPositionX(-1);
				tempTile.setPositionY(-1);
				
				for(x = 0; x < 6; x++)
				{
					this.board[x][y] = this.board[x + 1][y];
					this.board[x][y].setPositionX(x);
				}
				
				this.nextTile.setPositionX(x);
				this.nextTile.setPositionY(y);
				this.board[x][y] = this.nextTile;
				this.nextTile = tempTile;
				
			break;
		case DOWN:
				y = 6;
				x = index;
				
				tempTile = this.getTile(x, y);
				tempTile.setPositionX(-1);
				tempTile.setPositionY(-1);
				
				for(y = 6; y >= 1; y--)
				{
					this.board[x][y] = this.board[x][y - 1];
					this.board[x][y].setPositionX(x);
				}
				
				this.nextTile.setPositionX(x);
				this.nextTile.setPositionY(y);
				this.board[x][y] = this.nextTile;
				this.nextTile = tempTile;
				
			break;
		case RIGHT:
				y = index;
				x = 6;
				
				tempTile = this.getTile(x, y);
				tempTile.setPositionX(-1);
				tempTile.setPositionY(-1);
				
				for(x = 6; x >= 1; x--)
				{
					this.board[x][y] = this.board[x - 1][y];
					this.board[x][y].setPositionX(x);
				}
				
				this.nextTile.setPositionX(x);
				this.nextTile.setPositionY(y);
				this.board[x][y] = this.nextTile;
				this.nextTile = tempTile;
				
			break;
		case UP:
				y = 0;
				x = index;
				
				tempTile = this.getTile(x, y);
				tempTile.setPositionX(-1);
				tempTile.setPositionY(-1);
				
				for(y = 0; y < 6; y++)
				{
					this.board[x][y] = this.board[x][y + 1];
					this.board[x][y].setPositionX(x);
				}
				
				this.nextTile.setPositionX(x);
				this.nextTile.setPositionY(y);
				this.board[x][y] = this.nextTile;
				this.nextTile = tempTile;
				
			break;
		default:
				y = -1;
				x = -1;
			break;
		}
		
		this.notifyNewBoard();
	}
	
	private void notifyNewBoard()
	{
		new Thread(new BoardUpdater()).start();
	}
	
	private void notifyTurnChange()
	{
		new Thread(new TurnUpdater()).start();
	}

	private class BoardUpdater implements Runnable
	{
		@Override
		public void run()
		{
			Tile[][] newBoard = board;
			Tile newNextTile = nextTile;

			Iterator<String> observersIterator = observers.keySet().iterator();
			while (observersIterator.hasNext())
			{
				String clientID = observersIterator.next();
				IClientGameController obs = observers.get(clientID);
				if (obs == null)
					observersIterator.remove();
				else
				{
					try
					{
						obs.updateBoard(newBoard);
						obs.updateNextTile(newNextTile);
					}
					catch (UndeclaredThrowableException ute)
					{// The observer is unreachable, remove it right away (we could be more flexible here and require a certain threshold of failure before kicking him)
						observersIterator.remove();
					}
				}
			}

		}
	}
	
	public String[][] getTilesPaths()
	{
		String[][] tilesPaths = new String[7][7];
		
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				tilesPaths[i][j] = this.board[i][j].getPath();
			}
		}
		
		return tilesPaths;
	}
	
	private class TurnUpdater implements Runnable
	{
		@Override
		public void run()
		{
			int noActivePlayer = activePlayer;

			Iterator<String> observersIterator = observers.keySet().iterator();
			while (observersIterator.hasNext())
			{
				String clientID = observersIterator.next();
				IClientGameController obs = observers.get(clientID);
				if (obs == null)
					observersIterator.remove();
				else
				{
					try
					{
						obs.updateTurnChange(noActivePlayer);
					}
					catch (UndeclaredThrowableException ute)
					{// The observer is unreachable, remove it right away (we could be more flexible here and require a certain threshold of failure before kicking him)
						observersIterator.remove();
					}
				}
			}

		}
	}
	
	public void addObserver(String observerID, IClientGameController observer)
	{
		this.observers.put(observerID, observer);
		
		new BoardNotifyer(observer, this.board, this.nextTile, this.observers.size() - 1, Thread.currentThread()).start();
		new TurnNotifyer(observer, this.activePlayer, Thread.currentThread()).start();
		
		this.newPlayer(observerID);
	}
	
	private void newPlayer(String observerID)
	{
		Player newPlayer;
		
		switch(this.playerList.size())
		{
		case 0 :
			newPlayer = new Player("Player " + observerID, Avatar.commando, this.getBoard()[0][0], 1);
			break;
		case 1 :
			newPlayer = new Player("Player " + observerID, Avatar.darkVador, this.getBoard()[6][0], 2);
			break;
		case 2 :
			newPlayer = new Player("Player " + observerID, Avatar.pirate, this.getBoard()[6][6], 3);
			break;
		case 3 :
			newPlayer = new Player("Player " + observerID, Avatar.viking, this.getBoard()[0][6], 4);
			break;
		default:
			newPlayer = null;
			break;
		}
		
		this.addPlayer(newPlayer);
	}
	
	private class BoardNotifyer extends Thread
	{
		private final IClientGameController obs;
		private final Tile[][] newBoard;
		private final Tile newNextTile;
		private final Thread father;
		private final int noActivePlayer;

		private BoardNotifyer(IClientGameController obs, Tile[][] newBoard, Tile newNextTile, int noActivePlayer, Thread father)
		{
			this.obs = obs;
			this.newBoard = newBoard;
			this.newNextTile = newNextTile;
			this.father = father;
			this.noActivePlayer = noActivePlayer;
		}

		@Override
		public void run()
		{
			try
			{
				// We wait for the parent thread to terminate (the connection from the client to close) before notifying (and thus connecting back to) the client.
				this.father.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				obs.updateBoard(this.newBoard);
				obs.updateNextTile(this.newNextTile);
				obs.updateNoPlayer(this.noActivePlayer);
			}
		}
	}
	
	private class TurnNotifyer extends Thread
	{
		private final IClientGameController obs;
		private final Thread father;
		private final int noActivePlayer;

		private TurnNotifyer(IClientGameController obs, int noActivePlayer, Thread father)
		{
			this.obs = obs;
			this.father = father;
			this.noActivePlayer = noActivePlayer;
		}

		@Override
		public void run()
		{
			try
			{
				// We wait for the parent thread to terminate (the connection from the client to close) before notifying (and thus connecting back to) the client.
				this.father.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				obs.updateTurnChange(this.noActivePlayer);
			}
		}
	}

	public void unregisterObserver(String clientID)
	{
		this.observers.remove(clientID);
	}
	
	public void kickPlayer()
	{
		Player playerToDelete = this.playerList.get(this.activePlayer);
		
		playerToDelete.getCurrentTile().playerList.remove(playerToDelete);
		playerToDelete.setCurrentTile(null);
		
		this.removePlayer();
		this.notifyNewBoard();
	}
}
