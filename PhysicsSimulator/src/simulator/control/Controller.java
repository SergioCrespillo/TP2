package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	
	private PhysicsSimulator _sim; // para poder añadir los cuerpos que se leen del fichero en formato JSON al simulador
	private Factory<Body> _bodiesFactory; //para transformar estructuras JSON en objetos de tipo Body
	
	public Controller(PhysicsSimulator s, Factory<Body> bf)
	{
		this._sim = s;
		this._bodiesFactory = bf;
	}
	
	public void run(int steps, OutputStream out) 
	{
		int i = 0;
		PrintStream p = (out == null) ? null : new PrintStream(out);
		String info = "{ \"states\": [";
		while(i < steps)
		{
			info += _sim.toString() + ",";
			_sim.advance();
			i++; 
		}
		info += _sim.toString() + "] }";
		p.print(info);
		out = p;
	}
	
	public void loadBodies(InputStream in) 
	{
		 JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInupt.getJSONArray("bodies");
		 for (int i = 0; i < bodies.length(); i++)
		 _sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
	}
}
