package de.ruu.lib.gen.java.fx.tableview;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.bean.FXBeanGenerator;
import de.ruu.lib.gen.java.fx.tableview.demo.FXModelDemo;
import de.ruu.lib.util.Files;

class FXTableViewConfiguratorGeneratorTest
{
	@BeforeAll
	static void beforeAll() throws GeneratorException, IOException
	{
		String packageName    = "de.ruu.lib.gen.java.fx.tableview.demo";
		String simpleFileName = FXModelDemo.class.getSimpleName();

		FXBeanGenerator generator =
				new FXBeanGenerator
				(
						"de.ruu.lib.gen.java.fx.tableview.demo",
						"FXModelDemo",
						new ClassFileImporter().importClass(JavaModelDemo.class)
				);

		generator.run();
		
		Path path =
				Path.of(
						"./src/gen/resources",
						Files.toDirectoryName(packageName),
						simpleFileName + ".fxml");
		Files.writeToFile("", path);
	}

	@Test void test() throws GeneratorException, IOException
	{
		FXTableViewConfiguratorGenerator generator =
				new FXTableViewConfiguratorGenerator
				(
						"de.ruu.lib.gen.java.fx.tableview.demo",
						"FXTableViewConfigurator",
						new ClassFileImporter().importClass(FXModelDemo.class)
				);

		generator.run();
	}
}