package de.ruu.lib.gen.java.fx.bean.demo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.fx.bean.FXBeanGenerator;
import de.ruu.lib.gen.java.fx.bean.FXProperty;

class FXBeanGeneratorTest
{
	interface FXModelDemo
	{
		@FXProperty boolean      aBoolean();
		@FXProperty int          anInteger();
		@FXProperty String       aString();
		@FXProperty BigDecimal   aBigDecimal();
		@FXProperty List<String> stringList();
	}

	@Test void test() throws GeneratorException, IOException
	{
		FXBeanGenerator generator =
				new FXBeanGenerator
				(
						FXModelDemo.class.getPackageName(),
						"FXBean",
						new ClassFileImporter().importClass(FXModelDemo.class)
				);

		generator.run();
	}
}