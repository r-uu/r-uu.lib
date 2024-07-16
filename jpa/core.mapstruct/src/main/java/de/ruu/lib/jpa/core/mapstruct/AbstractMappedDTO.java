package de.ruu.lib.jpa.core.mapstruct;

import de.ruu.lib.jpa.core.AbstractDTO;
import de.ruu.lib.mapstruct.BiMappedTarget;
import lombok.NonNull;

public abstract class AbstractMappedDTO<E extends AbstractMappedEntity<?>>
		extends AbstractDTO<E>
		implements BiMappedTarget<E>
{
	@Override public void beforeMapping(@NonNull E input) { mapIdAndVersion(input); }
}