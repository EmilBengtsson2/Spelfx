package objects;

import entities.Entity;
import others.World;

public abstract class Block extends Entity{
	protected boolean isSolid;
	protected boolean eventTriggered;

	public Block(int x, int y, double width, double height) {
		super(x, y, width, height);
		
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public abstract void event();
	
	public boolean getEventStatus() {
		return eventTriggered;
	}

}
