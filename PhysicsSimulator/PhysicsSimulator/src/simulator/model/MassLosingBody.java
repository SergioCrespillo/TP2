package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body {

	protected double lossFactor;
	protected double lossFrequency;
	protected double contador;
	
	public MassLosingBody(String id, Vector2D v, Vector2D p, double m, double lFa, double lFr) {
		super(id, v, p, m);
		lossFactor = lFa;
		lossFrequency = lFr;
		contador = 0.0;
	}
	
	void move(double t) {
		super.move(t);
		
		contador += t;
		
		if(contador >= lossFrequency) {
			mass = mass*(1-lossFactor);
			contador = 0.0;
		}
	}
}
