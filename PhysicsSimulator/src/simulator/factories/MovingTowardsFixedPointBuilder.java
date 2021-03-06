package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	
	public MovingTowardsFixedPointBuilder(){
		super._typeTag = "ftcg";
	}
	
	public ForceLaws createTheInstance(JSONObject bodies)
	{
		double g = bodies.getDouble("g");
		Vector2D c = (Vector2D) bodies.get("c");
		return new MovingTowardsFixedPoint(g, c);
	}
}
