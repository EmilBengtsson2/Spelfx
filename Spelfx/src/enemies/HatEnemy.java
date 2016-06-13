package enemies;

import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

import entities.AnimateEntity;
import entities.Entity;
import entities.HostileEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import objects.Block;

public class HatEnemy extends HostileEntity {

	private static Image image = new Image("/PicResource/HatEnemy.gif");

	private final static double SPEED = 2.0;
	private final static int HEALTH = 100;

	public HatEnemy(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		setStats(SPEED, HEALTH);
	}

	@Override
	public void action() {

		double dx = position.getX() - player.getPosition().getX();
		double dy = position.getY() - player.getPosition().getY();

		if (dx > 0) {
			position.setX(position.getX() - speed);
			collisionHandling('X');
		} else {
			position.setX(position.getX() + speed);
			collisionHandling('x');
		}
		if (dy > 0) {
			position.setY(position.getY() - speed);
			collisionHandling('Y');
		} else {
			position.setY(position.getY() + speed);
			collisionHandling('y');
		}

	}

	@Override
	protected void collisionHandling(char XorY) {
		if (XorY == 'x' || XorY == 'X') {
			Entity entity = getIntersectingObject();
			if (entity != null)
				if (entity instanceof Block)
					if (((Block) entity).isSolid()) {
						if (XorY == 'X')
							position.setX(position.getX() + speed);
						else
							position.setX(position.getX() - speed);
					}
			entity = getIntersectingEntity();
			if (entity != null) {
				if (XorY == 'X')
					position.setX(position.getX() + speed);
				else
					position.setX(position.getX() - speed);
			}
		} else if (XorY == 'y' || XorY == 'Y') {
			Entity entity = getIntersectingObject();
			if (entity != null)
				if (entity instanceof Block)
					if (((Block) entity).isSolid()) {
						if (XorY == 'Y')
							position.setY(position.getY() + speed);
						else
							position.setY(position.getY() - speed);
					}
			entity = getIntersectingEntity();
			if (entity != null) {
				if (XorY == 'Y')
					position.setY(position.getY() + speed);
				else
					position.setY(position.getY() - speed);
			}
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
		gc.setStroke(Color.RED);
		gc.strokeRect(position.getX() + 15, position.getY() + 5, 70, 110);
		drawHitbox(gc, hitbox.getPoints().length);
	}
}
