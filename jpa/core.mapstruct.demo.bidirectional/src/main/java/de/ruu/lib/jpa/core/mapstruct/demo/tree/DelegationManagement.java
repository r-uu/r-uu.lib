package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages a {@link java.util.Map} that holds a pair of delegators for a {@link NodeSimple} instance.
 */
class DelegationManagement
{
	static class Delegators
	{
		@Nullable private NodeEntity entity;
		@Nullable private NodeDTO    dto;

		Delegators(@NonNull NodeEntity entity) { this.entity = entity; }
		Delegators(@NonNull NodeDTO    dto)    { this.dto    = dto; }

		@NonNull Optional<NodeEntity> entity() { return Optional.ofNullable(entity); }
		@NonNull Optional<NodeDTO>    dto()    { return Optional.ofNullable(dto); }

		@NonNull Delegators entity(@NonNull NodeEntity entity)
		{
			this.entity = entity;
			return this;
		}

		@NonNull Delegators dto(@NonNull NodeDTO dto)
		{
			this.dto = dto;
			return this;
		}
	}

	final static DelegationManagement INSTANCE = new DelegationManagement();

	private Map<NodeSimple, Delegators> map = new HashMap();

	private DelegationManagement() { }

	public Optional<Delegators> get(@NonNull NodeSimple node) { return Optional.ofNullable(map.get(node)); }

	public DelegationManagement put(@NonNull NodeSimple node, Delegators delegators)
	{
		map.put(node, delegators);
		return this;
	}
}