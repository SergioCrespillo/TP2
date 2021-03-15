package simulator.control;


import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualStates implements StateComparator {

	private double eps;
	
	public EpsilonEqualStates(double epsilon) {
		eps = epsilon;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean iguales = false;
		
		if(s1.get("time").equals(s2.get("time"))) {
			iguales = true;
			JSONArray j1 = s1.getJSONArray("bodies");
			JSONArray j2 = s2.getJSONArray("bodies");
			int i = 0;
			
			while(i < j1.length() && iguales){
				if(j1.getJSONObject(i).getString("id").equals(j2.getJSONObject(i).getString("id")) 
						&& Math.abs((j1.getJSONObject(i).getDouble("mass"))-(j2.getJSONObject(i).getDouble("mass"))) <= eps 
						&& distanceToPosition(j1, j2, i, "p") <= eps
						&& distanceToPosition(j1, j2, i, "v") <= eps
						&& distanceToPosition(j1, j2, i, "f") <= eps)
						/*&& s1Bodies.get(i).getPosition().distanceTo(s2Bodies.get(i).getPosition()) <= eps
						&& s1Bodies.get(i).getVelocity().distanceTo(s2Bodies.get(i).getVelocity()) <= eps
						&& s1Bodies.get(i).getForce().distanceTo(s2Bodies.get(i).getForce()) <= eps)*/ {
					iguales = true;
				}
				else {
					iguales = false;
				}
				i++;
			}
		}
		return iguales;
	}
	
	private double distanceToPosition(JSONArray j1, JSONArray j2, int index, String key) {
		Vector2D v1 = jsonArrayTodoubleArray(j1.getJSONObject(index).getJSONArray(key));
		Vector2D v2 = jsonArrayTodoubleArray(j2.getJSONObject(index).getJSONArray(key));
		
		return v1.distanceTo(v2);
	}
	
	
	private Vector2D jsonArrayTodoubleArray(JSONArray ja)
	{
		
		return new Vector2D(ja.getDouble(0),ja.getDouble(1));
	}
}
