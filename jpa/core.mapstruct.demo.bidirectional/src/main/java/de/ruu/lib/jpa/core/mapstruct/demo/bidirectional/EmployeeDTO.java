package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import de.ruu.lib.jpa.core.mapstruct.AbstractMappedDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PROTECTED;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Slf4j
@Getter                   // generate getter methods for all fields using lombok unless configured otherwise ({@code
                          // @Getter(AccessLevel.NONE}))
@Accessors(fluent = true) // generate fluent accessors with lombok and java-bean-style-accessors in non-abstract classes
                          // with ide, fluent accessors will (usually / by default) be ignored by mapstruct
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED, force = true) // generate no args constructor for jsonb, jaxb, mapstruct, ...
public class EmployeeDTO extends AbstractMappedDTO<EmployeeEntity>
{
	/** mutable non-null */
	@NonNull @Setter private String name;

	/** mutable non-null */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NonNull @Setter private DepartmentDTO department;

	EmployeeDTO(@NonNull DepartmentDTO department, @NonNull String name)
	{
		this.department = department;
		department.add(this);
	}

	// java bean style accessors for those who do not work with fluent style accessors (mapstruct)
	public @NonNull String getName() { return name(); }

	@Override public void beforeMapping(@NonNull EmployeeEntity source)
	{
		log.debug("before mapping starting");
		log.debug("before mapping finished");
	}

	@Override public void afterMapping(@NonNull EmployeeEntity source)
	{
		log.debug("after mapping starting");
		log.debug("after mapping finished");
	}

	@Override public @NonNull EmployeeEntity toSource() { return Mapper.INSTANCE.map(this); }
}