package enemies;

import entities.RandomMover;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.World;

public class HappyArrow extends RandomMover{
	private static Image image = new Image("/PicResource/HappyArrow.gif");
	private final static double SPEED = 1.0;
	
	public HappyArrow(int x, int y) {
		super(SPEED, x, y, image.getWidth(), image.getHeight());
		
	}
	@Override
	public void action(){		
		if (movementCounter == 0) {
			xDirection = rdm.nextInt(2) *2 -1;
			movementCounter = 150;
		}
		position.setX(position.getX() + xDirection * speed);		
		movementCounter--;		
	}
	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int) position.getX(), (int) position.getY());
	}
}
