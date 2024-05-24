package de.ruu.lib.util.dto.simple;

import de.ruu.lib.util.dto.DTOProvider;
import de.ruu.lib.util.dto.MappedObjects;

class SampleDTOSource implements DTOProvider<SampleDTO>
{
	private String name;

	public SampleDTOSource(SampleDTO dto) { name = dto.getName(); }

	@Override public SampleDTO toDTO() { return new SampleDTO(this); }

	@Override public SampleDTO toDTO(MappedObjects mappedObjects) { return null; }

	public String getName() { return name; }
}