package simulator.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double _time;  // n�mero de pasos que se ejecuta la simulaci�n
	private ForceLaws _forceLaws;  // leyes de la fuerza a aplicar
	private List<Body> _bodies = new ArrayList<>();  // cuerpos de la simulaci�n
	private double _dt;  // incremento del tiempo
	
	public PhysicsSimulator(ForceLaws FL, double tRealporPaso)
	{
		if(FL != null)
		{
			this._forceLaws = FL;
		}
		else
		{
			throw new IllegalArgumentException("Ley de fuerza incorrecta");
		}
		
		if(tRealporPaso >= 0)
		{
			this._time = tRealporPaso;
		}
		else
		{
			throw new IllegalArgumentException("Tiempo no v�lido");
		}
		this._dt = 0.0;
	}
	
	public void advance(){
		for(Body b: this._bodies)
		{
			b.resetForce();
		}
		this._forceLaws.apply(_bodies);
		for(Body b: this._bodies)
		{
			b.move(_time);
			_dt += _time;
		}
	}
	
	public void addBody(Body b){
		if(!_bodies.contains(b))
		{
			_bodies.add(b);
		}
		else
		{
			throw new IllegalArgumentException("Ya existe el cuerpo");
		}
	}
	
	public JSONObject getState() {
		String cadena = new String();
		cadena = "{ \"time\":" + this._dt + ", \"bodies\": [ ";
		for(int i = 0; i < _bodies.size(); i ++)
		{
			if(i != _bodies.size() - 1)
			{
				cadena = cadena + _bodies.get(i).toString() + ", ";
			}
			else
			{
				cadena = cadena + _bodies.get(i).toString();
			}
		}
		cadena += " ] }";
		return cadena;
	}
	
	public String toString(){
		return getState().toString();
	}
}
