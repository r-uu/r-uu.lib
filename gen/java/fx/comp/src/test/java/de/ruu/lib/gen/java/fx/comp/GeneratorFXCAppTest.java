package de.ruu.lib.gen.java.fx.comp;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;

public class GeneratorFXCAppTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCApp.class;
		GeneratorFXCApp generator =
				new GeneratorFXCApp(
						clazz.getPackageName() + ".demo",
						clazz.getSimpleName() + "Demo");
		generator.run();
	}
}