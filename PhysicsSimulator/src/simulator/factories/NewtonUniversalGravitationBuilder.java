package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	
	public NewtonUniversalGravitationBuilder()
	{
		super._typeTag = "nlug";
	}
	
	public ForceLaws createTheInstance(JSONObject bodies)
	{
		return new NewtonUniversalGravitation();
	}
}
