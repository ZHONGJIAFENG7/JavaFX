import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
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

public class StopWatch {

	private long startTimeMillis;
	private boolean running = false;
	private long totalTimeMillis;

	public void start() {
		this.startTimeMillis = System.currentTimeMillis();
		running = true;
	}

	public long UpdateTimer() {
		this.totalTimeMillis = System.currentTimeMillis() - startTimeMillis;
		return totalTimeMillis;
	}

	public void stop() {
		long lastTime = System.currentTimeMillis() - this.startTimeMillis;
		this.totalTimeMillis += lastTime;
		this.running = false;
	}

	public boolean isRunning() {
		return this.running;
	}
}
