package de.ruu.lib.mapstruct.explore.objectfactory;

import de.ruu.lib.mapstruct.BiMappedSource;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChildSource extends Child implements BiMappedSource<ChildTarget>
{
	ChildSource(@NonNull Parent parent, @NonNull String name)
	{
		super(parent, name);
		parent.children().add(this);
	}

	@Override public void beforeMapping(@NonNull ChildTarget target)
	{
		log.debug("before mapping starting");
		log.debug("before mapping finished");
	}

	@Override public void afterMapping(@NonNull ChildTarget target)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull ChildTarget toTarget()
	{
		return Mapper.INSTANCE.map(this);
	}
}
