package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import others.Polygon;

public class DoorBlock {
	
	private int x, y;

	public DoorBlock(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.SADDLEBROWN);
		gc.fillRect(x, y, 100, 100);
	}
}
