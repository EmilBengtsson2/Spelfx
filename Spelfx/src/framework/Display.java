package framework;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import others.World;

public class Display {

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
		gc.setFill(Color.AQUAMARINE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//////////////////////////////////////////////////////////////////////////////

		// Ritar allt som inte är bakgrunden (spelare, så småning om npcs
		// kanske, osv.)
		world.paintWorld(gc);
	}
}
