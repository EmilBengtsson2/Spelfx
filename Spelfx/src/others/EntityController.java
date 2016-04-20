package others;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import entities.AnimateEntity;
import entities.Entity;
import entities.HostileEntity;
import framework.PlayerListener;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class EntityController {

	private PlayerListener listener;
	private ArrayList<AnimateEntity> allEntities;
	private ArrayList<Entity> objects;
	private int tX, tY;

	public EntityController(PlayerListener listener) {
		this.listener = listener;
		tX = tY = 0;
		allEntities = new ArrayList<AnimateEntity>();		
		objects = new ArrayList<Entity>();			
	}

	public void updateEntity() {
		for (AnimateEntity e : allEntities) {
			Position oldPosition = new Position(e.getPosition().getX(), e.getPosition().getY());
			e.action();
			e.handleObjectCollision(oldPosition);
			e.handleEntityCollision(oldPosition);
		}
	}

	public void paintEntity(GraphicsContext gc) {
		double playerPosX, playerPosY;
		for (AnimateEntity e : allEntities) {
			if(e instanceof Player) {
				gc.translate(-tX, -tY);
				tX = tY = 0;
				playerPosX = e.getPosition().getX();
				playerPosY = e.getPosition().getY();
				if(playerPosX > 600 && playerPosX < 1800) {
					tX = (int) -(playerPosX - 600);
				}
				if(playerPosY > 450 && playerPosY < 1350) {
					tY = (int) -(playerPosY - 450);
				}
				gc.translate(tX, tY);
				listener.setTranslatedXY(tX, tY);
				gc.setFill(Color.INDIGO);
				gc.fillArc(e.getPosition().getX()+e.getWidth()/2-(170), e.getPosition().getY()+e.getHeight()/2-(170), (170) * 2, (170) * 2, Math.toDegrees(-Math.PI/3 + Math.PI/2 - ((Player) e).getRotation()), Math.toDegrees(Math.PI/3 * 2), ArcType.ROUND);
				break;
			}
		}
		for (AnimateEntity e : allEntities) {
			e.paint(gc);
		}
		for (Entity i : getObjects()) {
			i.paint(gc);
		}
	}

	// Returns all enemies who got hit by the weapon (intersected the arc)
	public ArrayList<AnimateEntity> getMeleeHits(Arc2D.Double arc) {
		ArrayList<AnimateEntity> temp = new ArrayList<AnimateEntity>();
		for (Entity e : allEntities) {
			if (e instanceof HostileEntity) {
				AnimateEntity ae = ((HostileEntity) e).checkArcIntersection(arc);
				if (ae != null)
					temp.add(ae);
			}
		}
		return temp;
	}

	public ArrayList<Entity> getObjects() {
		return objects;
	}
	
	public ArrayList<AnimateEntity> getAnimateEntities() {
		return allEntities;
	}

	
}
