package de.ruu.lib.fx.control.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class AlertDialog
{
	public static void showAndWait(String content)
	{
		showAndWait(content, AlertType.INFORMATION);
	}

	public static void showAndWait(String content, AlertType type)
	{
		Alert alert = new Alert(type);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void showAndWait(String content, String title)
	{
		showAndWait(content, title, AlertType.INFORMATION);
	}

	public static void showAndWait(String content, String title, AlertType type)
	{
		Alert alert = new Alert(type);
		alert.setContentText(content);
		alert.setTitle(title);
		alert.showAndWait();
	}

	public static void showAndWait(String content, String title, String header, AlertType type)
	{
		Alert alert = new Alert(type);
		alert.setContentText(content);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.showAndWait();
	}
}