package de.ruu.lib.gen.java.fx.comp.runner;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.comp.GeneratorFXCompBundle;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
class ComponentHierarchyDemoGeneratorRunner
{
	public static void main(String[] args) throws IOException, GeneratorException
	{
		GeneratorFXCompBundle generator;
		String                packageNameBase = ComponentHierarchyDemoGeneratorRunner.class.getPackageName();

		log.debug("creating java fx component hierarchy bundles");
		generator = new GeneratorFXCompBundle(packageNameBase          , "HierarchyDemoMain");
		generator.run();
		generator = new GeneratorFXCompBundle(packageNameBase + ".sub1", "HierarchyDemoSub1");
		generator.run();
		generator = new GeneratorFXCompBundle(packageNameBase + ".sub2", "HierarchyDemoSub2");
		generator.run();
		log.debug("created  java fx component hierarchy bundles");
	}
}