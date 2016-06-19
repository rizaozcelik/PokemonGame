package brain;

import pokemons.Pokemon;
// This is the superclass of all strategies. New strategies can extend this class.
public class Strategy {

	Pokemon pokemon;

	// This method should be overriden by evry Strategy to work. It tells GameController
	// which action it shoul simulate.
	public String getAction(Object[] currentState){
		return "";
	}
// Set pokemon of the strategy
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}

	public Pokemon getPokemon(){
		return pokemon;
	}
}
