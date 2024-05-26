package de.ruu.lib.gen.java.fx.comp.runner;

import java.io.IOException;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.comp.GeneratorFXCompBundle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComponentDemoHierarchyGeneratorRunner
{
	public static void main(String[] args) throws IOException, GeneratorException
	{
		GeneratorFXCompBundle generator;
		String                packageName     = ComponentDemoHierarchyGeneratorRunner.class.getPackageName();
		String                packageNameBase = packageName.substring(0, packageName.lastIndexOf('.')) + ".demo.hierarchy";

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