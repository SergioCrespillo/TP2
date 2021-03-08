package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	
	public MovingTowardsFixedPointBuilder(){
		super._typeTag = "mtcp";
	}
	
	public ForceLaws createTheInstance(JSONObject info)
	{
		JSONObject data = info.getJSONObject("data");
		double g;
		Vector2D c;
		
		if(data.isNull("c")) {
			c = new Vector2D();
		}
		else {
			c = (Vector2D) data.get("c");
		}
		
		if(data.isNull("g")) {
			g = 9.81;
		}
		else{
			g = data.getDouble("g");
		}
		
		return new MovingTowardsFixedPoint(g, c);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("c", "center of the universe");
		data.put("g", "the gravity");
		data.put("desc", "Moving Towards Fixed Point law");
		
		return data;
	}
}
