package de.ruu.lib.gen.java.fx.comp;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;

public class GeneratorFXCViewServiceTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCViewService.class;
		GeneratorFXCViewService generator =
				new GeneratorFXCViewService(
						clazz.getPackageName() + ".demo",
						clazz.getSimpleName() + "Demo");
		generator.run();
	}
}