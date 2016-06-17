package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WallBlock extends Block {
	
	private static Image image = new Image("/PicResource/Block_Emerald.png");
	
	public WallBlock(int x, int y) {
		super (x, y, image.getWidth(), image.getHeight());
		isSolid = true;
		
	}	

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());		
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}

}
