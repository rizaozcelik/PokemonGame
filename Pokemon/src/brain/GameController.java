package brain;

import java.util.Random;
import java.util.Scanner;
import java.util.File;
import pokemons.Bulbasaur;
import pokemons.Charmander;
import pokemons.Geodude;
import pokemons.Krabby;
import pokemons.Magmar;
import pokemons.Oddish;
import pokemons.Onix;
import pokemons.Pikachu;
import pokemons.Pokemon;
import pokemons.Squirtle;
import pokemons.Voltorb;
// This class is the brain of the game. It plays the game accorddingly
// and implements the rules. It has 3 modes right now.
// 1 - multiplayer: Two people can play the game through the console.
// 2 - versusComputer: One can play against the computer.
// 3 - run200Games: computer plays 200 games automatically and prints out the result.

public class GameController {

	// boolean that holds if superPower usage is enabled
	public static boolean superPower;

	// Method that welcomes the players and wants them to choose pokemons.
	// It also runs the game and gives feedbacks about the hits to the player.
	// Parammeter:
	// boolean isEnabled : Information of if the superPower is enabled.
	public static void multiplayer(boolean isEnabled) {
		// Welcoming of the players.
		superPower = isEnabled;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Player1's name : ");
		String name1 = scan.nextLine();
		Pokemon poke1 = getPokemon(scan);
		System.out.println("Enter Player2's name : ");
		String name2 = scan.nextLine();
		Pokemon poke2 = getPokemon(scan);
		System.out.println("Time to battle!".toUpperCase());

		// Boolean that checks if potion left for any of the players.
		boolean potion1left = true;
		boolean potion2left = true;

		// The battle loop.
		while (poke1.isAlive() && poke2.isAlive()) {
			String action = getActionMultiPlayer(scan, 1); // Action selected by player1
			potion1left = makeTheAction(poke1, poke2, action, potion1left); // Note that this method returns a boolean
			if (poke2.isAlive()) {
				// It 2nd player' pokemon isn't dead
				// print the current condition and wait for the next move.
				System.out.println("Conditions are:");
				System.out.println(name1 + " " + poke1.getHP() + " " + poke1.getDefense() + " " + potion1left);
				System.out.println(name2 + " " + poke2.getHP() + " " + poke2.getDefense() + " " + potion2left);
				action = getActionMultiPlayer(scan, 2); // Action selected by player2.
				potion2left = makeTheAction(poke2, poke1, action, potion2left);
			} else {
				System.out.println("Congratz" + name1 + "! You won!");
				break;
			}
			if (!poke1.isAlive()) {
				System.out.println("Congratz" + name2 + "! You won!");
				break;
			}
			System.out.println("Conditions are:");
			System.out.println(name1 + " " + poke1.getHP() + " " + poke1.getDefense() + " " + potion1left);
			System.out.println(name2 + " " + poke2.getHP() + " " + poke2.getDefense() + " " + potion2left);
		}

		scan.close();
	}

