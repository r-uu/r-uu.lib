package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NodeSimple extends NodeAbstract<NodeSimple>
{
	public NodeSimple(@NonNull String name)                              { super(name); }
	public NodeSimple(@NonNull String name, @Nullable NodeSimple parent) { super(name, parent); }

	@Override public @NonNull List<NodeSimple> children() { return super.children(); }
}