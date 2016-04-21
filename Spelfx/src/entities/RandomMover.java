package entities;
import java.util.Random;

public abstract class RandomMover extends HostileEntity{
	
	protected Random rdm;
	protected int movementCounter, xDirection, yDirection;	
	

	public RandomMover(int x, int y, double width, double height) {
		super(x, y, width, height);		
		rdm = new Random();
		movementCounter = 0;			
	}	
	

}
