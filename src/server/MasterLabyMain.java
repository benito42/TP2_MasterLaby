package server;



public class MasterLabyMain {

	public static void main(String[] args)
	{
		Player p1 = new Player(0, 0);
		Player p2 = new Player(0, 6);
		Player p3 = new Player(6, 0);
		Player p4 = new Player(6, 6);
		
		GameController controller = new GameController();
		
		controller.addPlayer(p1);
		
		

	}

}
