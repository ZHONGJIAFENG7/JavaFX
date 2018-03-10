import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.*;
import java.util.EventListener.*;
import javafx.animation.*;
import javafx.beans.property.*;
import java.util.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import java.io.*;
import javafx.scene.text.Font;

public class Main {

	Group root = new Group();
	Group fruit = new Group();
	Group caterpillar = new Group();
	Group back = new Group();
	Scene scene = new Scene(root, 600, 700, Color.WHITE);
	Fruits allFruit;
	Fruits2 allFruit2;
	final LongProperty lastUpdateTime = new SimpleLongProperty();
	Player player;
	Background background;
	IntegerProperty score = new SimpleIntegerProperty(0);
	Label scoreboard = new Label();
	double time = 0;
	Label timer = new Label();
	StopWatch ST = new StopWatch();
	AnimationSetup Background1;
	IntegerProperty T = new SimpleIntegerProperty(0);
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 36);
	Label TIME = new Label("Time:");
	Stage tempStage, backStage;
	Scene backScene;

	Soundtrack S = new Soundtrack();

	Cows cowField;

	public Main(Stage stage, Scene scene) {
		backStage = stage;
		backScene = scene;
	}

	Scene setUp(Stage stage, int gameMode) {
		scoreboard.textProperty().bind(score.asString());
		scoreboard.setFont(bgFont);
		scoreboard.setTextFill(Color.YELLOW);
		if (gameMode == 1) {
			TIME.setFont(bgFont);
			TIME.setTextFill(Color.GREEN);
			timer.setFont(bgFont);
			timer.setTextFill(Color.GREEN);
			timer.textProperty().bind(T.asString());
			TIME.setTranslateX(100);
			timer.setTranslateX(200);
			root.getChildren().addAll(back, fruit, caterpillar);
			allFruit = new Fruits(4, fruit, S);
			root.getChildren().addAll(scoreboard, timer, TIME);
		}
		if (gameMode == 2) {
			allFruit2 = new Fruits2(4, fruit);
			root.getChildren().addAll(back, fruit, caterpillar);
			root.getChildren().add(scoreboard);
		}
		player = new Player(caterpillar, scene, fruit);

		Background1 = new AnimationSetup("finalImages/Main_Project_File_output", "png", 607, 1080, 30, back);
		// background = new Background(root);

		cowField = new Cows(root);
		stage.setScene(scene);

		return scene;
	}

	void show(Stage stage, int gameMode) {
		stage.show();
		tempStage = stage;

		S.backgroundMusic();

		if (gameMode == 1) {
			beginFruit.start();
		}
		if (gameMode == 2) {
			beginFruit2.start();
		}
	}

	void switchToLeaderBoard() {

		S.stopbackgroundMusic();

		Stage stage = new Stage();
		LeaderBoard lb = new LeaderBoard(backScene, backStage, 500, 700, true);
		int time = 100;
		int timeBonus = 100 - T.get();
		int tBonus = 0;
		if (timeBonus > 0) {
			tBonus = 10 * timeBonus;
		}
		lb.setSc(tBonus + score.get());
		tempStage.setScene(lb.leaderBoardShow());
		tempStage.show();

	}

	AnimationTimer beginFruit = new AnimationTimer() {
		public void handle(long now) {
			if (lastUpdateTime.get() > 0) {
				final double elapsedTime = (now - lastUpdateTime.get()) / 10000000;
				if (ST.isRunning())
					T.set((int) ST.UpdateTimer() / 1000);
				else
					ST.start();
				player.updateCaterpillarAnimation(elapsedTime, 1 / allFruit.getQuestionMomentum());
				Background1.updateAnimation(elapsedTime, 1 / allFruit.getQuestionMomentum());
				allFruit.updatePositions(elapsedTime, player, score);
				cowField.updateCow(elapsedTime);
				if (allFruit.isEnd()) {
					beginFruit.stop();
					switchToLeaderBoard();
				}
			}
			lastUpdateTime.set(now);
		}
	};

	AnimationTimer beginFruit2 = new AnimationTimer() {
		public void handle(long now) {
			if (lastUpdateTime.get() > 0) {
				final double elapsedTime = (now - lastUpdateTime.get()) / 10000000;
				player.updateCaterpillarAnimation(elapsedTime, 1);
				Background1.updateAnimation(elapsedTime, 1);
				boolean check = allFruit2.updatePositions(elapsedTime, player, score);
				cowField.updateCow(elapsedTime);
				if (check == false) {
					beginFruit2.stop();
					switchToLeaderBoard();
				}
			}
			lastUpdateTime.set(now);
		}

	};

}
