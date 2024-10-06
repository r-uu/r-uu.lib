package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.jpa.core.mapstruct.AbstractMappedDTO;
import de.ruu.lib.jpa.core.mapstruct.AbstractMappedEntity;
import de.ruu.lib.mapstruct.BiMappedSource;
import de.ruu.lib.mapstruct.BiMappedTarget;
import lombok.NonNull;

public abstract class AbstractMappedNode
		<D extends AbstractMappedDTO<?>, E extends AbstractMappedEntity<?>>
		extends AbstractNode<D, E>
		implements BiMappedSource<D>, BiMappedTarget<E>
{
	@Override public void beforeMapping(@NonNull D input) { mapIdAndVersion(input); }
	@Override public void beforeMapping(@NonNull E input) { mapIdAndVersion(input); }
}