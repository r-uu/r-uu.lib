package de.ruu.lib.fx.demo.gen;

import java.io.IOException;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import de.ruu.lib.fx.demo.gen.input.FXBeanModel;
import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.bean.FXBeanGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class RunnerRUUFXDemoBeanGenerator
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?>        fxBeanModelClass = FXBeanModel.class;
		FXBeanGenerator generator;

		log.debug("creating java fx bean for java fx bean model {}", fxBeanModelClass.getName());
		generator =
				new FXBeanGenerator
				(
						fxBeanModelClass.getPackageName(),
						"FXBean",
						new ClassFileImporter().importClass(fxBeanModelClass)
				);
		log.debug("created  java fx bean for java fx bean model {}", fxBeanModelClass.getName());

		generator.run();
	}
}