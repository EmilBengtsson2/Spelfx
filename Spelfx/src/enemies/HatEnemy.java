package enemies;

import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import entities.AnimateEntity;
import entities.Entity;
import entities.HostileEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import objects.Block;
import others.Polygon;
import others.Position;

public class HatEnemy extends HostileEntity {

	private static Image image = new Image("/PicResource/HatEnemy.gif");

	private final static double SPEED = 3;
	private final static int HEALTH = 100;

	public HatEnemy(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		setStats(SPEED, HEALTH);
		setHitbox();
	}

	private void setHitbox() {
		double tempX = position.getX(), tempY = position.getY();
		Position[] temp = new Position[10];
		temp[0] = new Position(tempX + 20, tempY);
		temp[1] = new Position(tempX + width - 20, tempY);
		temp[2] = new Position(tempX + width, tempY + 35);
		temp[3] = new Position(tempX + width, tempY + 55);
		temp[4] = new Position(tempX + width - 13, tempY + 95);
		temp[5] = new Position(tempX + width / 2 + 10, tempY + height);
		temp[6] = new Position(tempX + width / 2 - 10, tempY + height);
		temp[7] = new Position(tempX + 13, tempY + 95);
		temp[8] = new Position(tempX, tempY + 55);
		temp[9] = new Position(tempX, tempY + 35);
		hitbox = new Polygon(temp, center);
	}

	@Override
	public void action() {

		double dx = position.getX() - player.getPosition().getX();
		double dy = position.getY() - player.getPosition().getY();

		if (dx > 0) {
			hitbox.move(-speed, 0);
			collisionHandling('X');
		} else if (dx != 0) {
			hitbox.move(speed, 0);
			collisionHandling('x');
		}
		if (dy > 0) {
			hitbox.move(0, -speed);
			collisionHandling('Y');
		} else if (dy != 0) {
			hitbox.move(0, speed);
			collisionHandling('y');
		}
	}

	@Override
	protected void collisionHandling(char XorY) {
		boolean move = true;
		if (XorY == 'x' || XorY == 'X') {
			Entity entity = getIntersectingObject();
			if (entity != null) {
				if (entity instanceof Block) {
					if (((Block) entity).isSolid()) {
						if (XorY == 'X') {
							hitbox.move(speed, 0);
							move = false;
						} else {
							hitbox.move(-speed, 0);
							move = false;
						}
					}
				}
			} else {
				entity = getIntersectingEntity();
				if (entity != null) {
					if (XorY == 'X') {
						hitbox.move(speed, 0);
						move = false;
					} else {
						hitbox.move(-speed, 0);
						move = false;
					}
				}
			}
		} else if (XorY == 'y' || XorY == 'Y') {
			Entity entity = getIntersectingObject();
			if (entity != null) {
				if (entity instanceof Block) {
					if (((Block) entity).isSolid()) {
						if (XorY == 'Y') {
							hitbox.move(0, speed);
							move = false;
						} else {
							hitbox.move(0, -speed);
							move = false;
						}
					}
				}
			} else {
				entity = getIntersectingEntity();
				if (entity != null) {
					if (XorY == 'Y') {
						hitbox.move(0, speed);
						move = false;
					} else {
						hitbox.move(0, -speed);
						move = false;
					}
				}
			}
		}
		if (move) {
			if (XorY == 'X')
				position.setX(position.getX() - speed);
			else if (XorY == 'x')
				position.setX(position.getX() + speed);
			else if (XorY == 'Y')
				position.setY(position.getY() - speed);
			else if (XorY == 'y')
				position.setY(position.getY() + speed);
		}
	}

	// Kollar om en kon skär gubbens hitbox
	@Override
	public AnimateEntity checkArcIntersection(Arc2D.Double arc) {
		if (arc.intersects(new Rectangle2D.Double(position.getX() + 15, position.getY() + 5, 70, 110)))
			return this;
		return null;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int) position.getX(), (int) position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}
}
