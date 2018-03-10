import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;

import java.util.Scanner;
import java.io.*;

public class LeaderBoard {

	Stage window;
	private TableView<TopScore> table;
	private TextField nameInput;
	private Button confirm;
	private Button back;
	private int sc = 99;
	Scanner scanner;
	Scene newScene, oldScene;
	double width, height;
	MMenu menu = new MMenu();
	ObservableList<TopScore> topScores = FXCollections.observableArrayList();
	boolean showConfirm;

	public LeaderBoard(Scene scene, Stage stage, double width, double height, boolean showConfirm) {
		window = stage;
		window.setTitle("Leaderboard");
		this.oldScene = scene;
		this.width = width;
		this.height = height;

		// Name column
		TableColumn<TopScore, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Price column
		TableColumn<TopScore, Double> scoreColumn = new TableColumn<>("Score");
		scoreColumn.setMinWidth(100);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

		nameInput = new TextField();
		nameInput.setPromptText("NEW HIGHSCORE, ENTER YOUR NAME!");
		nameInput.setMinWidth(120);

		confirm = new Button("Confirm");
		confirm.setTranslateX(110);
		confirm.setOnAction(e -> confirmFunc());

		back = new Button("Back");
		back.setTranslateX(140);
		back.setOnAction(e -> backFunc());
		readFile();

		table = new TableView<>();
		scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
		table.setItems(getTopScore());
		table.getColumns().add(nameColumn);
		table.getColumns().add(scoreColumn);
		table.getSortOrder().add(scoreColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		BorderPane bp = new BorderPane();
		bp.setCenter(vBox);

		HBox hBox = new HBox();
		if (showConfirm) {
			hBox.getChildren().addAll(confirm, back);
			bp.setTop(nameInput);
		} else {
			hBox.getChildren().add(back);
		}
		bp.setBottom(hBox);

		newScene = new Scene(bp, 800, 600);
	}

	// Get all of the TopScores

	public ObservableList<TopScore> getTopScore() {
		return topScores;
	}

	void confirmFunc() {

		TopScore newScore = new TopScore();
		newScore.setName(nameInput.getText());
		newScore.setScore(sc);
		table.getItems().add(newScore);
		writeFile(nameInput.getText() + "`" + sc);
		confirm.setOnAction(null);
	}

	void backFunc() {
		window.setTitle("Bug bits!");
		window.setScene(oldScene);
		menu.show(window);
	}

	public void writeFile(String string) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("leaderboard.txt", true))) {

			bw.write(string);
			bw.newLine();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	void readFile() {
		String temp, name;
		try {
			scanner = new Scanner(new FileReader("leaderboard.txt"));

			while (scanner.hasNext()) {
				temp = scanner.nextLine();
				topScores.add(putToTopScore(temp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	TopScore putToTopScore(String temp) {
		TopScore ts;
		String name = temp.substring(0, temp.indexOf("`"));
		int score = Integer.parseInt(temp.substring(temp.indexOf("`") + 1));
		ts = new TopScore(name, score);
		return ts;

	}

	void setSc(int sc1) {
		sc = sc1;
	}

	Scene leaderBoardShow() {
		return newScene;
	}

}