	// Game mode that enables the playing versus CPU.
	// Parameters
	// Strategy cpu : Selected by player and it is strategy who is been playede against.
	// boolean isEnabled : Information of if superPower is enabled.
	public static void versusComputer(Strategy cpu, boolean isEnabled) {
		superPower = isEnabled;
		Scanner scan = new Scanner(System.in);
		Pokemon[] pokeballs = { new Pikachu(), new Voltorb(), new Charmander(), new Magmar(), new Bulbasaur(),
				new Oddish(), new Geodude(), new Onix(), new Krabby(), new Squirtle() };
		Random rand = new Random();
		Pokemon cpuPoke = pokeballs[rand.nextInt(10)];
		cpu.setPokemon(cpuPoke); // cpu selects pokemon randpmly.
		System.out.println("You are playing against a: " + cpuPoke.getClass().getName().substring(9));
		Pokemon pokeball = getPokemon(scan); // Pokemon of the user.

		// potion information of players.
		boolean potion2left = true;
		boolean potion1left = true;
		String lastAction;// Last action taken by user. Needed by Strategies.
		// Battle loop.
		while (cpuPoke.isAlive() && pokeball.isAlive()) {
			String action = getActionMultiPlayer(scan, 1);
			lastAction = action;
			potion1left = makeTheAction(pokeball, cpuPoke, action, potion1left);
			System.out.println("CPU conditions are:");
			System.out.println(cpuPoke.getHP() + " " + cpuPoke.getDefense() + " " + potion2left);
			if (cpuPoke.isAlive()) {
				Object[] currentState = { pokeball.getHP(), pokeball.getClass().getSuperclass().getName(),
						pokeball.getDefense(), potion2left, lastAction };
				action = cpu.getAction(currentState);
				potion2left = makeTheAction(cpuPoke, pokeball, action, potion2left);
				System.out.println("Your conditions are:");
				System.out.println(pokeball.getHP() + " " + pokeball.getDefense() + " " + potion1left);

			} else {
				System.out.println("Congratz! You Won!");
				break;
			}
		}
		if (!pokeball.isAlive()) {
			System.out.println("Sorry.. Good game though");
		}
	}

