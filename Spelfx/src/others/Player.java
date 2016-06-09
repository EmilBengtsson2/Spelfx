package others;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entities.AnimateEntity;
import entities.Entity;
import framework.PlayerListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import objects.Block;
import objects.EventBlock;
import weapons.Weapon;

public class Player extends AnimateEntity {

	// private static Image image = new Image("/PicResource/Player.gif");
	private static BufferedImage image;
	private Image[] images = new Image[5];
	private PlayerListener listener;
	private Position mousePos;
	private Weapon weapon;
	private double rotation;
	private int tX, tY;
	private final static double SPEED = 4.0;
	private final static int HEALTH = 20;

	public Player(int x, int y, PlayerListener listener, World world) {
		super(x, y, 70, 50);
		loadImages();
		setWorld(world);
		setPlayer(this);
		setStats(SPEED, HEALTH);
		this.listener = listener;
		mousePos = listener.getMousePos();
		tX = tY = 0;
	}

	private void loadImages() {
		try {
			image = ImageIO.read(getClass().getResource("/misterkenobi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		WritableImage temp = null;
		if (image != null) {
			for (int i = 0; i < 3; i++) {
				temp = SwingFXUtils.toFXImage(image.getSubimage(i * 71, 0, 70, 50), null);
				images[i] = new ImageView(temp).getImage();
			}
			if (temp == null)
				System.out.println("nisse");
		}
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

	@Override
	public void action() {
		position.setX(position.getX() + speed * listener.getHorizontalMult());
		collisionHandling('x');

		position.setY(position.getY() + speed * listener.getVerticalMult());
		collisionHandling('y');

		mousePos = listener.getMousePos();
		if (listener.getMouseDown())
			weapon.animation();
	}

	protected void collisionHandling(char XorY) {
		if (XorY == 'x') {
			Entity entity = getIntersectingObject();
			if (entity != null)
				if (entity instanceof Block) {
					if (((Block) entity).isSolid())
						position.setX(position.getX() - speed * listener.getHorizontalMult());
					else if (entity instanceof EventBlock && !((Block) entity).getEventStatus())
						((Block) entity).event();
				}
			entity = getIntersectingEntity();
			if (entity != null)
				position.setX(position.getX() - speed * listener.getHorizontalMult());
		} else if (XorY == 'y') {
			Entity entity = getIntersectingObject();
			if (entity != null)
				if (entity instanceof Block) {
					if (((Block) entity).isSolid())
						position.setY(position.getY() - speed * listener.getVerticalMult());
					else if (entity instanceof EventBlock && !((Block) entity).getEventStatus())
						((Block) entity).event();
				}
			entity = getIntersectingEntity();
			if (entity != null)
				position.setY(position.getY() - speed * listener.getVerticalMult());
		}
	}

	@Override
	public void paint(GraphicsContext gc) {
		centerPlayer(gc);

		double x = position.getX() + (getWidth() / 2);
		double y = position.getY() + (getHeight() / 2);
		double mX = mousePos.getX();
		double mY = mousePos.getY();

		gc.translate(x, y);
		gc.rotate(Math.toDegrees(Math.PI / 2));
		gc.translate(-x, -y);

		rotation = Math.PI / 2;

		if (mY < y) {
			if (mX > x) {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(
						-Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)))));
				gc.translate(-x, -y);
				rotation += -Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)));
			} else {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(Math.PI
						+ Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.PI
						+ Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)));
			}
		} else {
			if (mX > x) {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(
						Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)));
			} else {
				gc.translate(x, y);
				gc.rotate(Math.toDegrees(Math.PI
						- Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)))));
				gc.translate(-x, -y);
				rotation += Math.PI
						- Math.acos((Math.abs(mX - (x))) / Math.sqrt(Math.pow(y - mY, 2) + Math.pow(mX - (x), 2)));
			}
		}

		if (weapon != null)
			weapon.paint(gc);
		gc.drawImage(images[0], position.getX(), position.getY(), width, height);

		gc.translate(x, y);
		gc.rotate(Math.toDegrees(-rotation));
		gc.translate(-x, -y);
	}

	private void centerPlayer(GraphicsContext gc) {
		double playerPosX, playerPosY;
		gc.translate(-tX, -tY);
		tX = tY = 0;
		playerPosX = getPosition().getX();
		playerPosY = getPosition().getY();
		if (playerPosX > 600 - getWidth() / 2 && playerPosX < 1800 - getWidth() / 2) {
			tX = (int) -(playerPosX - (600 - getWidth() / 2));
		} else if (playerPosX >= 1800 - getWidth() / 2) {
			tX = (int) -(1800 - getWidth() / 2 - (600 - getWidth() / 2));
		}
		if (playerPosY > 450 - getHeight() / 2 && playerPosY < 1350 - getHeight() / 2) {
			tY = (int) -(playerPosY - (450 - getHeight() / 2));
		} else if (playerPosY >= 1350 - getHeight() / 2) {
			tY = (int) -(1350 - getHeight() / 2 - (450 - getHeight() / 2));
		}
		gc.translate(tX, tY);
		listener.setTranslatedXY(tX, tY);
	}

	private void setWorld(World world) {
		Entity.world = world;
	}

	private void setPlayer(Player player) {
		Entity.player = player;
	}
}
