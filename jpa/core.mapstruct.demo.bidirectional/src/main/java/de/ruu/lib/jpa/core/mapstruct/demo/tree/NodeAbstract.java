package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.util.Strings;
import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode
@ToString
public abstract class NodeAbstract<T extends Node<T>> implements Node<T>
{
	@NonNull  private String  name;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Nullable private T       parent;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NonNull  private List<T> children;

	public NodeAbstract(@NonNull String name)
	{
		name(name);
		children = new ArrayList<>();
	}

	public NodeAbstract(@NonNull String name, @Nullable T parent)
	{
		this(name);
		this.parent = parent;
	}

	@Override
	@NonNull
	public String name() { return name; }
	@NonNull
	public T name(@NonNull String name)
	{
		if (Strings.isEmptyOrBlank(name)) throw new IllegalArgumentException("name must not be empty nor blank");
		this.name = name;
		return (T) this;
	}

	/**
	 * Manually implement this method because lombok can not generate accessor returning {@code Optional} as required by
	 * {@link Node} for a non-{@code Optional} field as {@link #parent}.
	 *
	 * @return {@link #parent} wrapped in an {@code Optional}
	 */
	@Override
	@NonNull
	public Optional<T> parent() { return Optional.ofNullable(parent); }
	@Override
	@NonNull
	public T parent(@Nullable T parent)
	{
		this.parent = parent;
		return (T) this;
	}

	/**
	 * Manually implement this method because lombok can not generate accessor returning unmodifiable list.;
	 *
	 * @return {@link #parent} wrapped in an {@code Optional}
	 */
	@Override
	@NonNull public List<T> children() { return Collections.unmodifiableList(children); }

	/** handling of bidirectional relation */
	@Override
	public boolean add(@NonNull T node)
	{
		node.parent((T) this);
		return children.add(node);
	}

	/** handling of bidirectional relation */
	@Override
	public boolean remove(@NonNull T node)
	{
		boolean result = children.remove(node);
		if (result) node.parent(null);
		return result;
	}
}