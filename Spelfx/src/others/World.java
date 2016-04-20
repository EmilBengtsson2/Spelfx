package others;

import java.util.ArrayList;

import entities.AnimateEntity;
import entities.Entity;
import framework.PlayerListener;
import javafx.scene.canvas.GraphicsContext;

public class World {

	private EntityController entityController;
	private boolean victory;

	public World(PlayerListener listener) {
		entityController = new EntityController(listener);
		createWorld(listener);
	}

	public void updateWorld() {
		entityController.updateEntity();
		checkWin();
	}

	public void createWorld(PlayerListener listener) {
		LevelGenerator.generateLevel(this, listener, 1);
	}

	public void paintWorld(GraphicsContext gc) {
		entityController.paintEntity(gc);
	}	
	
	public void setVictory(Boolean win) {
		victory = win;
	}
	
	public void checkWin() {
		if (victory) {
			System.out.println("Du vann");
			victory=false;
		}
	}

	public EntityController getEntityController() {
		return entityController;
	}

	public ArrayList<Entity> getObjects() {
		return entityController.getObjects();
	}

	public ArrayList<AnimateEntity> getAnimateEntities() {
		return entityController.getAnimateEntities();
	}

}
