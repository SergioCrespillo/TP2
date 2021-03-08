package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	private static final double EPS = 0.0;
	
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		JSONObject data = info.getJSONObject("data");
		double eps;
		
		if(data.isNull("eps")) {
			eps = EPS;
		}
		else {
			eps=data.getDouble("eps");
		}
		
		return new EpsilonEqualStates(eps);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("eps", "Epsilon's value");
		data.put("desc", "Epsilon StateComparator");
		
		return data;
	}
}
