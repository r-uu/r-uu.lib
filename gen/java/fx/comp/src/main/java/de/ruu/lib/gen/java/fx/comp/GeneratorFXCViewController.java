package de.ruu.lib.gen.java.fx.comp;

import de.ruu.lib.fx.comp.FXCController.DefaultFXCController;
import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.CompilationUnitFileWriter;
import de.ruu.lib.gen.java.context.CompilationUnitContext;
import de.ruu.lib.gen.java.element.GeneratorAnnotation;
import de.ruu.lib.gen.java.element.type.GeneratorClass;
import de.ruu.lib.util.Time;
import javafx.fxml.FXML;

import java.io.IOException;

import static de.ruu.lib.gen.java.CompilationUnitFileWriter.writer;
import static de.ruu.lib.gen.java.GeneratorCodeBlock.codeBlokk;
import static de.ruu.lib.gen.java.Visibility.PROTECTED;
import static de.ruu.lib.gen.java.context.CompilationUnitContext.context;
import static de.ruu.lib.gen.java.doc.GeneratorJavaDoc.javaDoc;
import static de.ruu.lib.gen.java.element.GeneratorAnnotations.annotations;
import static de.ruu.lib.gen.java.element.GeneratorModifiersMethod.methodModifiers;
import static de.ruu.lib.gen.java.element.method.GeneratorMethod.method;
import static de.ruu.lib.gen.java.element.type.GeneratorClass.classType;
import static de.ruu.lib.gen.java.element.type.GeneratorClassExtends.extendsClause;
import static de.ruu.lib.gen.java.element.type.GeneratorClassImplements.implementsClause;
import static de.ruu.lib.util.Constants.LS;

public class GeneratorFXCViewController
{
	private final String packageName;
	private final String simpleFileName;
	private final String viewName;

	public GeneratorFXCViewController(String packageName, String simpleFileName)
	{
		this.packageName    = packageName;
		this.simpleFileName = simpleFileName;

		viewName = simpleFileName.substring(0, simpleFileName.indexOf("Controller"));
	}

	public void run() throws GeneratorException, IOException
	{
		CompilationUnitContext context = context(packageName, simpleFileName);
		GeneratorClass generator =
				classType(context, simpleFileName)
				.childNodesSeparator(LS)
				.javaDoc
				(
						javaDoc(context)
								.add("Java FX Component View Controller")
								.add("<p>")
								.add("generated by {@code " + getClass().getName() + "} at " + Time.getSortableTimestamp())
				)
				.extendsClause
				(
						extendsClause(context)
								.extendsClause
								(
										context.importManager().useType(DefaultFXCController.class)
												+ "<"
												+	  context.importManager().useType(viewName            ) + ", "
												+	  context.importManager().useType(viewName + "Service")
												+ ">"
//								        + " implements " + viewName + "Service"
								)
				)
				.implementsClause
				(
						implementsClause(context).add(viewName + "Service")
				)
				.childNodesSeparator(LS)
				.codeBlock
				(
						codeBlokk(context)
								.add
								(
										method(context, "void", "initialize")
												.modifiers(methodModifiers(context).visibility(PROTECTED))
												.childNodesSeparator(LS)
												.annotations
												(
														 annotations(context)
														 		.childNodesSeparator(" ")
																.add(GeneratorAnnotation.annotation(context, Override.class))
																.add(GeneratorAnnotation.annotation(context, FXML.class))
												)
								)
				)
				;
		CompilationUnitFileWriter writer = writer(packageName, simpleFileName);
		writer.write(generator.generate().toString());
	}
}