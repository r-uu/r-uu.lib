package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Slf4j
@Mapper
abstract class MapperNodeEntityNodeDTO
{
	static MapperNodeEntityNodeDTO INSTANCE = Mappers.getMapper(MapperNodeEntityNodeDTO.class);

	private static ReferenceCycleTracking CONTEXT  = new ReferenceCycleTracking();

	abstract NodeDTO    map(NodeEntity input);
	abstract NodeEntity map(NodeDTO    input);

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(NodeEntity source, @MappingTarget NodeDTO target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(NodeDTO source, @MappingTarget NodeEntity target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}
}