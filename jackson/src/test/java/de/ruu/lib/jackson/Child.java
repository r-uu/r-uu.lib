package de.ruu.lib.jackson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * data class for jackson tests
 * <p>
 * data classes have to be top-level, {@code public} and equipped with a {@code public} no-args default constructor
 *
 * @author ruu
 */
@Getter
@ToString
@NoArgsConstructor
public class Child
{
	@NonNull
	private String field;

	@NonNull
	private Parent parent;

	public Child(@NonNull String field, @NonNull Parent parent)
	{
		this.field = field;
		this.parent = parent;

		parent.getChildren().add(this);
	}
}