package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Slf4j
@Mapper
abstract class Map_Child
{
	static Map_Child INSTANCE = Mappers.getMapper(Map_Child.class);

	private final static ReferenceCycleTracking CONTEXT  = new ReferenceCycleTracking();

	abstract ChildTarget map(ChildSource source);
	abstract ChildSource map(ChildTarget target);

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(ChildSource source, @MappingTarget ChildTarget target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@BeforeMapping void beforeMapping(ChildTarget source, @MappingTarget ChildSource target)
	{
		log.debug("source {}, target  {}", source, target);
		target.beforeMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@AfterMapping void afterMapping(ChildSource source, @MappingTarget ChildTarget target)
	{
		log.debug("source {}, target  {}", source, target);
		target.afterMapping(source); // invoke callback for mapping
	}

	/** annotating parameter {@code target} with {@link MappingTarget} is essential for this method being called */
	@AfterMapping void afterMapping(ChildTarget source, @MappingTarget ChildSource target)
	{
		log.debug("source {}, target  {}", source, target);
		target.afterMapping(source); // invoke callback for mapping
	}

	@ObjectFactory @NonNull ChildSource lookupOrCreate(@NonNull ChildTarget input)
	{
		ChildSource result = CONTEXT.get(input, ChildSource.class);
		if (result == null)
		{
			ParentSource parent =
					input.parent()
							.map(p -> Map_Parent.INSTANCE.lookupOrCreate(input.parent().get()))
							.orElse(null);

			if (parent == null) result = new ChildSource(input.name());
			else                result = new ChildSource(parent, input.name());

			CONTEXT.put(input , result);
			CONTEXT.put(result, input);
		}
		return result;
	}

	@ObjectFactory @NonNull ChildTarget lookupOrCreate(@NonNull ChildSource input)
	{
		ChildTarget result = CONTEXT.get(input, ChildTarget.class);
		if (result == null)
		{
			ParentTarget parent =
					input.parent()
							.map(p -> Map_Parent.INSTANCE.lookupOrCreate(input.parent().get()))
							.orElse(null);

			if (parent == null) result = new ChildTarget(input.name());
			else                result = new ChildTarget(parent, input.name());

			CONTEXT.put(input , result);
			CONTEXT.put(result, input);
		}
		return result;
	}
}