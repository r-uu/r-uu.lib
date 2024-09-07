package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Slf4j
@Mapper
abstract class MapperNodeSimpleNodeEntity
{
	static MapperNodeSimpleNodeEntity INSTANCE = Mappers.getMapper(MapperNodeSimpleNodeEntity.class);

	private static ReferenceCycleTracking CONTEXT  = new ReferenceCycleTracking();

	abstract NodeEntity map(NodeSimple input);
	abstract NodeSimple map(NodeEntity input);

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(NodeSimple source, @MappingTarget NodeEntity target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(NodeEntity source, @MappingTarget NodeSimple target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}
}