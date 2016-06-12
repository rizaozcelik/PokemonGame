package brain;

public class MyStrategy extends Strategy{

	@Override
	public String getAction(Object[] currentState){
		if(this.pokemon.getHP() < 10){
			return "potion";
		}
		return "attack";
	}
}
