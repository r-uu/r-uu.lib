package de.ruu.lib.util.dto.simple;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class SampleDTOTest
{
	@Test
	void mapFromSampleDTOToSampleDTOSource()
	{
		SampleDTO       sampleDTO       = new SampleDTO("map me");
		SampleDTOSource sampleDTOSource = sampleDTO.toSource();

		assertThat(sampleDTOSource, is(not(nullValue())));
		assertThat(sampleDTOSource.getName(), is(sampleDTO.getName()));
	}
}