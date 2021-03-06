package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EventBlock extends Block{
	
	private static Image image = new Image("/PicResource/EventBlock.png");

	public EventBlock(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		isSolid = false;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public void event() {
		world.setVictory(true);
	}

}
