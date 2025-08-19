package de.ruu.lib.util.rs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter @Accessors(fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class ExceptionDTO
{
	private String message;
	private String cause;
}