package framework;

import javafx.animation.AnimationTimer;
import others.World;

public class Engine {
	
	private Display display;
	private World world;

	private PlayerListener listener;
	
	public Engine (Display display) {
		this.display = display;
		listener = new PlayerListener(this, display.getCanvas());
		world = new World(listener);
	}

	public void start() {
		display.getCanvas().requestFocus();
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				//Efter denna kommentaren skrivs allt spelrelaterat som ska hända i varje "frame/cykel"
				///////////////////////////////////////////////////////////////////////////////////////
				world.updateWorld();
				repaint();
				///////////////////////////////////////////////////////////////////////////////////////
			}
			
		}.start();
	}
	
	//Fullskärm (typ)
	public void setFullscreen(boolean fullscreen) {
		/*ram.setFullscreen(fullscreen);
		display.resize((Toolkit.getDefaultToolkit().getScreenSize()));*/
	}

	private void repaint() {
		display.paint(world);
	}
}
