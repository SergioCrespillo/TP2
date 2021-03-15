package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	
	private static final double cG = 6.67E-11;
	
	public NewtonUniversalGravitationBuilder()
	{
		super._typeTag = "nlug";
		super.desc = "Newton’s law of universal gravitation";
	}
	
	public ForceLaws createTheInstance(JSONObject info)
	{
		JSONObject data = info.getJSONObject("data");
		double G;
		
		if(data.isNull("G")) {
			G = cG;
		}
		else {
			G = data.getDouble("G");
		}
		
		return new NewtonUniversalGravitation(G);
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("G", "gravitational constant");
		
		return data;
	}
}

