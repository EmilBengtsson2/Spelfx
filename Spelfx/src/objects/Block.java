package objects;

import entities.Entity;
import others.World;

public abstract class Block extends Entity{
	protected boolean isSolid;

	public Block(int x, int y, World world, double width, double height) {
		super(x, y, world, width, height);
		
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public abstract void event();

}
