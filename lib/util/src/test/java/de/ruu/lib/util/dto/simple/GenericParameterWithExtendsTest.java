package de.ruu.lib.util.dto.simple;

/** just proof that generic parameter syntax is valid */
class GenericParameterWithExtendsTest
{
	interface DTO<E extends Entity<?>>
	{
		E toEntity();
	}

	interface Entity<D extends DTO<?>>
	{
		D toDTO();
	}

	class DTOImpl implements DTO<EntityImpl>
	{
		@Override public EntityImpl toEntity() { return null; }
	}

	class EntityImpl implements Entity<DTOImpl>
	{
		@Override public DTOImpl toDTO() { return null; }
	}
}