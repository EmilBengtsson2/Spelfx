package weapons;

import javafx.scene.canvas.GraphicsContext;

public interface Weapon {
	
	//Tänkte att alla "vapen" spelaren kan använda implementerar denna, metoderna borde funka till allt tror jag
	
	public int getDamage();
	
	public int getRange();
	
	public void hit();
	
	public void Animation();
	
	public void paint(GraphicsContext gc);
}
