package weapons;

import java.awt.geom.Arc2D;
import java.util.ArrayList;

import entities.AnimateEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import others.EntityController;
import others.Player;
import others.Position;

public class Sword implements Weapon {
	
	private Position p;
	private final int RANGE = 100, BASE_DAMAGE = 20, BASE_COOLDOWN = 500;
	private long lastAnimationTime;
	private int animation, xDislocation;
	private double theta;
	private final double dTheta = Math.PI / 3 / 10 * 2;
	private Player player;
	private EntityController entity;
	
	public Sword(Player player, EntityController entity) {
		this.player = player;
		this.entity = entity;
		theta = -Math.PI/3;
		xDislocation = -10;
		animation = 0;
	}

	@Override
	public int getDamage() {
		return BASE_DAMAGE;
	}
	
	@Override
	public int getRange() {
		return RANGE;
	}
		
	@Override
	public void hit() {
		Arc2D.Double arc = new Arc2D.Double(player.getPosition().getX()-(RANGE + 15), player.getPosition().getY()-(RANGE + 15), (RANGE + 15) * 2, (RANGE + 15) * 2, Math.toDegrees(-Math.PI/3 + Math.PI/2 - player.getRotation()), Math.toDegrees(Math.PI/3 * 2), Arc2D.PIE);
		ArrayList<AnimateEntity> entities = entity.getMeleeHits(arc);
		for(AnimateEntity ae : entities) {
			System.out.println("Träff på: (" + ae.getPosition().getX() + ", " + ae.getPosition().getY() + ")");
		}
		System.out.println("-----------------------------------------------------------------------");
	}
	
	@Override
	public void animation() {
		if(animation != 0 || System.currentTimeMillis() - lastAnimationTime > BASE_COOLDOWN) {
			if(animation == 0) {
				lastAnimationTime = System.currentTimeMillis();
				if(xDislocation < 0)
					animation++;
				else
					animation--;
				hit();
			}
			if(animation > 0) {
				theta += dTheta;
				xDislocation += 1;
				animation++;
			} else {
				theta -= dTheta;
				xDislocation -= 1;
				animation--;
			}
			
			if(Math.abs(animation) % 11 == 0)
				animation = 0;
		}
	}

	@Override
	public void paint(GraphicsContext gc) {
		p = player.getPosition();
		
		if(animation != 0)
			animation();
		
		gc.setFill(Color.DIMGRAY);
		gc.translate(p.getX()+xDislocation+5, p.getY()-20);
		gc.rotate(Math.toDegrees(theta));
		gc.translate(-(p.getX()+xDislocation+5), -(p.getY()-20));
		gc.fillRect((int)p.getX()+xDislocation, (int)p.getY()-20-RANGE, 10, RANGE);
		gc.translate(p.getX()+xDislocation+5, p.getY()-20);
		gc.rotate(Math.toDegrees(-theta));
		gc.translate(-(p.getX()+xDislocation+5), -(p.getY()-20));
	}
}