	// This mode runs 200 games between two Strategies and prints out the result.
	// It tries all the possible combinations of pokemons. Since there are  10 pokemons
	// and each Strategy should make the first action equally it 10*10*2 = 200 game is run.
	public static void run200Games(Strategy player1, Strategy player2, boolean isEnabled) {
		superPower = isEnabled;
		int player1wins = 0, player2wins = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 2; k++) {
					Pokemon[] pokeballs = { new Pikachu(), new Voltorb(), new Charmander(), new Magmar(),
							new Bulbasaur(), new Oddish(), new Geodude(), new Onix(), new Krabby(), new Squirtle(),
							new Pikachu(), new Voltorb(), new Charmander(), new Magmar(), new Bulbasaur(), new Oddish(),
							new Geodude(), new Onix(), new Krabby(), new Squirtle() };
					boolean player1turn = false; // Give avay the firs move if k = 0.
					if (k == 1) {
						player1turn = true;
					}
					player1.setPokemon(pokeballs[i]);// Choose each pokemon in order.
					player2.setPokemon(pokeballs[10 + j]);
					int numberOfTurns = 0;
					boolean potion1left = true;
					boolean potion2left = true;
					Pokemon poke1 = player1.getPokemon();
					Pokemon poke2 = player2.getPokemon();
					String lastAction = "";
					while (poke1.isAlive() && poke2.isAlive() && numberOfTurns < 41) {
						numberOfTurns++;
						String action;
						if (player1turn) {
							Object[] currentState = { poke2.getHP(), poke2.getClass().getSuperclass().getName(),
									poke2.getDefense(), potion2left, lastAction };
							action = player1.getAction(currentState);
							lastAction = action;
							potion1left = makeTheAction(poke1, poke2, action, potion1left);
							player1turn = false; // Now it is opponents turn.
						} else {
							Object[] currentState = { poke1.getHP(), poke1.getClass().getSuperclass().getName(),
									poke1.getDefense(), potion1left, lastAction };
							action = player2.getAction(currentState);
							lastAction = action;
							potion2left = makeTheAction(poke2, poke1, action, potion2left);
							player1turn = true;
						}
					} // End one fight.
					if (poke1.getHP() == poke2.getHP()) {
						File file = new File("out.txt");
						file.writeln("asdasd");
						System.out.println("hp1  is " + poke1.getHP());
						System.out.println("hp2  is " + poke2.getHP());
					}
					// determine  who is the winner.
					if (poke1.getHP() > poke2.getHP()) {
						player1wins++;
					} else {
						player2wins++;
					}
				}
			}
		}
		System.out.println("Player1 wins " + player1wins + " times");
		System.out.println("Player2 wins " + player2wins + " times");
	}

	//This method is used to get action from Players.
	// Parameters
	// Scanner scan : Used to get input
	// int turn : Information of whose turn it is.
	private static String getActionMultiPlayer(Scanner scan, int turn) {
		System.out.println("Player" + turn + " go ahead! Press 1 to attack, 2 to defense, 3 to potion");
		int decision = scan.nextInt();
		// Action is determined by players simply by 3 numbers.
		switch (decision) {
		case 1:
			return "attack";
		case 2:
			return "defense";
		case 3:
			return "potion";
		default:
			return "attack";
		}
	}


	private static Pokemon getPokemon(Scanner scan) {
		// If there were only 10 pokemon
		// and then the players hold the same one then there were conflicts Since
		// both of them will have the same object. So  there 20 pokemons.
		Pokemon[] pokeballs = { new Pikachu(), new Voltorb(), new Charmander(), new Magmar(), new Bulbasaur(),
				new Oddish(), new Geodude(), new Onix(), new Krabby(), new Squirtle(), new Pikachu(), new Voltorb(),
				new Charmander(), new Magmar(), new Bulbasaur(), new Oddish(), new Geodude(), new Onix(), new Krabby(),
				new Squirtle() };
		// Prompt pokemons to the user.
		System.out.println("Choose your pokeball. Enter the number or name of your pokeball.");
		System.out.println("Name HP AttackRange Defense");
		for (int i = 0; i < 10; i++) {
			System.out.println((i + 1) + " " + pokeballs[i].getClass().getName().substring(9) + " "
					+ pokeballs[i].getHP() + " " + print(pokeballs[i].getAttack()) + " " + pokeballs[i].getDefense());
		}
		Pokemon pokeball;
		String selection = scan.nextLine();
		// Get the selection.
		switch (selection) {
		case ("Pikachu"):
		case "1":
			pokeball = new Pikachu();
			break;
		case ("Voltorb"):
		case "2":
			pokeball = new Voltorb();
			break;
		case ("Charmander"):
		case "3":
			pokeball = new Charmander();
			break;
		case ("Magmar"):
		case "4":
			pokeball = new Magmar();
			break;
		case ("Bulbasaur"):
		case "5":
			pokeball = new Bulbasaur();
			break;
		case ("Oddish"):
		case "6":
			pokeball = new Oddish();
			break;
		case ("Geodude"):
		case "7":
			pokeball = new Geodude();
			break;
		case ("Onix"):
		case "8":
			pokeball = new Onix();
			break;
		case ("Krabby"):
		case "9":
			pokeball = new Krabby();
			break;
		case ("Squirtle"):
		case "10":
			pokeball = new Squirtle();
			break;
		default:
			pokeball = new Pikachu();
			break;
		}
		System.out.println("Wow! You picked a " + pokeball.getClass().getName().substring(9));
		return pokeball;
	}
	//  Simple method to print Pokemons.
	private static String print(int[] attack) {
		return (attack[0] + "-" + attack[attack.length - 1]);

	}

	// Go ahead and implement the action.
	// Parameters
	// Pokemon actor : Pokemon that makes the action.
	// Pokemon opponent : Pokemon that is being acted.
	// String action : Action to make.
	// boolean potionLeft : If there is potion.
	public static boolean makeTheAction(Pokemon actor, Pokemon opponent, String action, boolean potionLeft) {
		if (action.equals("attack")) {
			actor.damageHim(opponent); // Go decrease the opponent's HP.
			if (superPower) {
				// The superPower is implemented as attack twice so go attack again.
				actor.damageHim(opponent);
			}
		} else if (action.equals("defense")) {
			// Increment the defense.
			actor.setDefense(actor.getDefense() + 1);
		} else if (potionLeft) {
			// Full the pokemons HP.
			actor.fullMyHP();
			potionLeft = false;
		} else {
			// If one chooses the potion and no potion is left attack action is taken.
			System.out.println("Sorry no potion left. Default action: Attack");
			actor.damageHim(opponent);
		}
		return potionLeft; // return the potion information back.
	}
}
