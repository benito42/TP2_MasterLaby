package server;

public enum Avatar
{
	commando("/img/Avatar_Commando.jpg", "/img/commando1.jpg", "/img/commando2.jpg", "/img/commando3.jpg"),
	darkVador("/img/Avatar_DarkVador.jpg", "/img/DarkVador1.jpg", "/img/DarkVador2.jpg", "/img/DarkVador3.jpg"),
	ninja("/img/Avatar_Ninja.jpg", "/img/Ninja1.jpg", "/img/Ninja2.jpg", "/img/Ninja3.jpg"),
	pirate("/img/Avatar_Pirate.jpg", "/img/Pirate1.jpg.jpg", "/img/Pirate2.jpg", "/img/Pirate3.jpg"),
	robot("/img/Avatar_Robot.jpg", "/img/robot1.jpg", "/img/robot.jpg2", "/img/robot.jpg3"),
	viking("/img/Avatar_Viking.jpg", "/img/Viking1.jpg", "/img/Viking2.jpg", "/img/Viking3.jpg"),
	zombie("/img/Avatar_Zombie.jpg", "/img/zombie1.jpg", "/img/zombie2.jpg", "/img/zombie3.jpg");
	
	private final String avatar;
	private final String objective1;
	private final String objective2;
	private final String objective3;
	
	Avatar(String avatar, String obj1, String obj2, String obj3)
	{
		this.avatar = avatar;
		this.objective1 = obj1;
		this.objective2 = obj2;
		this.objective3 = obj3;
	}
	
	public String getAvatar(){return this.avatar;}
	public String getObj1(){return this.objective1;}
	public String getObj2(){return this.objective2;}
	public String getObj3(){return this.objective3;}
}