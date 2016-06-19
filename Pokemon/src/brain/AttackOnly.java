package brain;
// A strategy that attacks all the time.
public class AttackOnly extends Strategy {

	@Override
	public String getAction(Object[] currentState){
		return "attack";
	}
}
