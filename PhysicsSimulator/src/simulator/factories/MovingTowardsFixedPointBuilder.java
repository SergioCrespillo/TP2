package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	
	protected static final double G = 9.8;
	
	public MovingTowardsFixedPointBuilder(){
		super._typeTag = "mtfp";
		this.desc = "Moving towards a fixed point";
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
			g = G;
		}
		else{
			g = info.getDouble("g");
		}
		
		return new MovingTowardsFixedPoint(g, c);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("c", "the point towards which bodies move (a json list of 2 numbers, e.g., [100.0,50.0])");
		data.put("g", "the length of the acceleration vector (a number)");
		
		return data;
	}
}
