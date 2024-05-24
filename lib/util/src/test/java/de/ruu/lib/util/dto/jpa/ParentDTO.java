package de.ruu.lib.util.dto.jpa;

import de.ruu.lib.util.dto.DTO;
import de.ruu.lib.util.dto.MappedObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParentDTO
		extends AbstractDTO
		implements Parent, DTO<ParentEntity>
{
	private static final long serialVersionUID = 1L;
	
	private String name;

	public ParentDTO(ParentEntity entity) { name = entity.getName(); }

	@Override public ParentEntity toSource(MappedObjects mappedObjects) { return new ParentEntity(this);}
}