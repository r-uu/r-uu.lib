package de.ruu.lib.gen.java.fx.tableview.demo;

import java.math.BigDecimal;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXTableViewConfigurator {@link FXTableViewConfigurator}
 * <p>
 * generated by {@link de.ruu.lib.gen.java.fx.tableview.FXTableViewConfiguratorGenerator} at 2024.10.05 09:25:38:322
 */
public abstract class FXTableViewConfigurator
{
	public static void configure(TableView<FXModelDemo> tableView)
	{
		tableView.getColumns().add(createColumnAnInteger());
		tableView.getColumns().add(createColumnABoolean());
		tableView.getColumns().add(createColumnABigDecimal());
		tableView.getColumns().add(createColumnAString());
	}
	
	private static TableColumn<FXModelDemo, Number> createColumnAnInteger()
	{
		TableColumn<FXModelDemo, Number> result = new TableColumn<>("anInteger");
		result.setCellValueFactory(data -> data.getValue().anInteger());
		result.setCellFactory(
		tableColumn -> 
		{
		  TextFieldTableCell<FXModelDemo, Number> cell = new TextFieldTableCell<>();
		  cell.setAlignment(Pos.CENTER_RIGHT);
		  return cell;
		});
		return result;
	}
	
	private static TableColumn<FXModelDemo, Boolean> createColumnABoolean()
	{
		TableColumn<FXModelDemo, Boolean> result = new TableColumn<>("aBoolean");
		result.setCellValueFactory(data -> data.getValue().aBoolean());
		result.setCellFactory(tableColumn -> new CheckBoxTableCell<>());
		return result;
	}
	
	private static TableColumn<FXModelDemo, BigDecimal> createColumnABigDecimal()
	{
		TableColumn<FXModelDemo, BigDecimal> result = new TableColumn<>("aBigDecimal");
		result.setCellValueFactory(data -> data.getValue().aBigDecimal());
		result.setCellFactory(
		tableColumn -> 
		{
		  TextFieldTableCell<FXModelDemo, BigDecimal> cell = new TextFieldTableCell<>();
		  cell.setAlignment(Pos.CENTER_RIGHT);
		  return cell;
		});
		return result;
	}
	
	private static TableColumn<FXModelDemo, String> createColumnAString()
	{
		TableColumn<FXModelDemo, String> result = new TableColumn<>("aString");
		result.setCellValueFactory(data -> data.getValue().aString());
		result.setCellFactory(TextFieldTableCell.forTableColumn());
		return result;
	}
}