package brain;

public class AttackOnly extends Strategy {
	
	@Override
	public String getAction(Object[] currentState){
		return "attack";
	}
}
