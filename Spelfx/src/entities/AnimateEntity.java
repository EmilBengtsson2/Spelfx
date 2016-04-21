package entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class AnimateEntity extends Entity{
	
	protected double speed;
	protected int health;
	
	public AnimateEntity(int x, int y, double width, double height) {
		super(x, y, width, height);		
	}	
	
	public abstract void action();
	
	protected abstract void collisionHandling(char XorY);
	
	public void takeDamage(int damage) {
		health = health - damage;
		if (health <=0) {
			world.getAnimateEntities().remove(this);
		}
	}
	
	public void setStats(double speed, int health) {
		this.speed = speed;
		this.health = health;
	}
	
	public abstract void paint(GraphicsContext gc);	

}
