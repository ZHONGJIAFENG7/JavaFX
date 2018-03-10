import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.*;
import javafx.scene.*;
import javafx.beans.property.*;
import javafx.scene.image.*;
import java.lang.Math.*;

public class Cows {

	ImageView cows;
	ImageView bird[] = new ImageView[81];
	int cowSpeed = 1;
	int birdSpeed = 1;
	Random rand = new Random();
	Group Birdy = new Group();
	int j = 1;
	double time = 0;

	Cows(Group root) {
		cows = new ImageView(new Image("Images/cow.png"));
		cows.setX(600);
		cows.setY(200);
		root.getChildren().add(cows);
		for (int i = 0; i < 81; i++) {
			bird[i] = new ImageView(new Image("Images/bird00" + i + ".png"));
			bird[i].setX(-100);
			bird[i].setY(200);
			bird[i].setFitWidth(100);
			bird[i].setFitHeight(90);

		}
		Birdy.getChildren().setAll(bird[0]);
		root.getChildren().addAll(Birdy);
	}

	public void updateCow(double elapsedTime) {
		double cowMoved = elapsedTime * cowSpeed;
		cows.setX(cows.getX() - cowMoved);
		if (cows.getX() > 150 && cows.getX() < 250) {
			cows.setY(cows.getY() + 1);
			cows.setRotate(cows.getRotate() + 5);
		}
		if (cows.getX() > 250 && cows.getX() < 350) {
			cows.setY(cows.getY() - 1);
			cows.setRotate(cows.getRotate() - 5);
		}
		if (cows.getX() < rand.nextInt(600) - 1400)
			cows.setX(600);
		double birdMoved = elapsedTime * birdSpeed;
		for (int i = 0; i < 81; i++) {
			bird[i].setX(bird[0].getX() + birdMoved);
			bird[i].setY(20 * Math.sin(bird[0].getX() * 0.1) + 50);
			if (bird[0].getX() > rand.nextInt(600) + 1200)
				bird[i].setX(-100);
		}
		time = time + elapsedTime;
		if (time > 1) {
			Birdy.getChildren().setAll(bird[j]);
			j++;
			if (j > 80)
				j = 0;
			time = 0;
		}
	}

}
