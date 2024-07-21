package de.ruu.lib.fx.demo.comp.main;

import de.ruu.lib.fx.comp.FXCAppRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * Java FX Component Application Runner {@link RUUFXDemoComponentMainAppRunner}
 * <p>
 * generated by {@code de.ruu.lib.gen.java.fx.comp.GeneratorFXCAppRunner} at 2024.05.20 14:28:47:962
 */
@Slf4j
public class RUUFXDemoComponentMainAppRunner extends FXCAppRunner
{
	public static void main(String[] args)
	{
		log.debug("starting RUUFXDemoComponentMainAppRunner.class.getName()");
		FXCAppRunner.run(RUUFXDemoComponentMainApp.class, args);
		log.debug("finished RUUFXDemoComponentMainAppRunner.class.getName()");
	}
}