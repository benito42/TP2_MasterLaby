package server;

import java.util.LinkedList;

import server.Tile.TILETYPE;

public class Model
{
	private int turnNbr = 1;
	private int activePlayer = 0;
	private LinkedList<Player> playerList;
	private boolean gameOver = false;
	private Tile[][] board = new Tile[7][7];
	private Tile nextTile;

	public Model()
	{
		playerList = new LinkedList<Player>();
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}

	public void setGameOver(boolean gameOver)
	{
		this.gameOver = gameOver;
	}

	public void addPLayer(Player player)
	{
		playerList.add(player);
		activePlayer = playerList.size() - 1;
	}
	
	public void removePLayer()
	{
		playerList.remove(activePlayer);
		activePlayer--;
		
	}
	
	public Player getActivePlayer()
	{
		return playerList.get(activePlayer);
	}
	
	public void nextPlayer()
	{
		activePlayer++;
		
		if (activePlayer + 1 > playerList.size())
		{
			activePlayer = 0;
			turnNbr++;
		}
	}

	public int getNbrPlayer()
	{
		return playerList.size();
	}

	public int getTurnNbr()
	{
		return turnNbr;
	}
	
	public void buildBoard()
	{
		buildTiles();
		//*buildGameForPlayersAndObjectives();
	}
	
