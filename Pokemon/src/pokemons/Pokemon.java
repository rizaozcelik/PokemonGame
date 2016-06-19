package pokemons;

import java.util.Random;
// This is a super class for all types of Pokemons. All pokemons inherits this class
// directly or indirectly.
public class Pokemon {

	int HP; // Health of pokemons
	int[] attack; // attack range of pokemons.
	int defense; // defense point of pokemons.
	String strongAgainst; // Every type has the information of stength and weaknesses against other types.
	String weakAgainst;

	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int[] getAttack() {
		return attack;
	}
	public void setAttack(int[] attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		System.out.println("Oh my little Mourinho..");
		this.defense = defense;
	}

	public boolean isStrongAgainst(Pokemon opponent){
		// Every type has the strength and weaknesses agains other types. and this information is hold in the form
		// of capital letters. For example if a type has strength over Electric and water, its strength string is "E W"
		// Same goes for weaknesses too.
		return strongAgainst.contains(opponent.getClass().getSuperclass().getName().replace("pokemons.", "").substring(0,1));
	}

	public boolean isWeakAgainst(Pokemon opponent){
		return weakAgainst.contains(opponent.getClass().getSuperclass().getName().replace("pokemons.", "").substring(0,1));
	}
	// return if the pokemon is alive.
	public boolean isAlive(){
		return this.getHP() > 0;
	}
	// This method is used to attack opponents. It decreases the HP of enemy according to some rules.
	public void damageHim(Pokemon opponent){
		// For every attack there is chance of missing by %10. So Take a random number
		// between 0-9 and if it is 0 miss the attack.
		Random rand = new Random();
		int decision = rand.nextInt(10);
		if(decision == 0){
			System.out.println("Oh! So unlucky!");
			return;
		}
		// Every pokemon attack point is a range and they can attack it this range randomly
		int[] attackRange = getAttack();
		decision = decision % attackRange.length;
		int attackPoint = attackRange[decision];// Find the attack point.
		int opponentInitialHP = opponent.getHP();
		int opponentDefense = opponent.getDefense();
		int damage;
		// attack rules are as such: If pokemon is weak against the enemy multiply the
		// attack point by 0.8. If it is strong, multiply by 2. If it is neutral multiply by 1.
		// Then substract the defense of opponent. If the result is < 1 pull it up to 1.
		if(isWeakAgainst(opponent)){
			damage = (int) (Math.round(attackPoint*0.8) - opponentDefense);
			System.out.println("Well... Could be better you know..");
		}else if(isStrongAgainst(opponent)){
			damage = Math.round(attackPoint*2) - opponentDefense;
			System.out.println("OH MY BELOVING GOD!");
		}else{
			damage = Math.round(attackPoint*1) - opponentDefense;
			System.out.println("Good hit!");
		}
		damage = Math.max(damage, 1);
		opponent.setHP(opponentInitialHP - damage);
	}
	// This method sets the opponent HP as full. so implements potion.
	public void fullMyHP() {
		@SuppressWarnings("rawtypes")
		Class cls =  getClass();
		Pokemon temp;
		try {
			temp = (Pokemon) cls.newInstance();
			this.setHP(temp.getHP());
		} catch (InstantiationException | IllegalAccessException e) {
		}
		System.out.println("Vuh! That felt good!");
	}
}
