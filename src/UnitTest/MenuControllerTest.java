package UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import client.IMenuController;
import client.MenuController;
import client.RulesView;

public class MenuControllerTest 
{
	private IMenuController menuController = new MenuController();
	
	@Test
	public void showRulesTest() 
	{
		this.menuController.showRules();
		assertEquals(getClass(), RulesView.class);
	}

}
