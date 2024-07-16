package de.ruu.lib.gen.java.element.method;

import static de.ruu.lib.gen.java.context.CompilationUnitContext.context;
import static de.ruu.lib.gen.java.element.GeneratorAnnotation.annotation;
import static de.ruu.lib.gen.java.element.method.GeneratorParameter.parameter;
import static de.ruu.lib.gen.java.element.method.GeneratorParameters.parameters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.ruu.lib.gen.GeneratorException;
import de.ruu.lib.gen.java.context.CompilationUnitContext;
import de.ruu.lib.gen.java.element.GeneratorAnnotations;

//@Slf4j
class GeneratorParametersTest
{
	private CompilationUnitContext context;

	@BeforeEach void beforeEach() { context = context("package.name", "SimpleFileName"); }

	@Test void defaultGenerator() throws GeneratorException
	{
		GeneratorParameters generator = parameters(context);
		
//		log.debug(generator.generate().toString());
		
		assertThat(generator, is(not(nullValue())));
		assertThat(generator.generate().toString(), is("()"));
	}

	@Test void parameterisedGenerator() throws GeneratorException
	{
		String annotation = "annotation";
		String type       = "type";
		String name       = "name";

		GeneratorParameter generatorParameter = parameter(context, type, name);

		GeneratorAnnotations annotations = GeneratorAnnotations.annotations(context);
		annotations.add(annotation(context, annotation));
		
		generatorParameter.annotations(annotations);
		
		GeneratorParameters generator = parameters(context);
		generator.add(generatorParameter);
		
//		log.debug(generator.generate().toString());

		assertThat(
				generator.generate().toString(),
				is("(@" + annotation + " " + type + " " + name + ")"));
	}
}