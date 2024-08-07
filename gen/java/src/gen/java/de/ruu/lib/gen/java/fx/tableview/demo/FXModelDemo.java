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
import lombok.Builder;
import lombok.RequiredArgsConstructor;

/**
 * FXBean {@link FXModelDemo}
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.bean.FXBeanGenerator} at 2024.07.06 17:42:31:737
 */
@RequiredArgsConstructor
@Builder
public class FXModelDemo
{
	private ObjectProperty<BigDecimal> aBigDecimal;
	private BooleanProperty aBoolean;
	private StringProperty aString;
	private IntegerProperty anInteger;
	private ListProperty<String> stringList;
	
	public FXModelDemo(ObjectProperty<BigDecimal> aBigDecimal, BooleanProperty aBoolean, StringProperty aString, IntegerProperty anInteger, ListProperty<String> stringList)
	{
		this.aBigDecimal = aBigDecimal;
		this.aBoolean = aBoolean;
		this.aString = aString;
		this.anInteger = anInteger;
		this.stringList = stringList;
	}
	
	public FXModelDemo(JavaModelDemo javaModelDemo)
	{
		aBigDecimal = new SimpleObjectProperty<>(javaModelDemo.aBigDecimal());
		aBoolean = new SimpleBooleanProperty(javaModelDemo.aBoolean());
		aString = new SimpleStringProperty(javaModelDemo.aString());
		anInteger = new SimpleIntegerProperty(javaModelDemo.anInteger());
		stringList = new SimpleListProperty<>();
		stringList.addAll(javaModelDemo.stringList());
	}
	
	public ObjectProperty<BigDecimal> aBigDecimal()
	{
		return aBigDecimal;
	}
	
	public BooleanProperty aBoolean()
	{
		return aBoolean;
	}
	
	public StringProperty aString()
	{
		return aString;
	}
	
	public IntegerProperty anInteger()
	{
		return anInteger;
	}
	
	public ListProperty<String> stringList()
	{
		return stringList;
	}
}