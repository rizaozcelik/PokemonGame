package pokemons;

import java.util.Random;

public class Pokemon {
	
	int HP;
	int[] attack;
	int defense;
	String strongAgainst;
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
		return strongAgainst.contains(opponent.getClass().getSuperclass().getName().replace("pokemons.", "").substring(0,1));
	}
	
	public boolean isWeakAgainst(Pokemon opponent){
		return weakAgainst.contains(opponent.getClass().getSuperclass().getName().replace("pokemons.", "").substring(0,1));
	}
	
	public boolean isAlive(){
		return this.getHP() > 0;
	}
	
	public void damageHim(Pokemon opponent){
		Random rand = new Random();
		int decision = rand.nextInt(10);
		if(decision == 0){
			System.out.println("Oh! So unlucky!");
			return;
		}
		int[] attackRange = getAttack();
		decision = decision % attackRange.length;
		int attackPoint = attackRange[decision];
		int opponentInitialHP = opponent.getHP();
		int opponentDefense = opponent.getDefense();
		int damage;
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










