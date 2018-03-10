import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.InputStream;

public class Fruit {

	Circle fruit;
	private int fruitPosition;
	private double XPosition;
	private double YPosition;
	private double initialX;
	private double initialY;
	private double initialRadius = 15;
	boolean impacted = false;
	int fruitBit;
	private Label label;
	Image fruitImage;
	String fruitAnswer;
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 17);

	Fruit(int x, int y, Group G, int Position, int bitValue, Image fruitImage) {

		fruit = new Circle(x, y, 15);
		fruit.setFill(new ImagePattern(fruitImage));
		G.getChildren().add(fruit);
		XPosition = x;
		YPosition = y;
		initialX = x;
		initialY = y;
		fruitPosition = Position;
		fruitBit = bitValue;
	}

	Fruit(int x, int y, Group G, int i, Image fruitImage) {

		label = new Label();
		fruit = new Circle(x, y, 15);
		// fruit.setFill(Colours.get(i));
		fruit.setFill(new ImagePattern(fruitImage));
		this.fruitImage = fruitImage;

		G.getChildren().add(fruit);

		G.getChildren().add(label);
		XPosition = x;
		YPosition = y;
		initialX = x;
		initialY = y;
		fruitPosition = i;
		fruitAnswer = "asda";
	}

	void setLabel(String ans) {

		label.setText(ans);
		label.setFont(bgFont);
		if (fruitPosition == 0) {
			label.setTextFill(Color.BLUE);
		} else if (fruitPosition == 1) {
			label.setTextFill(Color.RED);
		} else if (fruitPosition == 2) {
			label.setTextFill(Color.GREEN);
		} else if (fruitPosition == 3) {
			label.setTextFill(Color.PURPLE);
		}
		label.setTranslateX(XPosition - 10);
		label.setTranslateY(YPosition + 20);
		label.toFront();

	}

	public void update(double x, double y) {
		if (!(YPosition > 650) && fruitPosition == 3) {
			XPosition = XPosition + x / 3;
			YPosition = YPosition + y;
		} else if (!(YPosition > 650) && fruitPosition == 0) {
			XPosition = XPosition - x / 3;
			YPosition = YPosition + y;
		} else if (!(YPosition > 650) && fruitPosition == 1) {
			XPosition = XPosition - x / 10;
			YPosition = YPosition + y;
		} else if (!(YPosition > 650) && fruitPosition == 2) {
			XPosition = XPosition + x / 10;
			YPosition = YPosition + y;
		} else {
			XPosition = initialX;
			YPosition = initialY;
			impacted = false;
			fruit.setRadius(initialRadius);
		}
		size(y);
	}

	public void draw() {
		fruit.setCenterX(XPosition);
		fruit.setCenterY(YPosition);
	}

	private void size(double radiusProportion) {
		fruit.setRadius(fruit.getRadius() + radiusProportion / 40);

	}

	public boolean impacts(Bounds b) {
		return fruit.intersects(b);
	}

	public void setFill(ImagePattern color) {
		fruit.setFill(color);
	}

	public double getY() {
		return YPosition;
	}

	public int getfruitBit() {
		return fruitBit;
	}

	public void setFruitBit(int i) {
		fruitBit = i;
	}

	public boolean getImpacted() {
		return impacted;
	}

	public void setImpacted() {
		impacted = true;
	}

	public void setFruitImage(Image fruit) {
		fruitImage = fruit;
	}

	public void setXstarting(int i) {
		initialX = i;
	}

	public void setFruitPosition(int i) {
		fruitPosition = i;
	}

	public void remove(Group g) {
		g.getChildren().remove(fruit);
	}

	public String getFruitAnswer() {
		return fruitAnswer;
	}

	public void addTo(Group G) {
		G.getChildren().add(fruit);
	}

	void setFruitAnswer(String ans) {
		fruitAnswer = ans;
	}
}
