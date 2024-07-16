package de.ruu.lib.gen.java.fx.comp;

import de.ruu.lib.gen.GeneratorException;

import java.io.IOException;

public class GeneratorFXCAppRunnerTest
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?> clazz = GeneratorFXCAppRunner.class;
		GeneratorFXCAppRunner generator =
				new GeneratorFXCAppRunner(
						clazz.getPackageName() + "demo",
						clazz.getSimpleName () + "Demo",
						clazz.getPackageName() + ".demo.GeneratorFXCApp2Demo");
		generator.run();
	}
}