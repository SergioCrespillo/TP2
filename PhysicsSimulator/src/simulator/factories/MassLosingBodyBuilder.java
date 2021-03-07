package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body>{
	
	public MassLosingBodyBuilder(){
		super._typeTag = "mlb";
	}
	
	public Body createTheInstance(JSONObject data)
	{
		String id = data.getString("id");
		Vector2D p = jsonArrayTodoubleArray(data.getJSONArray("p"));
		Vector2D v = jsonArrayTodoubleArray(data.getJSONArray("v"));
		double m = data.getDouble("mass");
		double freq = data.getDouble("freq");
		double factor = data.getDouble("factor");
		
		return new MassLosingBody(id, v, p, m, factor, freq); 
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("p", "the position");
		data.put("v", "the velocity");
		data.put("m", "the mass");
		data.put("freq", "the frequency");
		data.put("factor", "the factor");
		
		return data;
	}

}