package de.ruu.lib.gen.java.bean.demo;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * JavaBean {@link JavaBeanDemo}
 * <p>
 * generated by {@link de.ruu.lib.gen.java.bean.BeanGenerator} at 2024.10.05 09:24:16:105
 */
@AllArgsConstructor
@Builder(toBuilder = true)
public class JavaBeanDemo implements de.ruu.lib.gen.java.bean.demo.BeanGeneratorTest$JavaModelDemo
{
	private List<String> stringList;
	private boolean aBoolean;
	private String aString;
	private int anInteger;
	private BigDecimal aBigDecimal;
	
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