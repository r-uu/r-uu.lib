package de.ruu.lib.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class TaskGroup
{
	@EqualsAndHashCode.Exclude
	private long jsonId = ThreadLocalRandom.current().nextLong();

	@NonNull private String name;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	// important: _both_ jackson annotations are necessary
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="jsonId", scope = Task.class)
	@JsonManagedReference("taskGroup-task")
	@Nullable private Set<Task> tasks = new HashSet<>();

	private TaskGroup() {} // with this no-args constructor jackson does not need a @JsonCreator annotated method

	TaskGroup(@NonNull String name) { this.name = name; }
}