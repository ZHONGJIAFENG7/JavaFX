import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.canvas.*;
import javafx.geometry.Orientation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.io.*;
import javafx.scene.text.Font;

public class Instructions {
	Stage instructStage;
	Scene instructScene;
	double width, height;
	Canvas canvas = new Canvas(1000, 700);
	Image bg = new Image("pic/Bug.png");
	GraphicsContext g = canvas.getGraphicsContext2D();
	BorderPane borderPane = new BorderPane();
	BorderPane borderPane1 = new BorderPane();
	Scene scene = new Scene(borderPane, 1000, 700);
	Button back = new Button("back");
	MMenu menu = new MMenu();
	ScrollPane sp = new ScrollPane();
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 36);

	public Instructions() {
	}

	private void draw(GraphicsContext g, Image bg) {
		g.drawImage(bg, 0, 0);
	}

	public Instructions(Scene scene, Stage stage, double width, double height) {
		this.instructScene = scene;
		this.instructStage = stage;
		this.width = width;
		this.height = height;
	}

	private void setLayout() {

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: #336699;");
		hbox.getChildren().add(back);

		borderPane.getChildren().add(canvas);
		borderPane.setStyle("-fx-background-color: GREEN;");
		borderPane.setBottom(hbox);
		borderPane.setCenter(sp);
	}

	private void press(ActionEvent event) {
		instructStage.setTitle("Bug bits!");
		instructStage.setScene(instructScene);
		menu.show(instructStage);
	}

	private void redraw() {
		System.out.println("start redraw");
		g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		g.drawImage(bg, canvas.getWidth() / 4, canvas.getHeight() / 4);
	}

	public void scroll(Label content) {
		sp.setPrefSize(200, 200);
		borderPane1.setCenter(content);
		borderPane1.setStyle("-fx-background-color: GREEN;");
		sp.setContent(borderPane1);
		sp.vvalueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				content.setLayoutY(-new_val.intValue());
				System.out.println(new_val.intValue());
			}
		});
		sp.hvalueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				content.setLayoutY(-new_val.intValue());
				System.out.println(new_val.intValue());
			}
		});
		setLayout();
	}

	public Scene instructionShow() {
		back.setOnAction(this::press);
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());

		// redraw when resized
		canvas.widthProperty().addListener(observable -> redraw());
		canvas.heightProperty().addListener(observable -> redraw());
		return scene;
	}
}