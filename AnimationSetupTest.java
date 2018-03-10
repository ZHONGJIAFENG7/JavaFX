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
import javafx.event.*;
import javafx.util.*;

public class AnimationSetupTest extends Application {
	final String source = "pic/frame";
	final String extension = "png";
	final int width = 75;
	final int height = 125;
	final LongProperty lastUpdateTime = new SimpleLongProperty();
	ImageView[] animation = new ImageView[24];
	double time = 0;
	Group caterpillar;
	int j = 1;
	Group root = new Group();

	public void start(Stage stage) {
		// Group root = new Group();
		Scene scene = new Scene(root, 500, 500, Color.WHITE);
		// ImageView[] animation = new ImageView[24];
		for (int j = 0; j < 24; j++) {

			Image image = new Image(source + j + "." + extension);

			animation[j] = new ImageView(image);
			animation[j].setFitWidth(width);
			animation[j].setFitHeight(height);

		}
		for (int i = 0; i < 24; i++) {
			animation[i].setX(200);
			animation[i].setY(200);

		}
		root.getChildren().add(animation[0]);

		stage.setScene(scene);
		stage.show();
		beginFruit.start();
	}

	AnimationTimer beginFruit = new AnimationTimer() {
		public void handle(long now) {
			// System.out.println(now/1000000000);
			if (lastUpdateTime.get() > 0) {
				final double elapsedTime = (now - lastUpdateTime.get()) / 10000000;
				updateCaterpillar(elapsedTime);
			}
			lastUpdateTime.set(now);
		}
	};

	private void updateCaterpillar(double elapsedTime) {
		time = time + elapsedTime;
		System.out.println(time);
		if (time > 1) {
			root.getChildren().setAll(animation[j]);
			if (j < 23)
				j++;
			else
				j = 0;
			time = 0;
		}
	}

	/*
	 * Timeline tl = new Timeline( new KeyFrame(Duration.millis(10000), new
	 * EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[0]); System.out.println("here0"); }
	 * }), new KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[1]); System.out.println("here1"); }
	 * }), new KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[2]);
	 * System.out.println("here22222");
	 * 
	 * } }), new KeyFrame(Duration.millis(10000), new
	 * EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[3]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[4]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[5]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[6]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[7]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[8]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[9]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[10]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[11]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[12]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[13]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[14]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[15]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[16]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[17]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[18]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[19]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[20]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[21]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[22]); } }), new
	 * KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>(){
	 * 
	 * @Override public void handle(ActionEvent t) {
	 * //caterpillar.getChildren().remove(0);
	 * caterpillar.getChildren().add(slides[23]); } }) );
	 * tl.setCycleCount(tl.INDEFINITE); tl.play();
	 */
	/*
	 * FadeTransition show = new FadeTransition(Duration.millis(20), image);
	 * FadeTransition show2 = new FadeTransition(Duration.millis(1),
	 * slides[i+1]);
	 * 
	 * 
	 * FadeTransition gone = new FadeTransition(Duration.millis(1), image);
	 */

	/*
	 * if (position == 0) { show.setFromValue(1); show.setToValue(0);
	 * 
	 * } else { show.setFromValue(0); show.setToValue(1);
	 * 
	 * show2.setFromValue(0); show2.setToValue(1); gone.setFromValue(1);
	 * gone.setToValue(0); }
	 * 
	 * image.setOpacity(1); root.getChildren().add(image);
	 * 
	 * if (position == 0) { image.toBack(); } else { image.toFront();
	 * 
	 * } seq.getChildren().addAll(show, show2, gone);
	 */

	/*
	 * i++;
	 * 
	 * }
	 */

	/*
	 * seq.setCycleCount(seq.INDEFINITE); seq.play();
	 */

}
