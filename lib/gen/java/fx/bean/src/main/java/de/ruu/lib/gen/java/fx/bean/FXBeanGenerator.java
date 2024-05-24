package de.ruu.lib.gen.java.fx.bean;

import static de.ruu.lib.archunit.Util.isCollection;
import static de.ruu.lib.archunit.Util.publicMethodsWithAnnotationAndSortedByName;
import static de.ruu.lib.gen.java.CompilationUnitFileWriter.writer;
import static de.ruu.lib.gen.java.GeneratorCodeBlock.codeBlokk;
import static de.ruu.lib.gen.java.Visibility.PRIVATE;
import static de.ruu.lib.gen.java.Visibility.PUBLIC;
import static de.ruu.lib.gen.java.context.CompilationUnitContext.context;
import static de.ruu.lib.gen.java.doc.GeneratorJavaDoc.javaDoc;
import static de.ruu.lib.gen.java.element.GeneratorAnnotation.annotation;
import static de.ruu.lib.gen.java.element.GeneratorAnnotations.annotations;
import static de.ruu.lib.gen.java.element.GeneratorModifiers.modifiers;
import static de.ruu.lib.gen.java.element.GeneratorModifiersField.fieldModifiers;
import static de.ruu.lib.gen.java.element.GeneratorModifiersMethod.methodModifiers;
import static de.ruu.lib.gen.java.element.field.GeneratorField.field;
import static de.ruu.lib.gen.java.element.method.GeneratorMethod.method;
import static de.ruu.lib.gen.java.element.method.GeneratorParameter.parameter;
import static de.ruu.lib.gen.java.element.method.GeneratorParameters.parameters;
import static de.ruu.lib.gen.java.fx.bean.FXMapper.importManagement;
import static de.ruu.lib.gen.java.fx.bean.FXMapper.mapToFXProperty;
import static de.ruu.lib.util.Constants.LS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.CompilationUnitFileWriter;
import de.ruu.lib.gen.java.GeneratorCodeBlock;
import de.ruu.lib.gen.java.context.CompilationUnitContext;
import de.ruu.lib.gen.java.element.method.GeneratorMethod;
import de.ruu.lib.gen.java.element.method.GeneratorParameters;
import de.ruu.lib.gen.java.element.type.GeneratorClass;
import de.ruu.lib.util.Strings;
import de.ruu.lib.util.Time;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Analyses a provided {@link JavaClass} that represents an existing java type ({@link FXBeanGenerator#source}).
 * It generates a new corresponding java type (target) with a public java fx property for each public method in
 * source that is annotated with {@link FXProperty}.
 */
public class FXBeanGenerator
{
	@NonNull private final String packageName;    // package name for target
	@NonNull private final String simpleFileName; // simple file name for target

	@NonNull private final JavaClass source;      // source
	
	@NonNull private CompilationUnitContext context;

	public FXBeanGenerator(
			@NonNull String packageName, @NonNull String simpleFileName, @NonNull JavaClass source)
	{
		if (packageName   .isEmpty()) throw new IllegalArgumentException(    "package name may not be empty");
		if (simpleFileName.isEmpty()) throw new IllegalArgumentException("simple file name may not be empty");

		this.packageName    = packageName;
		this.simpleFileName = simpleFileName;

		this.source         = source;
		
		context = context(packageName, simpleFileName);
	}

	public void run() throws GeneratorException, IOException
	{
		GeneratorClass generator =
				GeneratorClass.classType(context, simpleFileName)
						.childNodesSeparator(LS)
						.javaDoc
						(
								javaDoc(context)
										.add("FXBean {@link " + simpleFileName + "}")
										.add("<p>")
										.add("generated by {@code " + getClass().getName() + "} at " + Time.getSortableTimestamp())

						)
						.annotations
						(
								annotations(context)
										.childNodesSeparator(LS)
										.add(annotation(context, RequiredArgsConstructor.class))
										.add(annotation(context, Builder.class))
						)
						.modifiers(modifiers(context).visibility(PUBLIC))
						.codeBlock(classCodeBlock())
						;
		CompilationUnitFileWriter writer = writer(packageName, simpleFileName);
		writer.write(generator.generate().toString());
	}

	private GeneratorCodeBlock classCodeBlock()
	{
		GeneratorCodeBlock result = codeBlokk(context).childNodesSeparator(LS);

		addGeneratorsForFields            (result);
		addGeneratorForPropertyConstructor(result);
		addGeneratorForCopyConstructor    (result);
		addGeneratorsForFluentStyleGetters(result);

		return result;
	}

	private void addGeneratorsForFields(GeneratorCodeBlock codeBlock)
	{
		boolean fieldsAdded = false;

		// generate fields
		for (JavaMethod method : publicMethodsWithAnnotationAndSortedByName(source, FXProperty.class))
		{
			codeBlock
					.add
					(
							field(context, propertyType(method), name(method))
									.modifiers(fieldModifiers(context).visibility(PRIVATE))
					);
			fieldsAdded = true;
		}

		// if fields have been added add child node separator to separate field declarations from
		// constructor
		if (fieldsAdded) codeBlock.add(codeBlock.childNodesSeparator().toString());
	}

	private void addGeneratorForPropertyConstructor(GeneratorCodeBlock codeBlock)
	{
		GeneratorMethod constructor =
				method(context, "", simpleFileName)
						.modifiers(methodModifiers(context).visibility(PUBLIC))
						.parameters(constructorParameters())
						.childNodesSeparator(LS)
						.codeBlock(constructorParametersCodeBlock());

		codeBlock.add(constructor);
		// separate property constructor from code to follow
		codeBlock.add(codeBlock.childNodesSeparator().toString());
	}

	private void addGeneratorForCopyConstructor(GeneratorCodeBlock codeBlock)
	{
		String parameterName = Strings.firstLetterToLowerCase(source.getSimpleName());

		GeneratorMethod constructor =
				method(context, "", simpleFileName)
						.modifiers(methodModifiers(context).visibility(PUBLIC))
						.parameters(
								parameters(context)
										.add(
												parameter(
														context,
														context.importManager().useType(source.getFullName()),
														parameterName)))
						.childNodesSeparator(LS)
						.codeBlock(constructorBeanCodeBlock(parameterName));

		codeBlock.add(constructor);
	}

	private void addGeneratorsForFluentStyleGetters(GeneratorCodeBlock codeBlock)
	{
		// generate methods
		for (JavaMethod method : publicMethodsWithAnnotationAndSortedByName(source, FXProperty.class))
		{
			codeBlock
					// add child node separator before each getter
					.add(codeBlock.childNodesSeparator().toString())
					.add
					(
							method(context, propertyType(method), name(method))
									.modifiers(methodModifiers(context).visibility(PUBLIC))
									.childNodesSeparator(LS)
									.codeBlock(codeBlokk(context).add("return " + name(method) + ";"))
					);
		}
	}

	private GeneratorParameters constructorParameters()
	{
		GeneratorParameters result = parameters(context);

		for (JavaMethod method : publicMethodsWithAnnotationAndSortedByName(source, FXProperty.class))
		{
			result.add(parameter(context, propertyType(method), name(method)));
		}

		return result;
	}

	private GeneratorCodeBlock constructorParametersCodeBlock()
	{
		GeneratorCodeBlock result = codeBlokk(context);

		List<String> assignments = new ArrayList<>();

//		List<JavaMethod> allMethods = new ArrayList<>(clazz.getAllMethods());
//		allMethods.sort
//		(
//				(m1, m2) ->
//				Integer.valueOf(m1.getAnnotationOfType(FXProperty.class).parameterIndex()).compareTo(
//				Integer.valueOf(m2.getAnnotationOfType(FXProperty.class).parameterIndex()))
//		);

		for (JavaMethod method : publicMethodsWithAnnotationAndSortedByName(source, FXProperty.class))
		{
			assignments.add("this." + name(method) + " = " + name(method) + ";");
		}
		
		result.add(String.join(LS, assignments));
		
		return result;
	}

	private GeneratorCodeBlock constructorBeanCodeBlock(String parameterName)
	{
		GeneratorCodeBlock result = codeBlokk(context);

		List<String> assignments = new ArrayList<>();

		for (JavaMethod method : publicMethodsWithAnnotationAndSortedByName(source, FXProperty.class))
		{
			String propertyType = propertyType(method);
			String propertyImplementation;

			// make sure we provide a correct property implementation type to import manager
			if (propertyType.contains("<"))
			{
				// build property type with prefix and without generic type parameters
				propertyImplementation =
						"javafx.beans.property.Simple" + propertyType.substring(0, propertyType.indexOf("<"));
			}
			else
			{
				// build property type with prefix and without generic type parameters
				propertyImplementation =
						"javafx.beans.property.Simple" + propertyType;
			}
			
			// provide a correct property implementation type to import manager
			propertyImplementation = context.importManager().useType(propertyImplementation);

			if (isCollection(method.getReturnType()))
			{
				assignments.add(name(method) + " = new " + propertyImplementation + "<>();");
				assignments.add(name(method) + ".addAll(" + parameterName + "." + name(method) + "());");
			}
			else
			{
				if (propertyType.contains("<"))
				{
					assignments.add(name(method) + " = new " + propertyImplementation + "<>(" + parameterName + "." + name(method) + "());");
				}
				else
				{
					assignments.add(name(method) + " = new " + propertyImplementation + "(" + parameterName + "." + name(method) + "());");
				}
			}
		}
		
		result.add(String.join(LS, assignments));
		
		return result;
	}

	private String propertyType(JavaMethod method)
	{
		Optional<String> result = mapToFXProperty(method.getReturnType(), importManagement(context));
		
		if (result.isPresent()) return result.get();
		
		throw new IllegalStateException("unexpected method return type: " + method.getReturnType().getName());
	}

	private String name(JavaMethod method)
	{
		return method.getName();
	}
}