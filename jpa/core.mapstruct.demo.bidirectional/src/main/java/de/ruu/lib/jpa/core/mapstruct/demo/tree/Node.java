package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface Node<T extends Node>
{
	@NonNull String      name();
	@NonNull T           name(@NonNull String name);
	@NonNull Optional<T> parent();
	@NonNull T           parent(@Nullable T parent);
	/** @return unmodifiable */
	@NonNull List<T>     children();

	boolean add   (@NonNull T node);
	boolean remove(@NonNull T node);
}