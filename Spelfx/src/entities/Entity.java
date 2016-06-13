package entities;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import others.Player;
import others.Polygon;
import others.Position;
import others.World;

public abstract class Entity {			
		
		protected static World world;
		protected static Player player;
		protected Position position;
		protected double width;
		protected double height;
		protected Polygon hitbox;
		protected Position center;
		
		public Entity(int x, int y, double width, double height, Position center) {	
			position = new Position(x, y);			
			this.width = width;
			this.height = height;
			this.center = center;
			Position[] temp = new Position[4];
			temp[0] = new Position(x, y);
			temp[1] = new Position(x + width, y);
			temp[2] = new Position(x + width, y + height);
			temp[3] = new Position(x, y + height);
			hitbox = new Polygon(temp, center);
		}
		
		public Position getCenter() {
			return center;
		}
		
		public Polygon getHitbox() {
			return hitbox;
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
		public World getWorld(){
			return world;
		}
		
		public abstract void paint(GraphicsContext gc);
		
		protected void drawHitbox(GraphicsContext gc, int nodes) {
			gc.setStroke(Color.ORANGERED);
			gc.strokePolygon(hitbox.getXPoints(), hitbox.getYPoints(), nodes);
		}
		
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


