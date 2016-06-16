package enemies;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Arc2D.Double;

import entities.AnimateEntity;
import entities.Entity;
import entities.RandomMover;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import objects.Block;

public class RedSkull extends RandomMover {
	private static Image[] images = { new Image("/PicResource/RedSkull1.png"), new Image("/PicResource/RedSkull2.png"),
			new Image("/PicResource/RedSkull3.png"), new Image("/PicResource/RedSkull4.png"),
			new Image("/PicResource/RedSkull5.png"), new Image("/PicResource/RedSkull6.png") };
	private final static double SPEED = 1.5;
	private final static int HEALTH = 50;
	private int animationCounter = 0;
	private int imageIndex = 0;

	public RedSkull(int x, int y) {
		super(x, y, images[0].getWidth(), images[0].getHeight());
		setStats(SPEED, HEALTH);
	}

	@Override
	public void action() {
		if (movementCounter == 0) {
			xDirection = rdm.nextInt(3) - 1;
			yDirection = rdm.nextInt(3) - 1;
			movementCounter = 100;
		}
		hitbox.move(xDirection * speed, 0);
		collisionHandling('x');
		hitbox.move(0, yDirection * speed);
		collisionHandling('y');

		movementCounter--;
	}

	@Override
	protected void collisionHandling(char XorY) {
		boolean move = true;
		if (XorY == 'x') {
			Entity entity = getIntersectingObject();
			if (entity != null) {
				if (entity instanceof Block) {
					if (((Block) entity).isSolid()) {
						hitbox.move(-xDirection * speed, 0);
						move = false;
					}
				}
			} else {
				entity = getIntersectingEntity();
				if (entity != null) {
					hitbox.move(-xDirection * speed, 0);
					move = false;
				}
			}
		} else if (XorY == 'y') {
			Entity entity = getIntersectingObject();
			if (entity != null) {
				if (entity instanceof Block) {
					if (((Block) entity).isSolid()) {
						hitbox.move(0, -yDirection * speed);
						move = false;
					}
				}
			} else {
				entity = getIntersectingEntity();
				if (entity != null) {
					hitbox.move(0, -yDirection * speed);
					move = false;
				}
			}
		}
		if (move) {
			if (XorY == 'x')
				position.setX(position.getX() + xDirection * speed);
			else if (XorY == 'y')
				position.setY(position.getY() + yDirection * speed);
		}
	}

	@Override
	public void paint(GraphicsContext gc) {
		switchImage();
		gc.drawImage(images[imageIndex], (int) position.getX(), (int) position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}

	private void switchImage() {
		if (animationCounter >= 20) {
			imageIndex = (imageIndex + 1) % 6;
			animationCounter = 0;
		}
		animationCounter++;
	}

	@Override
	public AnimateEntity checkArcIntersection(Double arc) {
		if (arc.intersects(new Rectangle2D.Double(position.getX(), position.getY(), images[0].getWidth(),
				images[0].getHeight()))) {
			return this;
		}
		return null;
	}
}