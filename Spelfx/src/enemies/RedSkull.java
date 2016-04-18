package enemies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.RandomMover;
import others.World;

public class RedSkull extends RandomMover {
	private static Image[] images = {
			new Image("/PicResource/RedSkull1.png"),
			new Image("/PicResource/RedSkull2.png"),
			new Image("/PicResource/RedSkull3.png"), 
			new Image("/PicResource/RedSkull4.png"),
			new Image("/PicResource/RedSkull5.png"), 
			new Image("/PicResource/RedSkull6.png")};
	private final static double SPEED = 1.5;
	private int animationCounter;
	private int imageIndex;

	public RedSkull(int x, int y, World world) {
		super(SPEED, x, y, world, images[0].getWidth(), images[0].getHeight());
		animationCounter = 0;
		imageIndex = 0;		
	}

	@Override
	public void action() {	
		if (movementCounter == 0) {
			xDirection = rdm.nextInt(3) - 1;
			yDirection = rdm.nextInt(3) - 1;
			movementCounter = 100;
		}
		position.setX(position.getX() + xDirection * speed);
		position.setY(position.getY() + yDirection * speed);
		movementCounter--;
		

	}

	@Override
	public void paint(GraphicsContext gc) {
		switchImage();
		gc.drawImage(images[imageIndex], (int) position.getX(), (int) position.getY());
	}

	private void switchImage() {
		if (animationCounter >= 20) {
			imageIndex = (imageIndex + 1) % 6;
			animationCounter = 0;
		}
		animationCounter++;
	}
}