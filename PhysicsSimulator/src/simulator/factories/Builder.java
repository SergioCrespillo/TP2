package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Builder<T> {
	
	protected String _typeTag; // indica el tipo de objeto a construir
	
	protected Vector2D jsonArrayTodoubleArray(JSONArray ja)
	{
		Vector2D da = new Vector2D();
		for (int i = 0; i < ja.length(); i++)
			da = (Vector2D) ja.get(i);
		return da;
	}
	
	public T createInstance(JSONObject info) {
		T b = null;
		if (_typeTag != null && _typeTag.equals(info.getString("type"))) {
			b = createTheInstance(info.has("data") ? info.getJSONObject("data") : null);
		}
		return b;
	}
	
	protected abstract T createTheInstance(JSONObject jsonObject);
	
	public JSONObject getBuilderInfo()
	{
		JSONObject info = new JSONObject();
		
		info.put("type", _typeTag);
		info.put("data", createData());
		
		return info;
	}
	
	protected JSONObject createData(){
		return new JSONObject();
	}

}
