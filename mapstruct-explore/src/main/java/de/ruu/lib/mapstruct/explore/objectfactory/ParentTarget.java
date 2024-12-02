package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.BiMappedTarget;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParentTarget
		extends ParentAbstract<ParentTarget, ChildTarget>
		implements BiMappedTarget<ParentSource>
{
	/** use this constructor to create regular person objects */
	public ParentTarget(@NonNull String name) { super(name); }

	void beforeMapping(@NonNull ParentSource input)
	{
		log.debug("before mapping starting");

		for (ChildSource child : input.children())
		{
			log.debug("child type {}", child.getClass().getName());
			children().add(child.toTarget());
		}

		log.debug("before mapping finished");
	}

	void afterMapping(@NonNull ParentSource input)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull ParentSource toSource() { return Map_Parent.INSTANCE.map(this); }
}