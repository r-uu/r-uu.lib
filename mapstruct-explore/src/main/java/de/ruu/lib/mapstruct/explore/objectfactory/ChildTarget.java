package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.BiMappedTarget;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildTarget
		extends ChildAbstract<ParentTarget, ChildTarget>
		implements BiMappedTarget<ChildSource>
{
	ChildTarget(@NonNull String name) { super(name); }

	ChildTarget(@NonNull ParentTarget parent, @NonNull String name)
	{
		super(parent, name);
		parent.children().add(this);
	}

	void beforeMapping(@NonNull ChildSource source)
	{
		log.debug("before mapping starting");
		log.debug("before mapping finished");
	}

	void afterMapping(@NonNull ChildSource source)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull ChildSource toSource() { return Map_Child.INSTANCE.map(this); }
}