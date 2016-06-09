package objects;

import entities.Entity;
import others.Position;

public abstract class Block extends Entity{
	protected boolean isSolid;
	protected boolean eventTriggered;

	public Block(int x, int y, double width, double height) {
		super(x, y, width, height, new Position(x + width / 2, y + height / 2));
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public abstract void event();
	
	public boolean getEventStatus() {
		return eventTriggered;
	}

}
