package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.excepcions.ControllerException;
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
	
	public void run(int steps, OutputStream out, InputStream expOut, StateComparator cmp) throws ControllerException 
	{
		PrintStream p = new PrintStream(out);
		JSONObject j = new JSONObject();
		
		p.println("{");
		p.println("\"states\": [");
		
		j = this._sim.getState();
		p.println(j);
		
		if(expOut != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(expOut));
			JSONArray j1 = jsonInput.getJSONArray("states");
			
			if(!cmp.equal(j, j1.getJSONObject(0))) {
				throw new ControllerException("Fallo en el paso numero " + steps);
			}
		}
		
		for(int i=1;i<steps;i++) {
			this._sim.advance();
			j = this._sim.getState();
			p.println(",");
			p.println(j);
			
			if(expOut != null) {
				JSONObject jsonInput = new JSONObject(new JSONTokener(expOut));
				JSONArray j1 = jsonInput.getJSONArray("states");
				
				if(!cmp.equal(j, j1.getJSONObject(i))) {
					throw new ControllerException("Fallo en el paso numero " + steps);
				}
			}
		}
		p.println("]");
		p.println("}");
	}
	
	public void loadBodies(InputStream in) 
	{
		 JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInupt.getJSONArray("bodies");
		 
		 for (int i = 0; i < bodies.length(); i++)
			 _sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
	}
}
