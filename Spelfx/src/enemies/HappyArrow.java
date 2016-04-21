package enemies;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Arc2D.Double;

import entities.AnimateEntity;
import entities.Entity;
import entities.RandomMover;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import objects.Block;

public class HappyArrow extends RandomMover{
	private static Image image = new Image("/PicResource/HappyArrow.gif");
	private final static double SPEED = 1.0;
	private final static int HEALTH = 200;
	
	public HappyArrow(int x, int y) {
		super(x, y, image.getWidth(), image.getHeight());
		setStats(SPEED, HEALTH);
		
	}
	
	@Override
	public void action(){		
		if (movementCounter == 0) {
			xDirection = rdm.nextInt(2) *2 -1;
			movementCounter = 150;
		}
		position.setX(position.getX() + xDirection * speed);
		collisionHandling('x');
		
		movementCounter--;		
	}
	
	@Override
	protected void collisionHandling(char XorY) {
		if (XorY == 'x') {
			Entity entity = getIntersectingObject();
			if (entity != null)
				if (entity instanceof Block)
					if (((Block) entity).isSolid())
						position.setX(position.getX() - xDirection * speed);
			entity = getIntersectingEntity();
			if (entity != null)
				position.setX(position.getX() - xDirection * speed);
		}
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int) position.getX(), (int) position.getY());
	}
	
	@Override
	public AnimateEntity checkArcIntersection(Double arc) {
		if(arc.intersects(new Rectangle2D.Double(position.getX(), position.getY(), image.getWidth(), image.getHeight()))) {
			return this;	
		}
		return null;
	}
}
