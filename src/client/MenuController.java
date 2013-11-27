package client;

import server.GameController;

public class MenuController implements IMenuController
{
	public static void main(String[] args) 
	{
		new MenuController();
	}
	
	public MenuController()
	{
		IMenuView view = new MenuView(this);
		view.showView();
	}

	@Override
	public void showRules() 
	{
		new RulesView();
	}

	@Override
	public void startGame() 
	{
		new GameController();
	}
}
