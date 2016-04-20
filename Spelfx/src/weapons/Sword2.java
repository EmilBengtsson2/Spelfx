package weapons;

import java.awt.geom.Arc2D;
import java.util.ArrayList;

import entities.AnimateEntity;
import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.Player;
import others.Position;

public class Sword2 extends Entity implements Weapon {
	private static Image image = new Image("/PicResource/Sword.png");
	private final int RANGE = (int) image.getHeight(), BASE_DAMAGE = 20, BASE_COOLDOWN = 800;	
	private double theta;
	private final double dTheta = Math.PI /20;
	private Player player;
	private boolean activeAnimation;	
	private double leftmostAngle =-Math.PI/3, rightmostAngle = Math.PI/3;	
	private boolean rightSwing = true;
	private double lastAnimationTime;

	public Sword2(Player player) {
		super((int)player.getPosition().getX(), (int)player.getPosition().getY(), player.getWorld(), image.getWidth(), image.getHeight());		
		this.player = player;		
		theta = -Math.PI/3;
	}

	@Override
	public int getDamage() {	
		return BASE_DAMAGE;
	}
	//sword2förändring
	@Override
	public int getRange() {	
		return RANGE;
	}
	
	public void action() {
		if (!cooldown()) {
			activeAnimation = true;
			lastAnimationTime = System.currentTimeMillis();
			hit();
		}				
	}
	
	private boolean cooldown() {
		return !(System.currentTimeMillis() - lastAnimationTime > BASE_COOLDOWN);
	}

	@Override
	public void hit() {
		Arc2D.Double arc = new Arc2D.Double(player.getPosition().getX()-(RANGE + 15), player.getPosition().getY()-(RANGE + 15), (RANGE + 15) * 2, (RANGE + 15) * 2, Math.toDegrees(-Math.PI/3 + Math.PI/2 - player.getRotation()), Math.toDegrees(Math.PI/3 * 2), Arc2D.PIE);
		ArrayList<AnimateEntity> entities = getWorld().getEntityController().getMeleeHits(arc);
		for(AnimateEntity ae : entities) {
			System.out.println("Träff på: (" + ae.getPosition().getX() + ", " + ae.getPosition().getY() + ")" + ae.getClass());
		}
		System.out.println("-----------------------------------------------------------------------");		
	}	

	@Override
	public void paint(GraphicsContext gc) {	
		Position p = player.getPosition();			
		if (activeAnimation) {
			if (rightSwing) {
				theta += dTheta;
			}
			else if (!rightSwing) {				
				theta -= dTheta;
			}
			if (theta < leftmostAngle ) {
				activeAnimation = false;
				rightSwing = true;
			}
			else if (theta >rightmostAngle)  {
				activeAnimation = false;
				rightSwing =false; 
			}				
		}
		gc.translate(p.getX(), p.getY());
		gc.rotate(Math.toDegrees(theta));
		gc.translate(-(p.getX()), -(p.getY()));		
		gc.drawImage(image, p.getX(), p.getY()-RANGE);
		gc.translate(p.getX(), p.getY());
		gc.rotate(Math.toDegrees(-theta));
		gc.translate(-(p.getX()), -(p.getY()));		
	}
}
