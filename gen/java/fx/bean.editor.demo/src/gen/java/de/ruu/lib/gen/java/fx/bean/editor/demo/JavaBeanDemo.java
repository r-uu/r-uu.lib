package de.ruu.lib.gen.java.fx.bean.editor.demo;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * JavaBean {@link JavaBeanDemo}
 * <p>
 * generated by {@link de.ruu.lib.gen.java.bean.BeanGenerator} at 2024.05.18 11:27:02:765
 */
@AllArgsConstructor
@Builder(toBuilder = true)
public class JavaBeanDemo implements de.ruu.lib.gen.java.fx.bean.editor.demo.GeneratorRunner$JavaModelDemo
{
	private boolean aBoolean;
	private BigDecimal aBigDecimal;
	private int anInteger;
	private String aString;
	private List<String> stringList;
	
	public BigDecimal aBigDecimal()
	{
		return aBigDecimal;
	}
	
	public boolean aBoolean()
	{
		return aBoolean;
	}
	
	public String aString()
	{
		return aString;
	}
	
	public int anInteger()
	{
		return anInteger;
	}
	
	public List<String> stringList()
	{
		return stringList;
	}
}