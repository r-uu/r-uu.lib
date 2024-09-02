package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.jpa.core.mapstruct.AbstractMappedDTO;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.apache.poi.ss.formula.functions.T;

public class NodeDTO extends AbstractMappedDTO<NodeEntity>
{
	public NodeDTO(@NonNull String name) { super(name); }
	public NodeDTO(@NonNull String name, @Nullable T parent) { super(name, parent); }

	@Override
	public void afterMapping(@NonNull NodeEntity input)
	{

	}

	@Override
	public @NonNull NodeEntity toSource()
	{
		return null;
	}
}