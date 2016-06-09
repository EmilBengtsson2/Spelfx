package framework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import others.Player;

public class Ram extends Application {
	private Stage primaryStage;
	// Kom gärna på ett bättre namn

	private Display display;
	private Engine engine;
	private GraphicsContext gc;
	private Group root;

	private final int WIDTH = 1200, HEIGHT = 900;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// "Målarduken"
		this.primaryStage = primaryStage;
		display = new Display(WIDTH * 2, HEIGHT * 2);
		gc = display.getCanvas().getGraphicsContext2D();
		gc.setFill(Color.CORAL);
		gc.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);
		root = new Group();

		// I denna klassen finns "gameloopen"
		engine = new Engine(display, this);

		primaryStage.setTitle("Fönstrets namn");

		primaryStage.setWidth(WIDTH);
		primaryStage.setHeight(HEIGHT);
		Button b = start();
		Button bQuit = quit();
		TextArea ta = new TextArea();
		ta.setFont(new Font(50));
		ta.appendText("The Super Amazing Game");
		ta.setMaxHeight(60);
		BorderPane pane = new BorderPane();
		pane.setBottom(bQuit);
		pane.autosize();
		pane.setStyle(
				"-fx-background-image: url(\"/PicResource/RedSkull4.png\");-fx-background-size: 1200, 900;-fx-background-repeat: no-repeat;");
		pane.setCenter(b);
		pane.setTop(ta);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void scaleFullscreen(boolean fullscreen, Player player) {
		/*if (fullscreen) {	
			root.setScaleX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH);
			root.setScaleY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / HEIGHT);
		} else {
			root.setScaleX(1);
			root.setScaleY(1);
		}*/
	}

	private Button start() {

		Button b = new Button("Start");
		b.setOnAction(event -> {
			root.getChildren().add(display.getCanvas());
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			engine.start();
		});
		b.setMinSize(50, 50);

		return b;
	}

	private Button quit() {
		Button b = new Button("Quit");
		b.setMinSize(50, 50);
		b.setOnAction(event -> System.exit(0));
		return b;
	}

}
