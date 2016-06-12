package brain;

import java.util.Random;
import java.util.Scanner;

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

public class GameController {

	public static boolean superPower;

	public static void multiplayer(boolean isEnabled) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Player1's name : ");
		String name1 = scan.nextLine();
		Pokemon poke1 = getPokemon(scan);
		System.out.println("Enter Player2's name : ");
		String name2 = scan.nextLine();
		Pokemon poke2 = getPokemon(scan);
		System.out.println("Time to battle!".toUpperCase());
		boolean potion1left = true;
		boolean potion2left = true;
		while (poke1.isAlive() && poke2.isAlive()) {
			String action = getActionMultiPlayer(scan, 1);
			potion1left = makeTheAction(poke1, poke2, action, potion1left);
			if (poke2.isAlive()) {
				System.out.println("Conditions are:");
				System.out.println(name1 + " " + poke1.getHP() + " " + poke1.getDefense() + " " + potion1left);
				System.out.println(name2 + " " + poke2.getHP() + " " + poke2.getDefense() + " " + potion2left);
				action = getActionMultiPlayer(scan, 2);
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

	private static String getActionMultiPlayer(Scanner scan, int turn) {
		System.out.println("Player" + turn + " go ahead! Press 1 to attack, 2 to defense, 3 to potion");
		int decision = scan.nextInt();
		switch (decision) {
		case 1:
			return "attack";
		case 2:
			return "defense";
		case 3:
			return "potion";
		default:
			return null;
		}

	}

	private static Pokemon getPokemon(Scanner scan) {
		Pokemon[] pokeballs = { new Pikachu(), new Voltorb(), new Charmander(), new Magmar(), new Bulbasaur(),
				new Oddish(), new Geodude(), new Onix(), new Krabby(), new Squirtle(), new Pikachu(), new Voltorb(),
				new Charmander(), new Magmar(), new Bulbasaur(), new Oddish(), new Geodude(), new Onix(), new Krabby(),
				new Squirtle() };
		System.out.println("Choose your pokeball. Enter the number or name of your pokeball.");
		System.out.println("Name HP AttackRange Defense");
		for (int i = 0; i < 10; i++) {
			System.out.println((i + 1) + " " + pokeballs[i].getClass().getName().substring(9) + " "
					+ pokeballs[i].getHP() + " " + print(pokeballs[i].getAttack()) + " " + pokeballs[i].getDefense());
		}
		Pokemon pokeball;
		String selection = scan.nextLine();
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

	private static String print(int[] attack) {
		return (attack[0] + "-" + attack[attack.length - 1]);

	}

	public static void versusComputer(Strategy cpu) {
		Scanner scan = new Scanner(System.in);
		Pokemon[] pokeballs = { new Pikachu(), new Voltorb(), new Charmander(), new Magmar(), new Bulbasaur(),
				new Oddish(), new Geodude(), new Onix(), new Krabby(), new Squirtle() };
		Random rand = new Random();
		Pokemon cpuPoke = pokeballs[rand.nextInt(10)];
		cpu.setPokemon(cpuPoke);
		System.out.println("You are playing against a: " + cpuPoke.getClass().getName().substring(9));
		Pokemon pokeball = getPokemon(scan);
		boolean potion2left = true;
		boolean potion1left = true;
		String lastAction;

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
					boolean player1turn = false;
					if (k == 1) {
						player1turn = true;
					}
					player1.setPokemon(pokeballs[i]);
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
							player1turn = false;
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
						System.out.println("hp1  is " + poke1.getHP());
						System.out.println("hp2  is " + poke2.getHP());
					}
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

	public static boolean makeTheAction(Pokemon actor, Pokemon opponent, String action, boolean potionLeft) {
		if (action.equals("attack")) {
			actor.damageHim(opponent);
			if (superPower) {
				actor.damageHim(opponent);
			}
		} else if (action.equals("defense")) {
			actor.setDefense(actor.getDefense() + 1);
		} else if (potionLeft) {
			actor.fullMyHP();
			potionLeft = false;
		} else {
			System.out.println("Sorry no potion left. Default action: Attack");
			actor.damageHim(opponent);
		}

		return potionLeft;

	}

}
