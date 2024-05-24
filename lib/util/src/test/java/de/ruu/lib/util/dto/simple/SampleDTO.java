package de.ruu.lib.util.dto.simple;

import de.ruu.lib.util.dto.DTO;
import de.ruu.lib.util.dto.MappedObjects;

public class SampleDTO implements DTO<SampleDTOSource>
{
	private static final long serialVersionUID = 1L;

	private String name;

	public SampleDTO(String name) { this.name = name; }

	public SampleDTO(SampleDTOSource source) { name = source.getName(); }

	@Override public SampleDTOSource toSource(MappedObjects mappedObjects) { return new SampleDTOSource(this); }

	public String getName() { return name; }
}