package framework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Ram extends Application {
	private Stage primaryStage;
	//Kom gärna på ett bättre namn
	//JFrame är typ som stage i javafx (tror jag)
	
	private Display display;
	private Engine engine;
	private GraphicsContext gc;
	
	private final int WIDTH = 1200, HEIGHT = 900;	
	
	

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//"Målarduken"
		this.primaryStage = primaryStage;
		display = new Display(WIDTH*2, HEIGHT*2);		
		gc = display.getCanvas().getGraphicsContext2D();
		gc.setFill(Color.CORAL);
		gc.fillRect(0, 0, WIDTH*2, HEIGHT*2);		
		
		//I denna klassen finns "gameloopen"
		engine = new Engine(display, this);
		
		primaryStage.setTitle("Fönstrets namn");
		
		primaryStage.setWidth(WIDTH);
		primaryStage.setHeight(HEIGHT);
		primaryStage.setResizable(false);
		
		Group root = new Group();
		//Scene scene = new Scene(root, WIDTH, HEIGHT);
		root.getChildren().add(display.getCanvas());
		
		primaryStage.setScene(new Scene(root));
		
		primaryStage.show();
		
		engine.start();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
