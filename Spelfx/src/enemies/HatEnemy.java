package enemies;

import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.AnimateEntity;
import entities.HostileEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import others.Player;
import others.World;

public class HatEnemy extends HostileEntity {	
	
	private static Image image = new Image("/PicResource/HatEnemy.gif");

	private Player player;
	private final static double SPEED = 2.0;
	

	public HatEnemy(int x, int y, Player player) {
		super(SPEED, x, y, image.getWidth(), image.getHeight());
		this.player = player;
		new Random();
		health = 2;		
	}

	@Override
	public void action() {
		
		double dx = position.getX()-player.getPosition().getX();
		double dy = position.getY()-player.getPosition().getY();

		if (dx > 0) {
			position.setX(position.getX() - speed);
		} else{
			position.setX(position.getX() + speed);
		}
		if (dy>0) {
			position.setY(position.getY() - speed);
		} else {
			position.setY(position.getY() + speed);
		}

	}
	
	//Kollar om en kon skär gubbens hitbox
	@Override
	public AnimateEntity checkArcIntersection(Arc2D.Double arc) {
		if(arc.intersects(new Rectangle2D.Double(position.getX()+15, position.getY()+5, 70, 110)))
			return this;
		return null;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());
		gc.setStroke(Color.RED);
		gc.strokeRect(position.getX()+15, position.getY()+5, 70, 110);
	}
	
	
	

}
