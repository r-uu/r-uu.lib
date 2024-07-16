package de.ruu.lib.gen.java.fx.comp.runner;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.comp.GeneratorFXCompBundle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComponentDemoEmptyGeneratorRunner
{
	public static void main(String[] args) throws IOException, GeneratorException
	{
		GeneratorFXCompBundle generator;
		String                packageName     = ComponentDemoEmptyGeneratorRunner.class.getPackageName();
		String                packageNameBase = packageName.substring(0, packageName.lastIndexOf('.')) + ".demo.empty";

		log.debug("creating java fx component demo empty bundles");
		generator = new GeneratorFXCompBundle(packageNameBase, "Empty");
		generator.run();
		log.debug("created  java fx component demo empty bundles");
	}
}