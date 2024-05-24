package de.ruu.lib.gen.java.fx.comp;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;

public class GeneratorFXCViewFXCViewTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCView.class;
		GeneratorFXCView generator =
				new GeneratorFXCView(
						clazz.getPackageName() + ".demo",
						clazz.getSimpleName() + "Demo");
		generator.run();
	}
}