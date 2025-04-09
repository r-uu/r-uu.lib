package de.ruu.lib.mapstruct.explore.jeeeraaah.common;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface Task<TG extends TaskGroup<? extends Task<TG, ?>>, SELF extends Task<TG, SELF>>
{
	@NonNull TG      taskGroup();
	@NonNull String  name     ();
	@NonNull Boolean closed   ();

	Optional<String>    description();
	Optional<LocalDate> start      ();
	Optional<LocalDate> end        ();

	@NonNull SELF description(String    description    );
	@NonNull SELF start      (LocalDate startEstimated );
	@NonNull SELF end        (LocalDate finishEstimated);
	@NonNull SELF closed     (Boolean   closed         );

	Optional<SELF> superTask();
	@NonNull SELF superTask(SELF superTask);

	Optional<Set<SELF>> subTasks     ();
	boolean             addSubTask   (@NonNull SELF task);
	boolean             removeSubTask(@NonNull SELF task);

	Optional<Set<SELF>> predecessors     ();
	boolean             addPredecessor   (@NonNull SELF task);
	boolean             removePredecessor(@NonNull SELF task);

	Optional<Set<SELF>> successors     ();
	boolean             addSuccessor   (@NonNull SELF task);
	boolean             removeSuccessor(@NonNull SELF task);
}