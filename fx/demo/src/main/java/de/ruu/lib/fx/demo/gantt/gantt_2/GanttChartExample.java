package de.ruu.lib.fx.demo.gantt.gantt_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GanttChartExample extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Gantt Chart Example");

		// Define the x and y axes
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Tasks");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Time");

		// Create the bar chart with stacked bars
		StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
		barChart.setTitle("Project Schedule");

		// Create series and add data
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Task 1");
		series1.getData().add(new XYChart.Data<>("Task 1", 2));
		series1.getData().add(new XYChart.Data<>("Task 2", 5));

		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Task 2");
		series2.getData().add(new XYChart.Data<>("Task 2", 3));
		series2.getData().add(new XYChart.Data<>("Task 3", 7));

		barChart.getData().addAll(series1, series2);

		// Create the scene and add it to the stage
		Scene scene = new Scene(barChart, 800, 600);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}