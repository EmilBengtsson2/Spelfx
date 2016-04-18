package entities;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import others.Position;
import others.World;

public abstract class Entity {			
		
		protected World world;
		protected Position position;
		protected double width;
		protected double height;
		
		public Entity(int x, int y, World world, double width, double height) {	
			position = new Position(x, y);
			this.world = world;
			this.width = width;
			this.height = height;			
		}
		
		public Position getPosition() {
			return position;
		}
		public double getWidth() {
			return width;
		}
		public double getHeight() {
			return height;
		}
		
		public abstract void paint(GraphicsContext gc);
		
		//letar block
		public Entity getIntersectingObject() {
			ArrayList<Entity> objects = world.getObjects();
			for (Entity object : objects) {
				if (intersects(object)) {
					return object;
				}
			}
			return null;
		}
		//letar "levande" objekt
		public Entity getIntersectingEntity() {
			ArrayList<AnimateEntity> entities = world.getAnimateEntities();
			for (AnimateEntity ae : entities) {
				if (intersects(ae) && this!=ae) {
					return ae;
				}
			}
			return null;
		}
		//kollar korsande rektanglar mellan Entity1 och Entity2, returnerar true vid intersection.
		private boolean intersects(Entity e) {			
			Position e1UpperLeft = getPosition();
			Position e1LowerRight = new Position(getPosition().getX() + getWidth(),
					getPosition().getY() + getHeight());

			Position e2UpperLeft = e.getPosition();
			Position e2LowerRight = new Position(e.getPosition().getX() + e.getWidth(),
					e.getPosition().getY() + e.getHeight());

			if (e1UpperLeft.getX() > e2LowerRight.getX() || e1LowerRight.getX() < e2UpperLeft.getX()
					|| e1UpperLeft.getY() > e2LowerRight.getY() || e1LowerRight.getY() < e2UpperLeft.getY()) {
				return false;
			}
			return true;
		}

	}


