package de.ruu.lib.jpa.core.mapstruct;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

import static java.util.Objects.isNull;

@Slf4j
@org.mapstruct.Mapper
abstract class Mapper
{
	static Mapper INSTANCE = Mappers.getMapper(Mapper.class);

	private static ReferenceCycleTracking CONTEXT  = new ReferenceCycleTracking();

	abstract SimpleMappedEntity map(SimpleMappedDTO    input);
	abstract SimpleMappedDTO    map(SimpleMappedEntity input);

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(SimpleMappedEntity source, @MappingTarget SimpleMappedDTO target)
	{
		log.debug("\nsource\n{}\ntarget\n{}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(SimpleMappedDTO source, @MappingTarget SimpleMappedEntity target)
	{
		log.debug("\nsource\n{}\ntarget\n{}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	@ObjectFactory @NonNull SimpleMappedEntity lookupOrCreate(@NonNull SimpleMappedDTO input)
	{
		SimpleMappedEntity result = CONTEXT.get(input, SimpleMappedEntity.class);
		if (isNull(result))
		{
			result = new SimpleMappedEntity(input.name());
			CONTEXT.put(input, result);
			CONTEXT.put(result, input);
		}
		return result;
	}

	@ObjectFactory @NonNull SimpleMappedDTO lookupOrCreate(@NonNull SimpleMappedEntity input)
	{
		SimpleMappedDTO result = CONTEXT.get(input, SimpleMappedDTO.class);
		if (isNull(result))
		{
			result = new SimpleMappedDTO(input.name());
			CONTEXT.put(input, result);
			CONTEXT.put(result, input);
		}
		return result;
	}

}