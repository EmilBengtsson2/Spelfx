package framework;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import others.Position;

public class PlayerListener {
	
	private boolean mouseDown, left, right, up, down;
	private int horizontalMult, verticalMult, translatedX, translatedY;
	private Position mousePos;
	private Engine engine;
	private Canvas canvas;
	private boolean fullscreen;
	
	public PlayerListener(Engine engine, Canvas canvas) {
		this.engine = engine;
		this.canvas = canvas;
		fullscreen = false;
		horizontalMult = verticalMult = translatedX = translatedY = 0;
		mousePos = new Position(0, 0);
		setup();
	}
	
	public boolean getMouseDown() {
		return mouseDown;
	}
	
	public int getHorizontalMult() {
		return horizontalMult;
	}
	
	public int getVerticalMult() {
		return verticalMult;
	}
	
	public Position getMousePos() {
		return mousePos;
	}
	
	public void setTranslatedXY(int x, int y) {
		mousePos.setX(mousePos.getX() + translatedX);
		mousePos.setY(mousePos.getY() + translatedY);
		translatedX = x;
		translatedY = y;
		mousePos.setX(mousePos.getX() - translatedX);
		mousePos.setY(mousePos.getY() - translatedY);
	}

	public void setup() {
		canvas.setOnMousePressed(e -> {
			mouseDown = true;
		});
		
		canvas.setOnMouseReleased(e -> {
			mouseDown = false;
		});
		
		canvas.setOnMouseDragged(e -> {
			mousePos.setX(e.getX() - translatedX);
			mousePos.setY(e.getY() - translatedY);
			mouseDown = true;
		});
		
		canvas.setOnMouseMoved(e -> {
			mousePos.setX(e.getX() - translatedX);
			mousePos.setY(e.getY() - translatedY);
			//System.out.println("X: " + mousePos.getX() + "\tY: " + mousePos.getY());
		});
		
		canvas.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.W) {
				up = true;
				verticalMult = -1;
			} else if(e.getCode() == KeyCode.S) {
				down = true;
				verticalMult = 1;
			} else if(e.getCode() == KeyCode.A) {
				left = true;
				horizontalMult = -1;
			} else if(e.getCode() == KeyCode.D) {
				right = true;
				horizontalMult = 1;
			} else if(e.getCode() == KeyCode.NUMPAD0) {
				if(fullscreen)
					fullscreen = false;
				else
					fullscreen = true;
				engine.setFullscreen(fullscreen);
			}
		});
		
		canvas.setOnKeyReleased(e -> {
			if(e.getCode() == KeyCode.W) {
				up = false;
				if(down)
					verticalMult = 1;
				else
					verticalMult = 0;
			}
			else if(e.getCode() == KeyCode.S) {
				down = false;
				if(up)
					verticalMult = -1;
				else
					verticalMult = 0;
			}
			if(e.getCode() == KeyCode.A) {
				left = false;
				if(right)
					horizontalMult = 1;
				else
					horizontalMult = 0;
			}
			else if(e.getCode() == KeyCode.D) {
				right = false;
				if(left)
					horizontalMult = -1;
				else
					horizontalMult = 0;
			}
		});
	}
}
