package enemies;

import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.AnimateEntity;
import entities.HostileEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.Player;
import others.World;

public class HatEnemy extends HostileEntity {	
	
	private static Image image = new Image("/PicResource/HatEnemy.gif");

	private Player player;
	private final static double SPEED = 0.7;
	

	public HatEnemy(int x, int y, Player player, World world) {
		super(SPEED, x, y, world, image.getWidth(), image.getHeight());
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
	
	//Kollar om en kon sk�r gubbens hitbox
	@Override
	public AnimateEntity checkArcIntersection(Arc2D.Double arc) {
		if(arc.intersects(new Rectangle2D.Double(position.getX()-15, position.getY()+10, 30, 90)))
			return this;
		return null;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.drawImage(image, (int)position.getX(), (int)position.getY());
	}
	
	
	

}
