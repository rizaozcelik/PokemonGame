package brain;
// Very simple strategy which attacks if the remaining HP > 10. It uses potion otherwise
public class MyStrategy extends Strategy{

	@Override
	public String getAction(Object[] currentState){
		if(this.pokemon.getHP() < 10){
			return "potion";
		}
		return "attack";
	}
}
