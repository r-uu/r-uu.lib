package de.ruu.lib.util.dto.bidirectional;

import de.ruu.lib.util.dto.MappedObjects;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class DTOEntityTest
{
	@Test void createOneDTO()
	{
		OneDTO one = new OneDTO("one");
		assertThat(one, is(not(nullValue())));
		assertThat(one.getName(), is("one"));
		assertThat(one.getManys().isEmpty(), is(true));
	}

	@Test void createMany()
	{
		OneDTO one = new OneDTO("one");
		ManyDTO many = new ManyDTO("many", one);

		assertThat(many          ,                      is(not(nullValue())));
		assertThat(many.getName(),                      is("many"));
		assertThat(one.getManys().isEmpty(),            is(false));
		assertThat(one.getManys().get().size(),         is(1));
		assertThat(one.getManys().get().contains(many), is(true));
	}

	@Test void mapFromDTOToEntity()
	{
		OneDTO  one  = new OneDTO("map me");
		ManyDTO many = new ManyDTO("map me too", one);

		OneEntity oneEntity = one.toSource(new MappedObjects());

		assertThat(oneEntity,                         is(not(nullValue())));
		assertThat(oneEntity.getName(),               is(one.getName()));
		assertThat(oneEntity.getManys().isEmpty(),    is(false));
		assertThat(oneEntity.getManys().get().size(), is(1));

		ManyEntity manyEntity = oneEntity.getManys().get().iterator().next();

		assertThat(manyEntity,                    is(not(nullValue())));
		assertThat(manyEntity.getName(),          is(many.getName()));
		assertThat(manyEntity.getOne().getName(), is(one .getName()));
	}

	@Test void mapFromEntityToDTO()
	{
		OneDTO  one  = new OneDTO("map me");
		ManyDTO many = new ManyDTO("map me too", one);

		OneEntity oneEntity = one.toSource(new MappedObjects());

		OneDTO mappedOne = oneEntity.toDTO(new MappedObjects());

		assertThat(mappedOne,                         is(not(nullValue())));
		assertThat(mappedOne.getName(),               is(one.getName()));
		assertThat(mappedOne.getManys().isEmpty(),    is(false));
		assertThat(mappedOne.getManys().get().size(), is(1));

		Set<ManyDTO> mappedManys = mappedOne.getManys().get();
		ManyDTO      mappedMany  = mappedManys.iterator().next();

		assertThat(mappedMany,                    is(not(nullValue())));
		assertThat(mappedMany.getName(),          is(many.getName()));
		assertThat(mappedMany.getOne().getName(), is(one .getName()));
	}
}