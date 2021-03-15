package simulator.model;


import org.json.JSONObject;

import simulator.misc.Vector2D;

public class Body {
	protected String id;
	protected Vector2D velocity;
	protected Vector2D force;
	protected Vector2D position;
	protected double mass;
	
	public Body(String id, Vector2D v, Vector2D p, double m) {
		this.id = id;
		velocity = new Vector2D(v);
		force = new Vector2D(0,0);
		position = new Vector2D(p);
		mass = m;
	}
	
	public String getId() {
		return id;
	}
	
	public Vector2D getVelocity() {
		return velocity;
	}

	public Vector2D getForce() {
		return force;
	}

	public Vector2D getPosition() {
		return position;
	}

	public double getMass() {
		return mass;
	}
	
	void addForce(Vector2D f) {
		force.plus(f);
	}
	
	void resetForce() {
		force = new Vector2D();
	}
	
	void move(double t) {
		//aceleration vector
		Vector2D a;
		if(mass==0) {
			a = new Vector2D();
		}
		else {
			a = new Vector2D(force.getX()/mass,force.getY()/mass);
		}
		
		// update position vector
		position = position.plus(velocity.scale(t).plus(a.scale(t*t/2)));
        // update velocity vector
        velocity = velocity.plus(a.scale(t));
	}
	
	//Añadir equals
	
	public JSONObject getState() {
		JSONObject j = new JSONObject();
		
		j.put("id", id);
		j.put("m", mass);
		j.put("p", position.asJSONArray());
		j.put("v", velocity.asJSONArray());
		j.put("f", force.asJSONArray());
		
		return j;
	}
	
	public String toString() {
		return getState().toString();
	}
}
