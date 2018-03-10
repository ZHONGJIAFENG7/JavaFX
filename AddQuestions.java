import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.event.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Pos;
import javafx.scene.canvas.*;
import javafx.geometry.Orientation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AddQuestions {
	Stage instructStage;
	Scene instructScene;
	double width, height;
	Canvas canvas = new Canvas(400, 300);
	Image bg = new Image("pic/Bug.png");
	// Group root = new Group();
	// ImageView bgView = new ImageView(bg);
	GraphicsContext g = canvas.getGraphicsContext2D();
	GridPane gridPane = new GridPane();

	Scene scene = new Scene(gridPane, 1100, 800);
	Button addbtn = new Button("add");
	Button savebtn = new Button("save");
	Button backbtn = new Button("back");
	Button clearbtn = new Button("clear");
	MMenu menu = new MMenu();
	Label label1 = new Label("Question");
	Label label2 = new Label("Correct Answer");

	Label label3 = new Label("Wrong Answer");

	Label label4 = new Label("Wrong Answer");

	Label label5 = new Label("Wrong Answer");

	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	TextField tf3 = new TextField();
	TextField tf4 = new TextField();
	TextField tf5 = new TextField();

	int finalRow = 2;
	MFileReader mfr;

	private void draw(GraphicsContext g, Image bg) {
		g.drawImage(bg, 0, 0);
	}

	public AddQuestions(Scene scene, Stage stage, double width, double height) {
		this.instructScene = scene;
		this.instructStage = stage;
		this.width = width;
		this.height = height;
		setLayout();
		mfr = new MFileReader("questions.txt");
		updateTextFieldValue();

	}

	private void setLayout() {

		// gridPane.getChildren().add(addbtn);
		gridPane.setHgap(10);
		gridPane.setHgap(5);
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		ColumnConstraints col4 = new ColumnConstraints();
		ColumnConstraints col5 = new ColumnConstraints();

		col1.setPercentWidth(30);
		col2.setPercentWidth(10);
		col3.setPercentWidth(10);
		col4.setPercentWidth(10);
		col5.setPercentWidth(10);
		gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
		gridPane.getChildren().addAll(label1, label2, label3, label4, label5, tf1, tf2, tf3, tf4, tf5, addbtn, backbtn,
				savebtn, clearbtn);

		gridPane.setConstraints(label1, 0, 0);
		gridPane.setConstraints(label2, 1, 0);
		gridPane.setConstraints(label3, 2, 0);
		gridPane.setConstraints(label4, 3, 0);
		gridPane.setConstraints(label5, 4, 0);

		gridPane.setConstraints(tf1, 0, 1);
		gridPane.setConstraints(tf2, 1, 1);
		gridPane.setConstraints(tf3, 2, 1);
		gridPane.setConstraints(tf4, 3, 1);
		gridPane.setConstraints(tf5, 4, 1);
		/*
		 * gridPane.setConstraints(addbtn, 0,3);
		 * gridPane.setConstraints(savebtn, 1,3);
		 * gridPane.setConstraints(backbtn, 2,3);
		 */

		gridPane.setConstraints(addbtn, 5, 2);
		gridPane.setConstraints(savebtn, 6, 2);
		gridPane.setConstraints(backbtn, 7, 2);
		gridPane.setConstraints(clearbtn, 8, 2);

	}

	private void backPress(ActionEvent event) {
		instructStage.setTitle("Bug bits!");

		instructStage.setScene(instructScene);

		menu.show(instructStage);
	}

	private void addRow(ActionEvent event) {

		TextField new1 = new TextField();
		TextField new2 = new TextField();
		TextField new3 = new TextField();
		TextField new4 = new TextField();
		TextField new5 = new TextField();

		gridPane.getChildren().addAll(new1, new2, new3, new4, new5);
		gridPane.setConstraints(new1, 0, finalRow);
		gridPane.setConstraints(new2, 1, finalRow);
		gridPane.setConstraints(new3, 2, finalRow);
		gridPane.setConstraints(new4, 3, finalRow);
		gridPane.setConstraints(new5, 4, finalRow);

		finalRow += 1;

	}

	private void addRow2() {

		TextField new1 = new TextField();
		TextField new2 = new TextField();
		TextField new3 = new TextField();
		TextField new4 = new TextField();
		TextField new5 = new TextField();

		gridPane.getChildren().addAll(new1, new2, new3, new4, new5);
		gridPane.setConstraints(new1, 0, finalRow);
		gridPane.setConstraints(new2, 1, finalRow);
		gridPane.setConstraints(new3, 2, finalRow);
		gridPane.setConstraints(new4, 3, finalRow);
		gridPane.setConstraints(new5, 4, finalRow);

		finalRow += 1;

	}

	void updateTextFieldValue() {

		mfr.readFile();
		HashMap<String, ArrayList<String>> temp = new HashMap<>();
		TextField tf, tf2;

		ArrayList<String> tempArray = new ArrayList<>();
		temp = mfr.getQAndA();
		int i = 1;
		// add row to accomadate teh data

		for (String s : temp.keySet()) {
			tempArray.add(s);
			if (i >= 2) {
				addRow2();
			}
			tf = (TextField) getNodeFromGridPane(i, 0);
			tf.setText(s);
			i++;
		}

		finalRow = temp.size() + 1;

		// put text in answers

		for (int k = 1; k < finalRow; k++) {
			for (int j = 1; j < 5; j++) {
				tf = (TextField) getNodeFromGridPane(k, j);
				tf2 = (TextField) getNodeFromGridPane(k, 0);

				tf.setText(temp.get(tf2.getText()).get(j - 1));

			}
		}

		if (finalRow < 2) {
			finalRow = 2;
		}
	}

	private void saveRecord(ActionEvent event) {

		StringBuilder sb = new StringBuilder();

		// row 0 is label
		for (int i = 1; i < finalRow; i++) {
			for (int j = 0; j < 5; j++) {

				// possible from if empty field, will cause problem
				String temp = getStringFromGridPane(i, j);
				if (!temp.isEmpty()) {
					sb.append(temp);
					sb.append("`");
				}

			}
			sb.append("\n");
		}

		mfr.writeFile(sb.toString());

	}

	private String getStringFromGridPane(int row, int col) {

		TextField tf = null;
		try {
			tf = (TextField) getNodeFromGridPane(row, col);
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}
		if (tf != null) {
			return tf.getText();
		} else
			return "";
	}

	private Node getNodeFromGridPane(int row, int col) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}

		}
		return null;
	}

	private void redraw() {
		g.clearRect(0, 0, scene.getWidth(), scene.getHeight());
		g.drawImage(bg, canvas.getWidth() / 4, canvas.getHeight() / 4);
	}

	void clearRecord(ActionEvent event) {
		mfr.clearFile();
		TextField tf;

		for (int k = 1; k < finalRow; k++) {
			for (int j = 0; j < 5; j++) {
				try {
					tf = (TextField) getNodeFromGridPane(k, j);
					tf.setText("");

				} catch (Exception e) {
					// do nothing
				}

			}
		}

	}

	public Scene instructionShow() {
		backbtn.setOnAction(this::backPress);
		addbtn.setOnAction(this::addRow);
		savebtn.setOnAction(this::saveRecord);
		clearbtn.setOnAction(this::clearRecord);
		canvas.widthProperty().bind(scene.widthProperty());
		canvas.heightProperty().bind(scene.heightProperty());

		// redraw when resized
		canvas.widthProperty().addListener(observable -> redraw());
		canvas.heightProperty().addListener(observable -> redraw());
		return scene;
	}
}
