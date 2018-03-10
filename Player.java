import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.animation.*;
import javafx.util.*;

public class Player {

	ImageView player;
	AnimationSetup animationSetup;
	final int caterPillarY = 590;

	double[] channel = { 161, 256, 338, 432 };

	int currentChannel = 2;
	double XPosition = channel[currentChannel];

	Group collisionFruit;

	Player(Group g, Scene scene, Group fruit) {

		animationSetup = new AnimationSetup("pic/caterpillar", "png", 60, 100, 24, g);
		changePlayerPosition(338 - 20, caterPillarY);
		scene.setOnKeyPressed(this::leftright);
		collisionFruit = fruit;
	}

	public void leftright(KeyEvent e) {
		if (e.getCode() == KeyCode.LEFT && currentChannel > 0) {
			currentChannel -= 1;
			changePlayerPosition(channel[currentChannel] - 20, caterPillarY);
			XPosition = animationSetup.getX();
		} else if (e.getCode() == KeyCode.RIGHT && currentChannel < 3) {
			currentChannel += 1;
			changePlayerPosition(channel[currentChannel] - 20, caterPillarY);
			XPosition = animationSetup.getX();
		}
	}

	public void updateCaterpillarAnimation(double elapsedTime, double speed) {
		animationSetup.updateAnimation(elapsedTime, speed);
	}

	public Bounds getBounds() {
		return animationSetup.getBounds();
	}

	void changePlayerPosition(double x, double y) {
		int angle;
		if (currentChannel == 0)
			angle = 20;
		else if (currentChannel == 3)
			angle = -20;
		else if (currentChannel == 1)
			angle = 5;
		else
			angle = -5;
		animationSetup.changeCaterpillarChannel(x, y, angle);
	}

}