	private void buildTiles()
	{		
		//first row
		//rd.dl.rdl.ud.rdl.rd.dl
		Tile tempTile00 = new Tile(TILETYPE.down_right, 0, 0);
		board[0][0] = tempTile00;
		
		Tile tempTile10 = new Tile(TILETYPE.down_left, 1, 0);
		board[1][0] = tempTile10;

		Tile tempTile20 = new Tile(TILETYPE.horizontal_down, 2, 0);
		board[2][0] = tempTile20;

		Tile tempTile30 = new Tile(TILETYPE.vertical, 3, 0);
		board[3][0] = tempTile30;

		Tile tempTile40 = new Tile(TILETYPE.horizontal_down, 4, 0);
		board[4][0] = tempTile40;

		Tile tempTile50 = new Tile(TILETYPE.down_right, 5, 0);
		board[5][0] = tempTile50;

		Tile tempTile60 = new Tile(TILETYPE.down_left, 6, 0);
		board[6][0] = tempTile60;

		//second row
		//ur.ud.rd.dl.ud.rl.rl
		Tile tempTile01 = new Tile(TILETYPE.up_right, 0, 1);
		board[0][1] = tempTile01;

		Tile tempTile11 = new Tile(TILETYPE.vertical, 1, 1);
		board[1][1] = tempTile11;

		Tile tempTile21 = new Tile(TILETYPE.down_right, 2, 1);
		board[2][1] = tempTile21;

		Tile tempTile31 = new Tile(TILETYPE.down_left, 3, 1);
		board[3][1] = tempTile31;

		Tile tempTile41 = new Tile(TILETYPE.vertical, 4, 1);
		board[4][1] = tempTile41;

		Tile tempTile51 = new Tile(TILETYPE.horizontal, 5, 1);
		board[5][1] = tempTile51;

		Tile tempTile61 = new Tile(TILETYPE.up_left, 6, 1);
		board[6][1] = tempTile61;

		//third row
		//urd.rl.urd.rl.rdl.dl.udl
		Tile tempTile02 = new Tile(TILETYPE.vertical_right, 0, 2);
		board[0][2] = tempTile02;

		Tile tempTile12 = new Tile(TILETYPE.horizontal, 1, 2);
		board[1][2] = tempTile12;

		Tile tempTile22 = new Tile(TILETYPE.vertical_right, 2, 2);
		board[2][2] = tempTile22;

		Tile tempTile32 = new Tile(TILETYPE.horizontal, 3, 2);
		board[3][2] = tempTile32;

		Tile tempTile42 = new Tile(TILETYPE.horizontal_down, 4, 2);
		board[4][2] = tempTile42;

		Tile tempTile52 = new Tile(TILETYPE.down_left, 5, 2);
		board[5][2] = tempTile52;

		Tile tempTile62 = new Tile(TILETYPE.vertical_left, 6, 2);
		board[6][2] = tempTile62;

		//forth row
		//rl.rd.ud.ul.ud.ul.rl
		Tile tempTile03 = new Tile(TILETYPE.horizontal, 0, 3);
		board[0][3] = tempTile03;

		Tile tempTile13 = new Tile(TILETYPE.down_right, 1, 3);
		board[1][3] = tempTile13;

		Tile tempTile23 = new Tile(TILETYPE.vertical, 2, 3);
		board[2][3] = tempTile23;

		Tile tempTile33 = new Tile(TILETYPE.up_left, 3, 3);
		board[3][3] = tempTile33;

		Tile tempTile43 = new Tile(TILETYPE.vertical, 4, 3);
		board[4][3] = tempTile43;

		Tile tempTile53 = new Tile(TILETYPE.up_left, 5, 3);
		board[5][3] = tempTile53;

		Tile tempTile63 = new Tile(TILETYPE.horizontal, 6, 3);
		board[6][3] = tempTile63;

		//fifth row
		//urd.ur.url.rl.udl.rl.udl
		Tile tempTile04 = new Tile(TILETYPE.vertical_right, 0, 4);
		board[0][4] = tempTile04;

		Tile tempTile14 = new Tile(TILETYPE.up_right, 1, 4);
		board[1][4] = tempTile14;

		Tile tempTile24 = new Tile(TILETYPE.horizontal_up, 2, 4);
		board[2][4] = tempTile24;

		Tile tempTile34 = new Tile(TILETYPE.horizontal, 3, 4);
		board[3][4] = tempTile34;

		Tile tempTile44 = new Tile(TILETYPE.vertical_left, 4, 4);
		board[4][4] = tempTile44;

		Tile tempTile54 = new Tile(TILETYPE.horizontal, 5, 4);
		board[5][4] = tempTile54;

		Tile tempTile64 = new Tile(TILETYPE.vertical_left, 6, 4);
		board[6][4] = tempTile64;

		//sixth row
		//rd.rl.ud.ur.ul.ud.dl
		Tile tempTile05 = new Tile(TILETYPE.down_right, 0, 5);
		board[0][5] = tempTile05;

		Tile tempTile15 = new Tile(TILETYPE.horizontal, 1, 5);
		board[1][5] = tempTile15;

		Tile tempTile25 = new Tile(TILETYPE.vertical, 2, 5);
		board[2][5] = tempTile25;

		Tile tempTile35 = new Tile(TILETYPE.up_right, 3, 5);
		board[3][5] = tempTile35;

		Tile tempTile45 = new Tile(TILETYPE.up_left, 4, 5);
		board[4][5] = tempTile45;

		Tile tempTile55 = new Tile(TILETYPE.vertical, 5, 5);
		board[5][5] = tempTile55;

		Tile tempTile65 = new Tile(TILETYPE.down_left, 6, 5);
		board[6][5] = tempTile65;

		//seventh row
		//ur.ul.url.ud.url.ur.ul
		Tile tempTile06 = new Tile(TILETYPE.up_right, 0, 6);
		board[0][6] = tempTile06;

		Tile tempTile16 = new Tile(TILETYPE.up_left, 1, 6);
		board[1][6] = tempTile16;

		Tile tempTile26 = new Tile(TILETYPE.horizontal_up, 2, 6);
		board[2][6] = tempTile26;

		Tile tempTile36 = new Tile(TILETYPE.vertical, 3, 6);
		board[3][6] = tempTile36;

		Tile tempTile46 = new Tile(TILETYPE.horizontal_up, 4, 6);
		board[4][6] = tempTile46;

		Tile tempTile56 = new Tile(TILETYPE.up_right, 5, 6);
		board[5][6] = tempTile56;

		Tile tempTile66 = new Tile(TILETYPE.up_left, 6, 6);
		board[6][6] = tempTile66;
		
		this.nextTile = new Tile(TILETYPE.horizontal_up, -1, -1);
	}
	
	public Tile getTile(int xPosition, int yPosition)
	{
		return board[xPosition][yPosition];
	}
	
	private void buildPlayersAndObjectives(Player[] players)
	{
		
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
		
		
	}
}
