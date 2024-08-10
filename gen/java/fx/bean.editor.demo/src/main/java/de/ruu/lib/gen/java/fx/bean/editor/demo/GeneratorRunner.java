package de.ruu.lib.gen.java.fx.bean.editor.demo;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.bean.BeanGenerator;
import de.ruu.lib.gen.java.fx.bean.FXBeanGenerator;
import de.ruu.lib.gen.java.fx.bean.FXProperty;
import de.ruu.lib.gen.java.fx.bean.editor.FXBeanEditorFXMLGenerator;
import de.ruu.lib.gen.java.fx.comp.GeneratorFXCompBundle;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Analyses a provided {@link JavaClass} that represents an existing java type (source). It
 * generates artifacts for a java fx editor control.
 */
@Slf4j
class GeneratorRunner
{
	interface JavaModelDemo
	{
		@FXProperty boolean      aBoolean();
		@FXProperty int          anInteger();
		@FXProperty String       aString();
		@FXProperty BigDecimal   aBigDecimal();
		@FXProperty List<String> stringList();
	}

	@AllArgsConstructor
	class JavaModelDemoDTO
	{
		boolean      aBoolean;
		int          anInteger;
		String       aString;
		BigDecimal   aBigDecimal;
		List<String> stringList;
	}

	public static void main(String[] args) throws GeneratorException, IOException
	{
		log.debug("create java bean class based on interface JavaModelDemo");
		BeanGenerator beanGenerator =
				new BeanGenerator
				(
						GeneratorRunner.class.getPackageName(),
						"JavaBeanDemo",
						new ClassFileImporter().importClass(JavaModelDemo.class)
				);

		beanGenerator.run();

		log.debug("create java fx bean class based on interface JavaModelDemo");
		FXBeanGenerator fxBeanGenerator =
				new FXBeanGenerator
				(
						GeneratorRunner.class.getPackageName(),
						"FXBeanDemo",
						new ClassFileImporter().importClass(JavaModelDemo.class),
						new ClassFileImporter().importClass(JavaModelDemoDTO.class)
				);

		fxBeanGenerator.run();

		log.debug("create java fxml for bean editor based on interface JavaModelDemo");
		FXBeanEditorFXMLGenerator fxBeanEditorFXMLGenerator =
				new FXBeanEditorFXMLGenerator
				(
						GeneratorRunner.class.getPackageName(),
						"FXBeanEditorDemo.grid.",
						new ClassFileImporter().importClass(JavaModelDemo.class)
				);

		fxBeanEditorFXMLGenerator.run();

		log.debug("create java fx component bundle");
		GeneratorFXCompBundle fxBeanEditorComponentBundleGenerator =
				new GeneratorFXCompBundle
				(
						GeneratorRunner.class.getPackageName(),
						"FXBeanEditorDemo"
				);

		fxBeanEditorComponentBundleGenerator.run();
	}
}