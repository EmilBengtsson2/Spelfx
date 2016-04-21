package entities;
import java.awt.geom.Arc2D.Double;
import java.util.Random;

import others.World;

public abstract class RandomMover extends HostileEntity{
	
	protected Random rdm;
	protected int movementCounter, xDirection, yDirection;	
	

	public RandomMover(int x, int y, double width, double height) {
		super(x, y, width, height);		
		rdm = new Random();
		movementCounter = 0;			
	}	
	

}
