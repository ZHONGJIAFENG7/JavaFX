
/*This class acts as a repository of sounds that are called upon during the game.*/

import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.*;
import java.util.*;
import javafx.beans.property.*;
import java.io.*;

public class Soundtrack {

	private static File file = new File("music/FinalFantasy.mp3");
	private static final String source = file.toURI().toString();

	Media soundTrack = new Media(source);
	MediaPlayer player = new MediaPlayer(soundTrack);
	MediaView mediaView = new MediaView(player);

	private static File file1 = new File("music/burp.mp3");
	private static final String source1 = file1.toURI().toString();

	Media burp = new Media(source1);
	MediaPlayer burpplayer = new MediaPlayer(burp);
	MediaView burpmediaView = new MediaView(burpplayer);

	private static File file2 = new File("music/apple_eat.mp3");
	private static final String source2 = file2.toURI().toString();

	Media apple = new Media(source2);
	MediaPlayer appleplayer = new MediaPlayer(apple);
	MediaView applemediaView = new MediaView(appleplayer);
	
	public Soundtrack() {
		// TODO Auto-generated constructor stub
		System.out.println(source);
	}

	public void backgroundMusic() {
		player.play();
	}

	public void stopbackgroundMusic() {
		player.stop();
	}

	public void stop() {
		player.stop();
	}

	public void burp() {
		burpplayer.play();
		burpplayer.seek(burpplayer.getStartTime());
	}

	public void apple() {
		appleplayer.play();
		appleplayer.seek(appleplayer.getStartTime());
	}

}
