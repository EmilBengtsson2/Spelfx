package entities;

import javafx.scene.canvas.GraphicsContext;
import objects.Block;
import others.Position;
import others.World;

public abstract class AnimateEntity extends Entity{
	
	protected double speed;
	protected int health;
	
	public AnimateEntity(double speed, int x, int y, double width, double height) {
		super(x, y, width, height);
		this.speed = speed;
	}	
	
	public abstract void action();
	
	public void handleObjectCollision(Position oldPosition) {
		Entity entity =getIntersectingObject();
		if (entity!=null) {
			if (entity instanceof Block) {
				Block block = (Block) entity;
				if(block.isSolid()) {
					position.setX(oldPosition.getX());
					position.setY(oldPosition.getY());
				}				
			}			
		}
	}
	
	public void handleEntityCollision(Position oldPosition){
		Entity entity =getIntersectingEntity();
		if (entity!=null) {
			position.setX(oldPosition.getX());
			position.setY(oldPosition.getY());			
		}
	}
	
	public abstract void paint(GraphicsContext gc);	

}
