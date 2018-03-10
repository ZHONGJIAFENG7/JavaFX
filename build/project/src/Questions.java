import java.util.*;
import java.util.Random;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.beans.property.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.*;
import javafx.scene.text.Font;

public class Questions {
	private int[] answers = new int[30];
	Random rand = new Random();
	private int currentAnswer = 0;
	Label currentFruit = new Label();
	Map<Integer, String> myMap = new HashMap<Integer, String>();
	Group root;
	inGameText Correct;
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 50);

	boolean end = false;

	Questions(Group G) {

		root = G;
		rand = new Random();
		for (int i = 0; i < 10; i++) {
			int nextRandom;
			if (i == 0) {
				do {
					nextRandom = rand.nextInt(15);
				} while (nextRandom == 0);
			} else {
				do {
					nextRandom = rand.nextInt(15);
				} while (nextRandom == answers[i - 1]);
			}
			answers[i] = nextRandom;
			System.out.println(answers[i]);
		}
		currentFruit.setFont(bgFont);
		currentFruit.setTextFill(Color.RED);
		currentFruit.setTranslateX(290);
		currentFruit.setText("Target: " + Integer.toString(answers[0]));
		G.getChildren().add(currentFruit);
	}

	public int getcurrentAnswer() {
		return answers[currentAnswer];
	}

	public int getNextAnswer() {
		currentAnswer += 1;
		if (currentAnswer < 9) {
			Correct = new inGameText(root);

			currentFruit.setText("Target: " + Integer.toString(answers[currentAnswer]));
			return answers[currentAnswer];
		} else {
			System.out.println("Level complete");
			end = true;
			return -1;
		}
	}

	boolean isEnd() {
		return end;
	}

	public int getTargetBit(int i) {
		int[] bits = new int[4];
		int target = answers[currentAnswer];
		if (target - 8 >= 0) {
			bits[0] = 1;
			target = target - 8;
		} else
			bits[0] = 0;

		if (target - 4 >= 0) {
			bits[1] = 1;
			target = target - 4;
		} else
			bits[1] = 0;

		if (target - 2 >= 0) {
			bits[2] = 1;
			target = target - 2;
		} else
			bits[2] = 0;

		if (target - 1 >= 0) {
			bits[3] = 1;
			target = target - 1;
		} else
			bits[3] = 0;

		return bits[i];
	}
}
