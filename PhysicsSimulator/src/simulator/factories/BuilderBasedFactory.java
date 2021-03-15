package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;


public class BuilderBasedFactory<T> implements Factory<T> {
	
	private List<Builder<T>> _builders; //es una lista de constructores
	private List<JSONObject> _factoryElements; //una lista de objetos JSON construídos por defecto
	
	public BuilderBasedFactory(List<Builder<T>> builders) {	
		this._builders = new ArrayList<>(builders);
		this._factoryElements = new ArrayList<JSONObject>();
		
		for(Builder<T> b: this._builders){
			this._factoryElements.add(b.getBuilderInfo());
		}
	}
		
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException { 	
		if (info != null) {
			for (Builder<T> bb : _builders) {
				T o = bb.createInstance(info);
				if (o != null)
					return o;
			}
		}

		throw new IllegalArgumentException("Invalid value for createInstance: " + info);
	}

	@Override
	public List<JSONObject> getInfo() {  
		return this._factoryElements;
	}

}
