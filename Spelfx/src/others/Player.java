package others;

import entities.AnimateEntity;
import framework.PlayerListener;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.ArcType;
import weapons.Weapon;

public class Player extends AnimateEntity {
	
	
	private static Image image = new Image("/PicResource/Player.gif");
	private PlayerListener listener;
	private Position mousePos;
	private Weapon weapon;
	private double rotation;
	
	public Player(double speed, int x, int y, PlayerListener listener, World world) {
		super(speed, x, y, world, image.getWidth(), image.getHeight());
		this.listener = listener;
		mousePos = listener.getMousePos();
		health = 5;
	}
	
	public void setPosition(double x, double y) {
		position.setX(x);
		position.setY(y);
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public Position getMousePosition() {
		return mousePos;
	}
	
	public boolean getMouseDown() {
		return listener.getMouseDown();
	}
	
	/**
	 * @return rotation in radians
	 */
	public double getRotation() {
		return rotation;
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	
	public double getHeight() {
		return image.getHeight();
	}
	
	@Override
	public void action() {
		
		position.setX(position.getX() + speed * listener.getHorizontalMult());
		position.setY(position.getY() + speed * listener.getVerticalMult());
		mousePos = listener.getMousePos();
		if(listener.getMouseDown())
			weapon.Animation();

	}
	
	@Override
	public void paint(GraphicsContext gc) {
		double x = position.getX() + (getWidth()/2);
		double y = position.getY() + (getHeight()/2);
		double mX = mousePos.getX();
		double mY = mousePos.getY();
		
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(Math.PI/2));
		gc.translate(-x, -y);
		
		rotation = Math.PI/2;
		
		if(mY < y) {
			if(mX > x) {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(-Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)))));
				gc.translate(-x, -y);
				rotation += -Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)));
			} else {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(Math.PI + Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.PI + Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)));
			}
		} else {
			if(mX > x) {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)));
			} else {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(Math.PI-Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.PI-Math.acos((Math.abs(mX-(x)))/Math.sqrt(Math.pow(y-mY, 2) + Math.pow(mX-(x), 2)));
			}
		}
		
		if(weapon != null)
			weapon.paint(gc);
		gc.drawImage(image, position.getX(), position.getY(), width, height);
		
		gc.translate(x, y);
		gc.rotate(Math.toDegrees(-rotation));
		gc.translate(-x, -y);
	}
}
