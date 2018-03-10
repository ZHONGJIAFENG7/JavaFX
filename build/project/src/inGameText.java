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
import javafx.util.*;

public class inGameText {

	Group root;
	Text text;
	String currentFontFile = "CabinSketch-Bold.ttf";
	InputStream fontStream = Main.class.getResourceAsStream(currentFontFile);
	Font bgFont = Font.loadFont(fontStream, 45);

	inGameText(Group g) {
		root = g;
		text = new Text("Yum");
		g.getChildren().add(text);
		text.setFill(Color.ORANGE);
		text.setX(265);
		text.setY(230);
		text.setFont(bgFont);
		text.setFontSmoothingType(FontSmoothingType.LCD);

		FadeTransition ft = new FadeTransition(Duration.millis(3000), text);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
	}
}
