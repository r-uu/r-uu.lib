package de.ruu.lib.jackson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
@ToString
public class Task
{
	@EqualsAndHashCode.Exclude
	private long jsonId = ThreadLocalRandom.current().nextLong();

	@NonNull private String name;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
//	@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="jsonId", scope = TaskGroup.class)
	@JsonBackReference("taskGroup-task")
	@NonNull  private TaskGroup group;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonBackReference("superTask-subTask")
	@Nullable private Task      superTask;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// important: _both_ jackson annotations are necessary
	@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="jsonId", scope = Task.class)
	@JsonManagedReference("superTask-subTask")
	@NonNull private final Set<Task> subTasks = new HashSet<>();

	private Task() {} // with this no-args constructor jackson does not need a @JsonCreator annotated method

	Task(@NonNull TaskGroup group, @NonNull String name)
	{
		this.group = group;
		this.name  = name;

		group.tasks().add(this);
	}
}