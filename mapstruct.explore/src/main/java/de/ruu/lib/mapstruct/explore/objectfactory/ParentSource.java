package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.BiMappedSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentSource extends Parent<ChildSource> implements BiMappedSource<ParentTarget>
{
	/** use this constructor to create regular person objects */
	public ParentSource(@NonNull String name) { super(name); }

	@Override public void beforeMapping(@NonNull ParentTarget input)
	{
		log.debug("before mapping starting");

		for (ChildTarget child : input.children())
		{
			children().add(child.toSource());
		}

		log.debug("before mapping finished");
	}

	@Override public void afterMapping(@NonNull ParentTarget input)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull ParentTarget toTarget() { return Mapper.INSTANCE.map(this); }
}