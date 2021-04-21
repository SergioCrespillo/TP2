package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws {

	protected double g;
	protected Vector2D c;
	
	public MovingTowardsFixedPoint(double gravedad, Vector2D direccion) {
		g = gravedad;
		c = direccion;
	}
	
	@Override
	public void apply(List<Body> bs) {
		for(Body bi:bs) {
			Vector2D f;
			
			if(c.getX() == 0 && c.getY() == 0) {
				f = bi.getPosition().direction().scale((-g)*bi.getMass());
			}
			else {
				f = c.minus(bi.getPosition()).direction().scale(bi.getMass()*(g)); 
			}
			
			bi.addForce(f);
		}
	}
	
	public String toString()
	{
		return "Moving Towards " + this.c + " with constant acceleration " + this.g;
	}
}
