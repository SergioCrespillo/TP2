package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator>{

	public MassEqualStatesBuilder(){
		super._typeTag = "masseq";
	}
	
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		return new MassEqualStates();
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		return data;
	}

}
