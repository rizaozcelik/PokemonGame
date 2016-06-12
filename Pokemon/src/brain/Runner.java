package brain;

public class Runner {

	public static void main(String[] args) {
		AttackOnly ash = new AttackOnly();
		MyStrategy ketchum = new MyStrategy();
		//GameController.run200Games(ash, ketchum, true);
		//GameController.multiplayer(false);
		GameController.versusComputer(ash);
	}

}
