package server;

public enum Avatar
{
	commando("/img/Avatar_Commando.jpg", "/img/commando1.jpg", "/img/commando2", "/img/commando3"),
	darkVador("/img/Avatar_DarkVador.jpg", "/img/DarkVador1.jpg", "/img/DarkVador2", "/img/DarkVador3"),
	ninja("/img/Avatar_Ninja.jpg", "/img/Ninja1.jpg", "/img/Ninja2", "/img/Ninja3"),
	pirate("/img/Avatar_Pirate.jpg", "/img/Pirate1.jpg", "/img/Pirate2", "/img/Pirate3"),
	robot("/img/Avatar_Robot.jpg", "/img/robot1.jpg", "/img/robot2", "/img/robot3"),
	viking("/img/Avatar_Viking.jpg", "/img/Viking1.jpg", "/img/Viking2", "/img/Viking3"),
	zombie("/img/Avatar_Zombie.jpg", "/img/zombie1.jpg", "/img/zombie2", "/img/zombie3");
	
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