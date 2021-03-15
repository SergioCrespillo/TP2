package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	private static final double EPS = 0.0;
	
	public EpsilonEqualStatesBuilder() {
		this._typeTag = "epseq";
		this.desc = "Epsilon Equal State";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		double eps;
		
		if(!info.has("eps")) {
			eps = EPS;
		}
		else {
			eps=info.getDouble("eps");
		}
		
		return new EpsilonEqualStates(eps);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("eps", "Epsilon's value");
		
		return data;
	}
}
