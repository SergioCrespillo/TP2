package simulator.control;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Body;

public class MassEqualStates implements StateComparator {
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean iguales = false;
		
		if(s1.get("time").equals(s2.get("time"))) {
			iguales = true;
			JSONArray j1 = s1.getJSONArray("bodies");
			JSONArray j2 = s2.getJSONArray("bodies");
			int i = 0;
			
			while(i < j1.length() && iguales) {
				if(j1.getJSONObject(i).getString("id").equals(j2.getJSONObject(i).getString("id")) && 
						j1.getJSONObject(i).getDouble("mass") == j2.getJSONObject(i).getDouble("mass")) {
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
}
