import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.*;
import javafx.scene.*;
import javafx.beans.property.*;
import javafx.scene.image.*;

public class Fruits2 {

	List<Fruit> fruitArray = new ArrayList<>();
	List<Fruit> fruitSaLaD = new ArrayList<>();
	ArrayList<String> endGame = new ArrayList<>();
	private int numberFruits;
	private int initialNumberFruit;
	private double fruitVelocity = 1;
	Group allfruit;
	// ArrayList<Color> Colours = new ArrayList<Color>();
	ArrayList<Image> FruitImages = new ArrayList<Image>();
	Questions2 questions;
	inGameText correct;
	Group root;

	String nextAns = "hold";

	Fruits2(int numberFruit, Group G) {
		/*
		 * Colours.add(Color.BLUE); Colours.add(Color.GREEN);
		 * Colours.add(Color.RED); Colours.add(Color.YELLOW);
		 */
		questions = new Questions2(G);
		FruitImages.add(new Image("Images/apple.png"));
		FruitImages.add(new Image("Images/pineapple.png"));
		FruitImages.add(new Image("Images/banana.png"));
		FruitImages.add(new Image("Images/pear.png"));
		for (int i = 0; i < numberFruit; i++) {
			Fruit newFruit = new Fruit(290 + i * 5, 267, G, i, FruitImages.get(i));
			fruitArray.add(newFruit);
			fruitSaLaD.add(newFruit);
		}
		numberFruits = numberFruit;
		initialNumberFruit = numberFruit;
		root = G;
		endGame.add("Thanks");
		endGame.add("For");
		endGame.add("Playing");
		endGame.add("!!!");

		allfruit = G;

		resetSalad();
	}

	private void resetSalad() {
		for (int i = numberFruits - 1; i >= 0; i--) {
			Fruit currentFruit = fruitArray.get(i);
			currentFruit.remove(allfruit);
		}
		fruitArray = fruitSaLaD;
		shuffleSalad(fruitArray);
		// Collections.shuffle(fruitArray);
		// fruitArray.();
		numberFruits = initialNumberFruit;
		for (int i = 0; i < numberFruits; i++) {
			Fruit currentFruit = fruitArray.get(i);
			// currentFruit.setFruitPosition(i);
			// currentFruit.setFill();
			currentFruit.addTo(allfruit);
		}
	}

	private void shuffleSalad(List<Fruit> fruit) {

		// position should be different, id stays the same
		ArrayList<Integer> temp = new ArrayList<>();
		ArrayList<Integer> temp2 = new ArrayList<>();

		for (int i = 0; i < numberFruits; i++) {
			Fruit currentFruit = fruit.get(i);

			int newPosition = setRandNum(temp);
			int newImage = setRandNum(temp2);

			currentFruit.setFruitPosition(newPosition);
			currentFruit.setXstarting(newPosition * i + 290);

			currentFruit.setFill(new ImagePattern(FruitImages.get(newImage)));

			// setting answers to current fruit using current array
			try {
				currentFruit.setFruitAnswer(questions.getNewFruitAnswers().get(i));
			} catch (Exception e) {
				currentFruit.setFruitAnswer(endGame.get(i));
			}
		}

	}

	int setRandNum(ArrayList<Integer> temp) {
		Random rand = new Random();
		int x = rand.nextInt(4);
		while (temp.contains(x)) {
			x = rand.nextInt(4);
		}
		temp.add(x);
		return x;
	}

	// didn't go into this function
	boolean updatePositions(double timeElapsed, Player player, IntegerProperty score) {
		final double fruitMoved = timeElapsed * fruitVelocity;
		for (int i = 0; i < numberFruits; i++) {
			Fruit currentFruit = fruitArray.get(i);
			currentFruit.update(fruitMoved, fruitMoved);
			currentFruit.setLabel(currentFruit.getFruitAnswer());
		}
		checkCollisions(player, score);
		draw();

		if (nextAns.isEmpty()) {

			return false;
		} else
			return true;
	}

	void draw() {
		for (int i = 0; i < numberFruits; i++) {
			Fruit currentFruit = fruitArray.get(i);
			currentFruit.draw();
		}
	}

	void checkCollisions(Player player, IntegerProperty score) {

		for (int i = 0; i < numberFruits; i++) {
			Fruit currentFruit = fruitArray.get(i);

			if (currentFruit.impacts(player.getBounds()) && currentFruit.getY() == 640) {

				currentFruit.remove(allfruit);
				// set currentFruit.fruitAnswer according to question
				nextAns = questions.getNextAnswer();

				if (currentFruit.getFruitAnswer().equals(nextAns)) {
					correct = new inGameText(root);
					score.set(score.get() + 10);
				} else {
					score.set(score.get());
				}
				questions.setNewQuestion();
				resetSalad();

			}
		}
	}
}
