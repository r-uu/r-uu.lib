package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.BiMappedSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentSource
		extends ParentAbstract<ParentSource, ChildSource>
		implements BiMappedSource<ParentTarget>
{
	/** use this constructor to create regular person objects */
	public ParentSource(@NonNull String name) { super(name); }

	void beforeMapping(@NonNull ParentTarget input)
	{
		log.debug("before mapping starting");

		for (ChildTarget child : input.children())
		{
			log.debug("child type {}", child.getClass().getName());
			children().add(child.toSource());
		}

		log.debug("before mapping finished");
	}

	void afterMapping(@NonNull ParentTarget input)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull ParentTarget toTarget() { return Map_Parent.INSTANCE.map(this); }
}