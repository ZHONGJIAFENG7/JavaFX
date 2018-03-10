import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.*;
import javafx.scene.*;
import javafx.beans.property.*;
import javafx.scene.image.*;
import java.lang.Math.*;

public class Sun {

	ImageView[] suns = new ImageView[2];
	Group G = new Group();

	Sun(Group root) {
		suns[0] = new ImageView(new Image("pic/sun_happy.png"));
		suns[1] = new ImageView(new Image("pic/sun_unhappy.png"));
		G.getChildren().setAll(suns[0]);
		root.getChildren().add(G);
	}

	public void happy() {
		G.getChildren().setAll(suns[0]);
	}

	public void sad() {
		G.getChildren().setAll(suns[1]);
	}
}
