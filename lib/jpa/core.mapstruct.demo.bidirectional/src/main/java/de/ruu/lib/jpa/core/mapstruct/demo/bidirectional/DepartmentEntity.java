package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import de.ruu.lib.jpa.core.mapstruct.AbstractEntity;
import de.ruu.lib.mapstruct.CyclicDependencyTracking;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PROTECTED;

@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED, force = true) // generate no args constructor for jpa, mapstruct, ...
//@Accessors(fluent = true) // mapstruct does not seem to properly support fluent accessors
@Getter()
@ToString
@EqualsAndHashCode(callSuper = true)
public class DepartmentEntity extends AbstractEntity
{
	/**
	 * may not be modified from outside
	 * <p>not {@code final} or {@code @NonNull} because otherwise there has to be a constructor with {@code id}-parameter
	 */
//	private Long id;

	/** mutable non-null */
	@NonNull @Setter private String name;

	/** mutable */
	@Setter private String description;

	/** no direct access to modifiable set */
	@Getter(AccessLevel.NONE) private Set<EmployeeEntity> employees;

	/** return optional unmodifiable */
	public Optional<Set<EmployeeEntity>> getOptionalEmployees()
	{
		if (isNull(employees)) return Optional.empty();
		return Optional.of(Set.copyOf(employees));
	}

	public boolean add(@NonNull EmployeeEntity employee)
	{
		if (employee.getDepartment() == this)
		{
			if (employeesContains(employee)) return true;
			return nonNullEmployees().add(employee);
		}
		else
		{
			// following check should never return true
			if (employeesContains(employee))
				log.error("employee with {} is already contained in {}", employee.getDepartment(), this);

			// assign this department as department of employee and update employees
			employee.setDepartment(this);
			return nonNullEmployees().add(employee);
		}
	}

	public boolean remove(@NonNull EmployeeEntity employee)
	{
		if (isNull(employees)) return false;
		return employees.remove(employee);
	}

	private Set<EmployeeEntity> nonNullEmployees()
	{
		if (isNull(employees)) employees = new HashSet<>();
		return employees;
	}

	private boolean employeesContains(EmployeeEntity employee)
	{
		if (isNull(employees)) return false;
		return employees.contains(employee);
	}

	/** mapstruct callback (see {@link Mapper#beforeMapping(DepartmentDTO, DepartmentEntity, CyclicDependencyTracking)} ) */
	void beforeMapping(@NonNull DepartmentDTO department, CyclicDependencyTracking context)
	{
		// set fields that can not be modified from outside
		if (!isNull(department.getId())) setId(department.getId());

		if (department.getOptionalEmployees().isPresent())
		{
			department.getOptionalEmployees().get().forEach(e -> map(e, context));
		}
	}

	/** mapstruct callback (see {@link Mapper#beforeMapping(DepartmentDTO, DepartmentEntity, CyclicDependencyTracking)} ) */
	void afterMapping(@NonNull DepartmentDTO dto, CyclicDependencyTracking context) { }

	private void setId(@NonNull Long id) { this.id = id; }

	private void map(EmployeeDTO source, CyclicDependencyTracking context)
	{
		EmployeeEntity target = context.getMappedInstance(source, EmployeeEntity.class);

		if (target == null)
		{
			target = Mapper.INSTANCE.map(source, context);
			context.storeMappedInstance(source, target);

			if (add(target)) log.debug("added {}", target);
			else             log.error("failure adding {}", target);
		}
	}
}