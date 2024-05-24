package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import de.ruu.lib.mapstruct.CyclicDependencyTracking;

@org.mapstruct.Mapper
public abstract class Mapper
{
	public static final Mapper INSTANCE = Mappers.getMapper(Mapper.class);

	abstract DepartmentEntity map(DepartmentDTO    department, @Context CyclicDependencyTracking context);
	abstract DepartmentDTO    map(DepartmentEntity department, @Context CyclicDependencyTracking context);

	abstract EmployeeEntity map(EmployeeDTO    employee, @Context CyclicDependencyTracking context);
	abstract EmployeeDTO    map(EmployeeEntity employee, @Context CyclicDependencyTracking context);

	@BeforeMapping protected void beforeMapping(
			DepartmentDTO source, @MappingTarget DepartmentEntity target, @Context CyclicDependencyTracking context)
	{
		target.beforeMapping(source, context);
	}

	@AfterMapping protected void afterMapping(
			DepartmentDTO source, @MappingTarget DepartmentEntity target, @Context CyclicDependencyTracking context)
	{
		target.afterMapping(source, context);
	}

	@BeforeMapping protected void beforeMapping(
			DepartmentEntity source, @MappingTarget DepartmentDTO target, @Context CyclicDependencyTracking context)
	{
		target.beforeMapping(source, context);
	}

	@AfterMapping protected void afterMapping(
			DepartmentEntity source, @MappingTarget DepartmentDTO target, @Context CyclicDependencyTracking context)
	{
		target.afterMapping(source, context);
	}
}