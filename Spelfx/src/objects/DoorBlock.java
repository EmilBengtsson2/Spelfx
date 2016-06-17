package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import others.Polygon;
import others.Position;

public class DoorBlock extends Block {

	private static Image image = new Image("/PicResource/Door_Horizontal.png");

	public DoorBlock(int x, int y, char VorH_orientation) {
		super(x, y, image.getWidth(), image.getHeight());
		isSolid = true;
		if(VorH_orientation == 'H') {
			setHitboxHorizontal();
		} else {
			image = new Image("/PicResource/Door_Vertical.png");
			setHitboxVertical();
		}
	}

	private void setHitboxHorizontal() {
		double tempX = position.getX(), tempY = position.getY();
		Position[] temp = new Position[4];
		temp[0] = new Position(tempX, tempY + height / 2 - 8);
		temp[1] = new Position(tempX, tempY + height / 2 + 8);
		temp[2] = new Position(tempX + width, tempY + height / 2 + 8);
		temp[3] = new Position(tempX + width, tempY + height / 2 - 8);
		hitbox = new Polygon(temp, center);
	}

	private void setHitboxVertical() {
		double tempX = position.getX(), tempY = position.getY();
		Position[] temp = new Position[4];
		temp[0] = new Position(tempX + width / 2 - 8, tempY);
		temp[1] = new Position(tempX + width / 2 + 8, tempY);
		temp[2] = new Position(tempX + width / 2 + 8, tempY + height);
		temp[3] = new Position(tempX + width / 2 - 8, tempY + height);
		hitbox = new Polygon(temp, center);
	}

	public void paint(GraphicsContext gc) {
		gc.drawImage(image, position.getX(), position.getY());
		drawHitbox(gc, hitbox.getPoints().length);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}
}
