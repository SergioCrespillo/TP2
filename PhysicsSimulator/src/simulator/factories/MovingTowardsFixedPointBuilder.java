package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	
	public MovingTowardsFixedPointBuilder(){
		super._typeTag = "mtfp";
		this.desc = "Moving Towards Fixed Point law";
	}
	
	public ForceLaws createTheInstance(JSONObject info)
	{
		double g;
		Vector2D c;
		
		if(!info.has("c")) {
			c = new Vector2D();
		}
		else {
			c = jsonArrayTodoubleArray(info.getJSONArray("c"));
		}
		
		if(!info.has("g")) {
			g = 9.81;
		}
		else{
			g = info.getDouble("g");
		}
		
		return new MovingTowardsFixedPoint(g, c);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("c", "center of the universe");
		data.put("g", "the gravity");
		
		return data;
	}
}
