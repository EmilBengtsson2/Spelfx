package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DoorBlock extends Block {
	
	private static Image image = new Image("/PicResource/Door_horizontal.png");

	public DoorBlock(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		isSolid = true;
	}
	
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, position.getX(), position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}
}
