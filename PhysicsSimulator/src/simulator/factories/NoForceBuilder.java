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
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("desc", "No Force law");
		
		return data;
	}
}
