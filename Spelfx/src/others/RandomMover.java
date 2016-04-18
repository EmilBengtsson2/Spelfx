package others;
import java.awt.geom.Arc2D.Double;
import java.util.Random;

import entities.AnimateEntity;
import entities.HostileEntity;

public abstract class RandomMover extends HostileEntity{
	
	protected Random rdm;
	protected int movementCounter, xDirection, yDirection;	
	

	public RandomMover(double speed, int x, int y, World world, double d, double e) {
		super(speed, x, y, world, d, e);		
		rdm = new Random();
		movementCounter = 0;			
	}	
	
	public AnimateEntity checkArcIntersection(Double arc) {
		// TODO Auto-generated method stub
		return null;
	}

}
