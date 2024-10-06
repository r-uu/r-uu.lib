package de.ruu.lib.gen.java.fx.bean.demo;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * FXBean {@link FXBean}
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.bean.FXBeanGenerator} at 2024.10.05 09:22:54:701
 */
@AllArgsConstructor
@Builder
@Getter
public class FXBean
{
	private ObjectProperty<BigDecimal> aBigDecimal = new SimpleObjectProperty<BigDecimal>();
	private BooleanProperty aBoolean = new SimpleBooleanProperty();
	private StringProperty aString = new SimpleStringProperty();
	private IntegerProperty anInteger = new SimpleIntegerProperty();
	private ListProperty<String> stringList = new SimpleListProperty<String>();
	
	public FXBean(FXBeanGeneratorTest.FXModelDemo fXModelDemo)
	{
		aBigDecimal = new SimpleObjectProperty<>(fXModelDemo.aBigDecimal());
		aBoolean = new SimpleBooleanProperty(fXModelDemo.aBoolean());
		aString = new SimpleStringProperty(fXModelDemo.aString());
		anInteger = new SimpleIntegerProperty(fXModelDemo.anInteger());
		stringList = new SimpleListProperty<>();
		stringList.addAll(fXModelDemo.stringList());
	}
	
	public FXBeanGeneratorTest.FXModelDemo toSource()
	{
		return new FXBeanGeneratorTest.FXModelDemoDTO(aBigDecimal.getValue(), aBoolean.getValue(), aString.getValue(), anInteger.getValue(), stringList.getValue());
	}
}