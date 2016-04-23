package framework;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import others.World;

public class Display {
	
	private static Image image = new Image("/PicResource/Dessert.png");

	private GraphicsContext gc;
	private Canvas canvas;

	public Display(int width, int height) {
		canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.AQUAMARINE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void paint(World world) {

		// Bakgrund
		//////////////////////////////////////////////////////////////////////////////
		gc.drawImage(image, 0, 0);
		//////////////////////////////////////////////////////////////////////////////

		// Ritar allt som inte �r bakgrunden (spelare, s� sm�ning om npcs
		// kanske, osv.)
		world.paintWorld(gc);
	}
}
