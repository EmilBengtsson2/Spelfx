package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DoorBlock extends Block {
	
	private int x, y;
	private static Image image = new Image("/PicResource/HappyArrow.gif");

	public DoorBlock(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		this.x = x;
		this.y = y;
	}
	
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRect(x, y, 100, 100);
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}
}
