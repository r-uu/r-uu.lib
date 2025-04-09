package de.ruu.lib.mapstruct.explore.jeeeraaah.common;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface Task<TG extends TaskGroup<?>>
{
	@NonNull TG      taskGroup();
	@NonNull String  name     ();
	@NonNull Boolean closed   ();

	Optional<String>    description();
	Optional<LocalDate> start      ();
	Optional<LocalDate> end        ();

	@NonNull Task<TG> description(String    description    );
	@NonNull Task<TG> start      (LocalDate startEstimated );
	@NonNull Task<TG> end        (LocalDate finishEstimated);
	@NonNull Task<TG> closed     (Boolean   closed         );

	Optional<? extends Task<TG>> superTask();
//	Optional<Task<TG>> superTask();
	@NonNull <T extends Task<TG>> T superTask(T superTask);

	Optional<Set<? extends Task<TG>>> subTasks     ();
	boolean                 addSubTask   (@NonNull Task<TG> task);
	boolean                 removeSubTask(@NonNull Task<TG> task);

	Optional<Set<Task<TG>>> predecessors     ();
	boolean                 addPredecessor   (@NonNull Task<TG> task);
	boolean                 removePredecessor(@NonNull Task<TG> task);

	Optional<Set<Task<TG>>> successors     ();
	boolean                 addSuccessor   (@NonNull Task<TG> task);
	boolean                 removeSuccessor(@NonNull Task<TG> task);
}