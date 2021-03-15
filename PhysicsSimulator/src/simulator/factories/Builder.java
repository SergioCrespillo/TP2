package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public abstract class Builder<T> {
	
	protected String _typeTag; // indica el tipo de objeto a construir
	protected String desc;
	
	protected Vector2D jsonArrayTodoubleArray(JSONArray ja)
	{
		return new Vector2D(ja.getDouble(0),ja.getDouble(1));
	}
	
	public T createInstance(JSONObject info) {
		T b = null;
		if (_typeTag != null && _typeTag.equals(info.getString("type"))) {
			b = createTheInstance(info.has("data") ? info.getJSONObject("data") : null);
		}
		return b;
	}
	
	protected abstract T createTheInstance(JSONObject info);
	
	public JSONObject getBuilderInfo()
	{
		JSONObject info = new JSONObject();
		
		info.put("type", _typeTag);
		info.put("data", createData());
		info.put("desc", desc);
		
		return info;
	}
	
	protected JSONObject createData(){
		return new JSONObject();
	}

}
