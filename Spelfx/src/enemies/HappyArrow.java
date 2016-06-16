package enemies;

import java.awt.geom.Arc2D.Double;
import java.awt.geom.Rectangle2D;

import entities.AnimateEntity;
import entities.Entity;
import entities.RandomMover;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.Block;
import others.Polygon;
import others.Position;

public class HappyArrow extends RandomMover {
	private static Image image = new Image("/PicResource/HappyArrow.gif");
	private final static double SPEED = 1.0;
	private final static int HEALTH = 200;

	public HappyArrow(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		setStats(SPEED, HEALTH);
		setHitbox();
	}

	private void setHitbox() {
		double tempX = position.getX(), tempY = position.getY();
		Position[] temp = new Position[6];
		temp[0] = new Position(tempX, tempY + height / 2);
		temp[1] = new Position(tempX + 50, tempY);
		temp[2] = new Position(tempX + width - 50, tempY);
		temp[3] = new Position(tempX + width, tempY + height / 2);
		temp[4] = new Position(tempX + width - 50, tempY + height);
		temp[5] = new Position(tempX + 50, tempY + height);
		hitbox = new Polygon(temp, center);
	}

	@Override
	public void action() {
		if (movementCounter == 0) {
			xDirection = rdm.nextInt(2) * 2 - 1;
			movementCounter = 150;
		}
		hitbox.move(xDirection * speed, 0);
		collisionHandling('x');

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
		}
		if (move) {
			position.setX(position.getX() + xDirection * speed);
		}
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int) position.getX(), (int) position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public AnimateEntity checkArcIntersection(Double arc) {
		if (arc.intersects(
				new Rectangle2D.Double(position.getX(), position.getY(), image.getWidth(), image.getHeight()))) {
			return this;
		}
		return null;
	}
}
