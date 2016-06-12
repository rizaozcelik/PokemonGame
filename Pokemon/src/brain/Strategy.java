package brain;

import pokemons.Pokemon;

public class Strategy {
	
	Pokemon pokemon;
	
	public String getAction(Object[] currentState){
		return "";
	}
	
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	
	public Pokemon getPokemon(){
		return pokemon;
	}
}
