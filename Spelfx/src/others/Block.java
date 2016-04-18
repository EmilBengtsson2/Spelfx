package others;

import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Block extends Entity {
	
	private static Image image = new Image("/PicResource/Block.png");
	
	public Block(int x, int y, World world) {
		super (x, y, world,image.getWidth(), image.getHeight());		
		
	}	

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());		
	}

}
