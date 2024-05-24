package de.ruu.lib.util.dto.jpa;

import de.ruu.lib.util.dto.DTOProvider;
import de.ruu.lib.util.dto.MappedObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParentEntity
		extends AbstractEntity
		implements Parent, DTOProvider<ParentDTO>
{
	private String name;

	public ParentEntity(ParentDTO dto) { name = dto.getName(); }

	@Override public ParentDTO toDTO(MappedObjects mappedObjects) { return new ParentDTO(this); }
}