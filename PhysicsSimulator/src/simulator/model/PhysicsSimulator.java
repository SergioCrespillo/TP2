package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double _time;  // tiempo actual
	private ForceLaws _forceLaws;  // leyes de la fuerza a aplicar
	private List<Body> _bodies;  // cuerpos de la simulacion
	private List<Body> _bodiesUnmodifiable;
	private double _dt;  // tiempo delta
	private List<SimulatorObserver> _observers;
	
	public PhysicsSimulator(ForceLaws FL, double tRealporPaso)
	{
		if(!FL.equals(null))
		{
			this._forceLaws = FL;
		}
		else
		{
			throw new IllegalArgumentException("Ley de fuerza incorrecta");
		}
		
		if(tRealporPaso >= 0)
		{
			this._dt = tRealporPaso;
		}
		else
		{
			throw new IllegalArgumentException("Tiempo no valido");
		}
		this._bodies = new ArrayList<>();
		this._observers = new ArrayList<>();
		_bodiesUnmodifiable = Collections.unmodifiableList(_bodies);
		this._time = 0.0;
	}
	
	public void advance(){
		for(Body b: this._bodies)
		{
			b.resetForce();
		}
		
		this._forceLaws.apply(_bodies);
		
		for(Body b: this._bodies)
		{
			b.move(_dt);
		}
		_time += _dt;
		
		for(SimulatorObserver s:this._observers) {
			s.onAdvance(_bodiesUnmodifiable, _time);
		}
	}
	
	public void addBody(Body b){
		if(!_bodies.contains(b))
		{
			_bodies.add(b);
			
			for(SimulatorObserver s:this._observers) {
				s.onBodyAdded(_bodiesUnmodifiable, b);
			}
		}
		else
		{
			throw new IllegalArgumentException("Ya existe el cuerpo");
		}
	}
	
	public void reset() {
		
		this._bodies.clear();
		this._time = 0.0;
		
		for(SimulatorObserver s: this._observers) {
			s.onReset(this._bodiesUnmodifiable, this._time, this._dt, this._forceLaws.toString());
		}
	}
	
	public void setDeltaTime(double dt) {
		if(dt<0) {
			throw new IllegalArgumentException("DeltaTime no valido");
		}
		
		this._dt = dt;
		
		for(SimulatorObserver s:this._observers) {
			s.onDeltaTimeChanged(_dt);
		}
	}
	
	public void setForceLawsLaws(ForceLaws forceLaws) {
		if(forceLaws.equals(null)) {
			throw new IllegalArgumentException("Leyes de fuerza invalidas");
		}
		
		this._forceLaws = forceLaws;
		
		for(SimulatorObserver s:this._observers) {
			s.onForceLawsChanged(this._forceLaws.toString());
		}
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!this._observers.contains(o)) {
			this._observers.add(o);
			o.onRegister(this._bodiesUnmodifiable, this._time, this._dt, this._forceLaws.toString());
		}
	}
	
	public JSONObject getState() {
		JSONObject j = new JSONObject();
		JSONArray ja = new JSONArray();
		
		for(Body b:this._bodies) {
			ja.put(b.getState());
		}
		
		j.put("bodies", ja);
		j.put("time", this._time);
		
		return j;
	}
	
	public String toString(){
		return getState().toString();
	}
}
