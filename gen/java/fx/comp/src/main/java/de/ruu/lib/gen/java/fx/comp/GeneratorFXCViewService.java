package de.ruu.lib.gen.java.fx.comp;

import de.ruu.lib.fx.comp.FXCViewService;
import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.CompilationUnitFileWriter;
import de.ruu.lib.gen.java.GeneratorCodeBlock;
import de.ruu.lib.gen.java.context.CompilationUnitContext;
import de.ruu.lib.gen.java.element.type.GeneratorInterface;
import de.ruu.lib.gen.java.element.type.GeneratorInterfaceExtends;
import de.ruu.lib.util.Time;

import java.io.IOException;

import static de.ruu.lib.gen.java.Visibility.PUBLIC;
import static de.ruu.lib.gen.java.context.CompilationUnitContext.context;
import static de.ruu.lib.gen.java.doc.GeneratorJavaDoc.javaDoc;
import static de.ruu.lib.gen.java.element.GeneratorModifiers.modifiers;
import static de.ruu.lib.gen.java.element.type.GeneratorInterface.interfaceType;
import static de.ruu.lib.util.Constants.LS;

public class GeneratorFXCViewService
{
	private String packageName;
	private String simpleFileName;

	public GeneratorFXCViewService(String packageName, String simpleFileName)
	{
		this.packageName    = packageName;
		this.simpleFileName = simpleFileName;
	}

	public void run() throws GeneratorException, IOException
	{
		CompilationUnitContext context = context(packageName, simpleFileName);
		GeneratorInterface generator =
				interfaceType(context, simpleFileName)
				.childNodesSeparator(LS)
				.javaDoc
				(
						javaDoc(context)
								.add("Java FX Component View Service")
								.add("<p>")
								.add("generated by {@code " + getClass().getName() + "} at " + Time.getSortableTimestamp())
				)
				.modifiers(modifiers(context).visibility(PUBLIC))
				.extendsClause(
						GeneratorInterfaceExtends.create(context)
								.add(context.importManager().useType(FXCViewService.class)))
				.codeBlock(GeneratorCodeBlock.codeBlokk(context))
				;
		CompilationUnitFileWriter writer =
				CompilationUnitFileWriter.writer(packageName, simpleFileName);
		writer.write(generator.generate().toString());
	}
}