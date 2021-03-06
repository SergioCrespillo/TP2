package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{
	
	public NoForceBuilder(){
		super._typeTag = "ng";
	}
	
	public ForceLaws createTheInstance(JSONObject bodies)
	{
		return new NoForce();
	}
}
