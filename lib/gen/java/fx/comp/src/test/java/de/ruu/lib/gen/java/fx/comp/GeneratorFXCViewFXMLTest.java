package de.ruu.lib.gen.java.fx.comp;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;

public class GeneratorFXCViewFXMLTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCViewFXML.class;
		GeneratorFXCViewFXML generator =
				new GeneratorFXCViewFXML(
						clazz.getPackageName() + ".demo",
						clazz.getSimpleName() + "Demo");
		generator.run();
	}
}