package de.ruu.lib.fx.demo.gantt.gantt_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GanttChartSample extends Application {

	private static final int ROW_HEIGHT = 30;
	private static final int COLUMN_WIDTH = 100;
	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 600;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Gantt Chart Example");

		Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		drawGanttChart(gc);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(canvas);

		Pane root = new Pane();
		root.getChildren().add(scrollPane);

		Scene scene = new Scene(root, 900, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void drawGanttChart(GraphicsContext gc) {
		// Draw the background grid
		gc.setStroke(Color.LIGHTGRAY);
		for (int i = 0; i < CANVAS_HEIGHT / ROW_HEIGHT; i++) {
			gc.strokeLine(0, i * ROW_HEIGHT, CANVAS_WIDTH, i * ROW_HEIGHT);
		}
		for (int i = 0; i < CANVAS_WIDTH / COLUMN_WIDTH; i++) {
			gc.strokeLine(i * COLUMN_WIDTH, 0, i * COLUMN_WIDTH, CANVAS_HEIGHT);
		}

		// Draw tasks (example data)
		drawTask(gc, "Task 1", 1, 1, 3);
		drawTask(gc, "Task 2", 2, 4, 2);
		drawTask(gc, "Task 3", 3, 2, 5);
		drawTask(gc, "Task 4", 4, 6, 3);
	}

	private void drawTask(GraphicsContext gc, String taskName, int row, int start, int duration) {
		int x = start * COLUMN_WIDTH;
		int y = (row - 1) * ROW_HEIGHT;
		int width = duration * COLUMN_WIDTH;
		int height = ROW_HEIGHT;

		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(x, y, width, height);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(x, y, width, height);
		gc.setFill(Color.BLACK);
		gc.fillText(taskName, x + 5, y + height / 2 + 5);
	}

	public static void main(String[] args) {
		launch(args);
	}
}