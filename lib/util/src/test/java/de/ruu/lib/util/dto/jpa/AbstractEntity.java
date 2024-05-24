package de.ruu.lib.util.dto.jpa;

import lombok.Getter;

@Getter
public abstract class AbstractEntity
		implements
				Entity
				// DTOSource<AbstractDTO> will be implemented in sub classes because if it was implemented here it is disallowed
				// to be implemented in subclasses where a concrete AbstractDTO type can be used
				//, DTOSource<AbstractDTO>
{
	private Long id;
	private Short version;
}