package de.ruu.lib.fx.demo.bean;

import java.math.BigDecimal;

import de.ruu.lib.fx.demo.gen.input.FXBeanModel;
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
 * FXBean {@link FXBean}
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.bean.FXBeanGenerator} at 2024.05.20 14:26:06:065
 */
@RequiredArgsConstructor
@Builder
public class FXBean
{
	private ObjectProperty<BigDecimal> aBigDecimal;
	private BooleanProperty aBoolean;
	private StringProperty aString;
	private IntegerProperty anInteger;
	private ListProperty<String> stringList;
	
	public FXBean(ObjectProperty<BigDecimal> aBigDecimal, BooleanProperty aBoolean, StringProperty aString, IntegerProperty anInteger, ListProperty<String> stringList)
	{
		this.aBigDecimal = aBigDecimal;
		this.aBoolean = aBoolean;
		this.aString = aString;
		this.anInteger = anInteger;
		this.stringList = stringList;
	}
	
	public FXBean(FXBeanModel fXBeanModel)
	{
		aBigDecimal = new SimpleObjectProperty<>(fXBeanModel.aBigDecimal());
		aBoolean = new SimpleBooleanProperty(fXBeanModel.aBoolean());
		aString = new SimpleStringProperty(fXBeanModel.aString());
		anInteger = new SimpleIntegerProperty(fXBeanModel.anInteger());
		stringList = new SimpleListProperty<>();
		stringList.addAll(fXBeanModel.stringList());
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