package de.ruu.lib.fx.demo.gen;

import java.io.IOException;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import de.ruu.lib.fx.demo.bean.FXBean;
import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.tableview.FXTableViewConfiguratorGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class RunnerRUUFXDemoTableViewConfiguratorGenerator
{
	public static void main(String[] args) throws GeneratorException, IOException
	{
		Class<?>                         fxBeanModelClass = FXBean.class;
		FXTableViewConfiguratorGenerator generator;

		log.debug("creating java fx table view configurator for java fx bean {}", fxBeanModelClass.getName());
		generator =
				new FXTableViewConfiguratorGenerator
				(
						fxBeanModelClass.getPackageName(),
						"FXTableViewConfigurator",
						new ClassFileImporter().importClass(fxBeanModelClass)
				);
		log.debug("created  java fx table view configurator for java fx bean {}", fxBeanModelClass.getName());

		generator.run();
	}
}