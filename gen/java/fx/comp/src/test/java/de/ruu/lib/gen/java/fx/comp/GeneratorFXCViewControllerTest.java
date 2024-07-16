package de.ruu.lib.gen.java.fx.comp;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;

public class GeneratorFXCViewControllerTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCViewController.class;
		GeneratorFXCViewController generator =
				new GeneratorFXCViewController(
						clazz.getPackageName() + ".demo",
						clazz.getSimpleName() + "Demo");
		generator.run();
	}
}