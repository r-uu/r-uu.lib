package de.ruu.lib.gen.java.fx.bean.editor.demo;

import de.ruu.lib.fx.comp.FXCAppRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * Java FX Component Application Runner {@link FXBeanEditorDemoAppRunner}
 * <p>
 * generated by {@link de.ruu.lib.gen.java.fx.comp.GeneratorFXCAppRunner} at 2024.05.17 07:44:09:963
 */
@Slf4j
public class FXBeanEditorDemoAppRunner extends FXCAppRunner
{
	public static void main(String[] args)
	{
		log.debug("starting FXBeanEditorDemoAppRunner.class.getName()");
		FXCAppRunner.run(FXBeanEditorDemoApp.class, args);
		log.debug("finished FXBeanEditorDemoAppRunner.class.getName()");
	}
}