package others;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import entities.AnimateEntity;
import entities.Entity;
import entities.HostileEntity;
import framework.PlayerListener;
import javafx.scene.canvas.GraphicsContext;

public class EntityController {

	private ArrayList<AnimateEntity> allEntities;
	private ArrayList<Entity> objects;	

	public EntityController(PlayerListener listener) {			
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
		for (Entity i : getObjects()) {
			i.paint(gc);
		}
		for (AnimateEntity e : allEntities) {
			e.paint(gc);
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
