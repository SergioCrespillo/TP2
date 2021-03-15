package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{
	
	public BasicBodyBuilder(){
		this._typeTag = "basic";
	}
	
	public Body createTheInstance(JSONObject info)
	{	
		Body b = null;
		
		if(info.get("type").equals("basic")) {
			String id = info.getString("id");
			Vector2D p = jsonArrayTodoubleArray(info.getJSONArray("p"));
			Vector2D v = jsonArrayTodoubleArray(info.getJSONArray("v"));
			double m = info.getDouble("m");
			
			b = new Body(id,v,p,m);
		}
		
		return b;
	}
	
	protected JSONObject createData()
	{
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("p", "the position");
		data.put("v", "the velocity");
		data.put("m", "the mass");
		
		return data;
	}
}
