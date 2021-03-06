package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;


public class BuilderBasedFactory<T> implements Factory<T> {
	
	private List<Builder<T>> _builders; //es una lista de constructores
	private List<JSONObject> _factoryElements = new ArrayList<JSONObject>(); //una lista de objetos JSON construídos por defecto
	
	public BuilderBasedFactory(List<Builder<T>> builders) {	
		this._builders = new ArrayList<>(builders);
		
		for(Builder<?> b: this._builders){
			this._factoryElements.add(b.getBuilderInfo());
		}
	}
		
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException { 	
		try{
			for(Builder<T> b: _builders){
				if(b.createInstance(info) != null){
					return b.createInstance(info);
				}
			}
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Error en la instancia");
		}
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {  
		return this._factoryElements;
	}

}
