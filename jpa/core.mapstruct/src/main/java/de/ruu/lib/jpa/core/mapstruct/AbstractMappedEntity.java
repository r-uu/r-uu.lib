package de.ruu.lib.jpa.core.mapstruct;

import de.ruu.lib.jpa.core.AbstractEntity;
import de.ruu.lib.mapstruct.BiMappedSource;
import lombok.NonNull;

public abstract class AbstractMappedEntity<D extends AbstractMappedDTO<?>>
		extends AbstractEntity<D>
		implements BiMappedSource<D>
{
	@Override public void beforeMapping(@NonNull D input) { mapIdAndVersion(input); }
}