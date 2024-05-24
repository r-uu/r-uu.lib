package de.ruu.lib.util.dto.jpa;

import lombok.Getter;

@Getter
public abstract class AbstractDTO
		implements
				Entity
				// DTO<AbstractEntity> will be implemented in sub classes because if it was implemented here it is disallowed
				// to be implemented in subclasses where a concrete AbstractEntity type can be used
				//, DTO<AbstractEntity>
{
	private Long id;
	private Short version;
}