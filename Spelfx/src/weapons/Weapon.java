package weapons;

import javafx.scene.canvas.GraphicsContext;

public interface Weapon {
	
	//T�nkte att alla "vapen" spelaren kan anv�nda implementerar denna, metoderna borde funka till allt tror jag
	
	public int getDamage();
	
	public int getRange();
	
	public void hit();
	
	public void Animation();
	
	public void paint(GraphicsContext gc);
}
