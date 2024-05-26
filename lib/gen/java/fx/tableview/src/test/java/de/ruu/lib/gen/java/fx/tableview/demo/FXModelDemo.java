package de.ruu.lib.gen.java.fx.tableview.demo;

import de.ruu.lib.gen.java.fx.tableview.JavaModelDemo;
import java.math.BigDecimal;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * FXBean {@link FXModelDemo}
 * <p>
 * generated by {@link de.ruu.lib.gen.java.fx.bean.FXBeanGenerator} at 2023.12.30 15:14:36:534
 */
public class FXModelDemo
{
	private ObjectProperty<BigDecimal> aBigDecimal;
	private BooleanProperty aBoolean;
	private IntegerProperty anInteger;
	private ListProperty<String> stringList;
	private StringProperty aString;
	
	public FXModelDemo(ObjectProperty<BigDecimal> aBigDecimal, BooleanProperty aBoolean, IntegerProperty anInteger, ListProperty<String> stringList, StringProperty aString)
	{
		this.aBigDecimal = aBigDecimal;
		this.aBoolean = aBoolean;
		this.anInteger = anInteger;
		this.stringList = stringList;
		this.aString = aString;
	}
	
	public FXModelDemo(JavaModelDemo javaModelDemo)
	{
		aBigDecimal = new SimpleObjectProperty<>(javaModelDemo.aBigDecimal());
		aBoolean = new SimpleBooleanProperty(javaModelDemo.aBoolean());
		anInteger = new SimpleIntegerProperty(javaModelDemo.anInteger());
		stringList = new SimpleListProperty<>();
		stringList.addAll(javaModelDemo.stringList());
		aString = new SimpleStringProperty(javaModelDemo.aString());
	}
	
	public ObjectProperty<BigDecimal> aBigDecimal()
	{
		return aBigDecimal;
	}
	
	public BooleanProperty aBoolean()
	{
		return aBoolean;
	}
	
	public IntegerProperty anInteger()
	{
		return anInteger;
	}
	
	public ListProperty<String> stringList()
	{
		return stringList;
	}
	
	public StringProperty aString()
	{
		return aString;
	}
}