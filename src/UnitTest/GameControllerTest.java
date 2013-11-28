package UnitTest;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import server.Direction;
import server.GameController;
import server.Model;
import server.Player;
import server.Tile;
import server.Tile.TILETYPE;

public class GameControllerTest
{
	public Player p1;
	public Player p2;
	public Player p3;
	public Player p4;
	public GameController controller;
	
	@Before
	public void setUp() throws Exception
	{
		controller = new GameController();
		p1 = new Player(controller.getModel().getTile(0,  0));
		p2 = new Player(controller.getModel().getTile(6,  0));
		p3 = new Player(controller.getModel().getTile(6,  6));
		p4 = new Player(controller.getModel().getTile(0,  6));
		
	}
	
	@Test
	public void addPlayerTest()
	{		
		controller.addPlayer(p1);
		
		assertEquals(1, controller.getModel().getNbrPlayer());
	}
	
	@Test
	public void removePlayerTest()
	{
		controller.addPlayer(p1);
		controller.addPlayer(p2);
		controller.addPlayer(p3);
		controller.removePlayer();
		
		assertEquals(2, controller.getModel().getNbrPlayer());
		assertEquals(p2, controller.getModel().getActivePlayer());
	}
	
	@Test
	public void gameOverTest()
	{
		controller.addPlayer(p1);
		controller.addPlayer(p2);
		controller.addPlayer(p3);
		controller.removePlayer();
		controller.removePlayer();
		
		assertEquals(true, controller.getModel().isGameOver());
	}
	
	@Test
	public void nextPlayerTest()
	{
		controller.addPlayer(p1);
		controller.addPlayer(p2);
		controller.addPlayer(p3);
		
		controller.nextPlayer();
		assertEquals(p1, controller.getModel().getActivePlayer());
		
		controller.nextPlayer();
		assertEquals(p2, controller.getModel().getActivePlayer());
		
	}
	
	@Test
	public void movingTestP1()
	{
		/*
rd.dl.rdl.ud.rdl.rd.dl
ur.ud.rd.dl.ud.rl.rl
urd.rl.urd.rl.rdl.dl.udl
rl.rd.ud.ul.ud.ul.rl
urd.ur.url.rl.udl.rl.udl
rd.rl.ud.ur.ul.ud.dl
ur.ul.url.ud.url.ur.ul
		 */
		controller.addPlayer(p1); //starts at (0, 0)
		
		controller.moveUp();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(),
				controller.getModel().getTile(0,  0));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  1));
		
		controller.moveLeft();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  1));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  1));
	}

	@Test
	public void movingTestP2()
	{
		controller.addPlayer(p2); //starts at (6, 0)
		
		controller.moveUp();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  0));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  1));
		
		controller.moveLeft();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(5,  1));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  1));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  1));
	}
	
	@Test
	public void movingTestP3()
	{
		controller.addPlayer(p3); //starts at (6, 6)
		
		controller.moveUp();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  5));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  6));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  6));
		
		controller.moveLeft();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(5,  6));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  6));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(6,  6));
	}
	
	@Test
	public void movingTestP4()
	{
		controller.addPlayer(p4); //starts at (0, 6)
		
		controller.moveUp();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  5));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(),
				controller.getModel().getTile(0,  6));
		
		controller.moveDown();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  6));
		
		controller.moveLeft();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(0,  6));
		
		controller.moveRight();
		assertEquals(controller.getModel().getActivePlayer().getCurrentTile(), 
				controller.getModel().getTile(1,  6));
	}
	
	@Test
	public void getTurnNumberTest()
	{
		controller.addPlayer(p1);
		controller.addPlayer(p2);
		controller.addPlayer(p3);
		controller.addPlayer(p4);
		
		assertEquals(1, controller.getModel().getTurnNbr());
		
		controller.nextPlayer();

		assertEquals(2, controller.getModel().getTurnNbr());
		
		controller.nextPlayer();
		controller.nextPlayer();
		controller.nextPlayer();
		controller.nextPlayer();

		assertEquals(3, controller.getModel().getTurnNbr());
		
	}
	
	@Test
	public void buildBoardTest()
	{
		assertEquals(controller.getModel().getTile(0, 0).getTileType(), TILETYPE.down_right);
		assertEquals(controller.getModel().getTile(1, 0).getTileType(), TILETYPE.down_left);
	}
	
	@Test
	public void shiftTilesRightTest()
	{
		Tile tempTile = controller.getModel().getTile(1, 1);
		Tile oldNextTile = controller.getModel().getNextTile();
		Tile newNextTile = controller.getModel().getTile(6, 1);
		
		controller.shiftTiles(1, Direction.RIGHT);
		
		assertEquals(controller.getModel().getTile(0,1), oldNextTile);
		assertEquals(controller.getModel().getTile(2,1), tempTile);
		assertEquals(controller.getModel().getNextTile(), newNextTile);
		//Checker aussi les positions x et y
	}
	
	@Test
	public void shiftTilesLeftTest()
	{
		Tile tempTile = controller.getModel().getTile(1, 1);
		Tile oldNextTile = controller.getModel().getNextTile();
		Tile newNextTile = controller.getModel().getTile(0, 1);
		
		controller.shiftTiles(1, Direction.LEFT);
		
		assertEquals(controller.getModel().getTile(6,1), oldNextTile);
		assertEquals(controller.getModel().getTile(0,1), tempTile);
		assertEquals(controller.getModel().getNextTile(), newNextTile);
		//Checker aussi les positions x et y
	}
	
	@Test
	public void shiftTilesUpTest()
	{
		Tile tempTile = controller.getModel().getTile(1, 1);
		Tile oldNextTile = controller.getModel().getNextTile();
		Tile newNextTile = controller.getModel().getTile(1, 0);
		
		controller.shiftTiles(1, Direction.UP);
		
		assertEquals(controller.getModel().getTile(1,6), oldNextTile);
		assertEquals(controller.getModel().getTile(1,0), tempTile);
		assertEquals(controller.getModel().getNextTile(), newNextTile);
		//Checker aussi les positions x et y
	}
	
	@Test
	public void shiftTilesDownTest()
	{
		Tile tempTile = controller.getModel().getTile(1, 1);
		Tile oldNextTile = controller.getModel().getNextTile();
		Tile newNextTile = controller.getModel().getTile(1, 6);
		
		controller.shiftTiles(1, Direction.DOWN);
		
		assertEquals(controller.getModel().getTile(1,0), oldNextTile);
		assertEquals(controller.getModel().getTile(1,2), tempTile);
		assertEquals(controller.getModel().getNextTile(), newNextTile);
		//Checker aussi les positions x et y
	}
}
