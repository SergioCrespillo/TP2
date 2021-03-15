package simulator.control;

import java.util.List;

import org.json.JSONObject;

import simulator.model.Body;

public class MassEqualStates implements StateComparator {
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean iguales = false;
		
		if(s1.get("time").equals(s2.get("time"))) {
			iguales = true;
			//hacer como en epsilosEqualStates
			List<Body> s1Bodies = (List<Body>) s1.get("bodies");
			List<Body> s2Bodies = (List<Body>) s2.get("bodies");
			int i = 0;
			
			while(i < s1Bodies.size() && iguales) {
				if(s1Bodies.get(i).getId().equals(s2Bodies.get(i).getId()) && 
					s1Bodies.get(i).getMass() == s2Bodies.get(i).getMass()) {
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
