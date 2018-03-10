import java.util.*;
import java.io.File;
import java.io.InputStream;
import java.lang.Exception.*;
import java.io.IOException;
import java.io.FileInputStream;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.event.*;
import javafx.beans.property.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.geometry.Orientation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.io.*;
import javafx.scene.text.Font;

public class MMenu extends Application {

	Canvas canvas = new Canvas(500, 700);
	Image bg = new Image("pic/Bug.png");
	GraphicsContext g = canvas.getGraphicsContext2D();
	BorderPane borderPane = new BorderPane();
	Scene scene = new Scene(borderPane, 700, 600);
	Label gameName = new Label("BUG BITS");
	List<Button> buttonList = new ArrayList<Button>();
	ScrollPane sp = new ScrollPane();
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = MMenu.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 36);
	Font bgFont2 = Font.loadFont(fontStream, 100);
	Main m;

	String[] name = { "Start", "Instructions", "Multiple Choice", "Set Questions", "Leader Board", "Quit" };

	// remember initial satge and create a new scene
	Stage starStage;
	Stage backStage;
	Scene backScene;

	// animation time
	public static final long duration = 40000;

	// num of circle
	public static final int count = 20;

	public MMenu() {

		gameName.setFont(bgFont2);

	}

	private void draw(GraphicsContext g, Image bg) {
		g.drawImage(bg, 10, 100);
	}

	private void press(ActionEvent event) {
		String text = ((Button) event.getSource()).getText();
		switch (text) {
		case "Start":
			m = new Main(backStage, backScene);
			starStage.setScene(m.setUp(starStage, 1));
			m.show(starStage, 1);

			break;
		case "Instructions":
			Instructions instruct = new Instructions(backScene, backStage, scene.getWidth(), scene.getHeight());
			try {
				starStage.setTitle("Instructions!");
				Label content = instructionLabel("Instructions.txt");
				content.setFont(bgFont);
				content.setStyle("-fx-text-fill: yellow;");
				// create a new scene
				starStage.setScene(instruct.instructionShow());
				instruct.scroll(content);
			} catch (IOException e) {
			}

			show(starStage);
			break;
		case "Multiple Choice":
			// goto game home to play game
			m = new Main(backStage, backScene);
			starStage.setScene(m.setUp(starStage, 2));

			m.show(starStage, 2);
			break;
		case "Set Questions":
			AddQuestions instruct1 = new AddQuestions(backScene, backStage, scene.getWidth(), scene.getHeight());
			try {
				starStage.setTitle("Set Instructions!");
				Label content1 = instructionLabel("howtoplay.txt");
				// create a new scene
				starStage.setScene(instruct1.instructionShow());

			} catch (IOException e) {
			}
			show(starStage);
			break;
		case "Leader Board":
			LeaderBoard lb = new LeaderBoard(backScene, backStage, scene.getWidth(), scene.getHeight(), false);
			starStage.setScene(lb.leaderBoardShow());
			show(starStage);
			break;
		case "Quit":
			starStage.close();
			break;
		default:
			break;
		}
	}

	public Label instructionLabel(String name) throws IOException {
		byte[] b = new byte[20048];
		String s = null;
		InputStream out = null;
		Label labelInstructions = null;
		try {
			File file = new File(name);
			out = new FileInputStream(file);
			int len;
			while ((len = out.read(b)) != -1) {
				s = new String(b, "utf-8");
			}
			labelInstructions = new Label(s);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return labelInstructions;
	}

	public Button createButton(String name) {
		Button button = new Button(name);
		return button;
	}

	private void reDrawRectangle() {
		g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		animate(scene.getWidth(), scene.getHeight());
	}

	public void animate(double endX, double endY) {
		gameName.setFont(bgFont);
		gameName.setFont(bgFont);
		Rectangle colors = new Rectangle(0, 0, endX, endY);
		LinearGradient lg2 = new LinearGradient(0f, 1f, 1f, 1f, true, CycleMethod.NO_CYCLE,
				new Stop[] { new Stop(0, Color.web("#f8bd55")), // web color
						new Stop(0.14, Color.web("#c0fe56")), new Stop(0.28, Color.web("#5dfbc1")),
						new Stop(0.43, Color.web("#64c2f8")), new Stop(0.57, Color.web("#be4af7")),
						new Stop(0.71, Color.web("#ed5fc2")), new Stop(0.85, Color.web("#ef504c")),
						new Stop(1, Color.web("#f2660f")), });
		colors.setFill(lg2);
		colors.widthProperty().bind(scene.widthProperty());
		colors.heightProperty().bind(scene.heightProperty());

		Group circles = new Group();
		for (int i = 0; i < count; i++) {
			Circle circle = new Circle(Math.random() * 30 + 30, Color.web("blue", 0.45)); // create
																							// circle,
																							// color:white,
																							// opacity:15%
			circle.setStroke(Color.web("white", 0.2)); // border_color: white,
														// opacity: 20%
			circle.setStrokeWidth(2); // boder-width
			circles.getChildren().add(circle);
		}

		Timeline timeline = new Timeline();
		circles.getChildren().forEach((circle) -> { // set animation of all
													// circles

			KeyFrame start = new KeyFrame(Duration.ZERO,
					new KeyValue(circle.translateXProperty(), Math.random() * scene.getWidth()),
					new KeyValue(circle.translateYProperty(), Math.random() * scene.getHeight()));
			KeyFrame end = new KeyFrame(new Duration(duration),
					new KeyValue(circle.translateXProperty(), Math.random() * scene.getWidth()),
					new KeyValue(circle.translateYProperty(), Math.random() * scene.getHeight()));

			timeline.getKeyFrames().addAll(start, end);
		});
		timeline.play();

		colors.setBlendMode(BlendMode.OVERLAY);

		borderPane.getChildren().add(circles);
		borderPane.getChildren().add(colors);

	}

	private void redraw() {
		g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		g.drawImage(bg, 10, 100);
	}

	public void fixPosition() {
		// set vertical layout of buttons
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().addAll(buttonList);
		vbox.setAlignment(Pos.CENTER);

		// set horizontal layout off buttons
		HBox hbox = new HBox();
		hbox.getChildren().add(gameName);
		hbox.setAlignment(Pos.CENTER);

		// put all into BorderPane and then put into parent root
		borderPane.getChildren().add(canvas);
		borderPane.setRight(vbox);
		borderPane.setTop(hbox);
	}

	public void setButton() {
		// set four buttons
		for (String s : name) {
			Button b = createButton(s);
			buttonList.add(b);
		}
		// set style and add EventLstener
		String cssButton = "-fx-border-radius: 20.0;-fx-background-color: green;"
				+ "-fx-background-radius: 20;-fx-text-fill: yellow; "
				+ "-fx-border-color: white;-fx-padding: 15 15 15 15";

		for (int i = 0; i < name.length; i++) {
			Button b = buttonList.get(i);
			b.setStyle(cssButton);
			b.setFont(bgFont);
			b.setOnAction(this::press);
		}
		borderPane.setStyle("-fx-background: ORANGE");
	}

	public void bindControl() {
		// Bind the width/height with scene
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());

		// redraw when resized
		canvas.widthProperty().addListener(observable -> redraw());
		canvas.heightProperty().addListener(observable -> redraw());
		draw(g, bg);
	}

	public void show(Stage stage) {
		stage.show();
	}

	public void start(Stage stage) {
		starStage = stage;
		backStage = stage;
		backScene = scene;
		stage.setTitle("Bug Bits!");
		stage.setScene(scene);
		bindControl();
		animate(scene.getWidth(), scene.getHeight());
		setButton();
		fixPosition();
		show(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
