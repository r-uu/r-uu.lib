package de.ruu.lib.fx.comp.demo.hierarchy;

import de.ruu.lib.fx.comp.FXCAppRunner;
import de.ruu.lib.fx.comp.FXCAppStartedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Java FX Component Application Runner {@link HierarchyDemoMainAppRunner}
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.comp.GeneratorFXCAppRunner} at 2024.05.17 12:57:18:203
 */
@Slf4j
public class HierarchyDemoMainAppRunner extends FXCAppRunner
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		log.debug("starting HierarchyDemoMainAppRunner.class.getName()");

		// force class to be loaded (and it's static initialiser to be executed)
		// add-reads module of FXCAppStartedEvent to ALL-UNNAMED
		Class<?> clazz = Class.forName(FXCAppStartedEvent.class.getName());
		log.debug("loaded {}", clazz.getName());

		FXCAppRunner.run(HierarchyDemoMainApp.class, args);
		log.debug("finished HierarchyDemoMainAppRunner.class.getName()");
	}
}