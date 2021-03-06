package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {

	private double G;
	
	public NewtonUniversalGravitation(double g) {
		G = g;
	}
	
	@Override
	public void apply(List<Body> bs) {
		for(Body bi:bs) {
			for(Body bj: bs) {
				if(!(bi.equals(bj))){
					double fij = forceIJ(bi,bj);
					Vector2D d = bj.getPosition().minus(bi.getPosition());
					Vector2D Fij = d.direction().scale(fij);
					
					bi.addForce(Fij);
				}
			}
		}
	}
	
	private double forceIJ(Body bi, Body bj) {
		double fij;
		Vector2D pos;
		
		pos = bj.getPosition().minus(bi.getPosition());
		fij = G * (bi.getMass()*bj.getMass())/Math.pow(pos.magnitude(), 2);
		
		return fij;
	}
	
	public String toString()
	{
		return "Newton's Universal Gravitation";
	}
}
