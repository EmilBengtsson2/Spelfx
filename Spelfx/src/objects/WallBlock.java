package objects;

import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.World;

public class WallBlock extends Block {
	
	private static Image image = new Image("/PicResource/Block.png");
	
	public WallBlock(int x, int y, World world) {
		super (x, y, world,image.getWidth(), image.getHeight());
		isSolid = true;
		
	}	

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());		
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}

}
