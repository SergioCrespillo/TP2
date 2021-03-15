package simulator.control;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Body;

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
			
			for(int i=0;i<j1.length();i++){
				if(j1.getJSONObject(i).getString("id").equals(s2Bodies.get(i).getId()) 
						&& Math.abs(s1Bodies.get(i).getMass()-s2Bodies.get(i).getMass()) <= eps 
						&& s1Bodies.get(i).getPosition().distanceTo(s2Bodies.get(i).getPosition()) <= eps
						&& s1Bodies.get(i).getVelocity().distanceTo(s2Bodies.get(i).getVelocity()) <= eps
						&& s1Bodies.get(i).getForce().distanceTo(s2Bodies.get(i).getForce()) <= eps) {
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
	
	public boolean iguales(JSONArray j1, JSONArray j2) {
		//Metodo de jsonArray a vector2d y comparas con distance to
		Vector2D v1 = j1.
	}
}
