package de.ruu.lib.mapstruct.explore.jeeeraaah.common;

import jakarta.annotation.Nullable;
import lombok.NonNull;

import java.util.Optional;
import java.util.Set;

public interface TaskGroup<T extends Task<?>>
{
	@NonNull String           name();
	@NonNull TaskGroup<T>     name(@NonNull String name);
	         Optional<String> description();
	@NonNull TaskGroup<T>     description(@Nullable String description);
	         Optional<Set<T>> tasks();
	         boolean          removeTask(T task);
}