package de.ruu.lib.mapstruct.explore.objectfactory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PROTECTED;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
@NoArgsConstructor(access = PROTECTED) // required by mapstruct in classes
@Getter                   // generate getter methods for all fields using lombok unless configured otherwise ({@code
                          // @Getter(AccessLevel.NONE}))
@Accessors(fluent = true) // generate fluent accessors with lombok and java-bean-style-accessors in non-abstract classes
                          // with ide, fluent accessors will (usually / by default) be ignored by mapstruct
abstract class Person
{
	private @NonNull String name;
}