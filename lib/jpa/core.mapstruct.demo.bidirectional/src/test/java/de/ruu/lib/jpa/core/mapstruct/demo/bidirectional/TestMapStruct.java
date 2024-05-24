package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ruu.lib.mapstruct.CyclicDependencyTracking;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class TestMapStruct
{
	@Test void mapEmptyDepartmentDTO()
	{
		DepartmentDTO department = new DepartmentDTO();

		assertThrows(
				NullPointerException.class, () -> Mapper.INSTANCE.map(department, new CyclicDependencyTracking()));
	}

	@Test void mapEmptyDepartmentEntity()
	{
		DepartmentEntity department = new DepartmentEntity();

		assertThrows(
				NullPointerException.class, () -> Mapper.INSTANCE.map(department, new CyclicDependencyTracking()));
	}

	@Test void mapEmptyEmployeeDTO()
	{
		EmployeeDTO employee = new EmployeeDTO();

		assertThrows(
				NullPointerException.class, () -> Mapper.INSTANCE.map(employee, new CyclicDependencyTracking()));
	}

	@Test void mapEmptyEmployeeEntity()
	{
		EmployeeEntity employee = new EmployeeEntity();

		assertThrows(
				NullPointerException.class, () -> Mapper.INSTANCE.map(employee, new CyclicDependencyTracking()));
	}

	@Test void mapInvalidNamedDepartmentDTO()
	{
		String name = null;

		assertThrows(
				NullPointerException.class,
				() -> Mapper.INSTANCE.map(new DepartmentDTO(name), new CyclicDependencyTracking()));
	}

	@Test void mapInvalidNamedEmployeeDTO()
	{
		String name = null;

		assertThrows(
				NullPointerException.class,
				() ->
						Mapper
								.INSTANCE.map(new EmployeeDTO(name, new DepartmentDTO("name")), new CyclicDependencyTracking()));
	}

	@Test void mapInvalidNamedDepartmentEntity()
	{
		String name = null;

		assertThrows(
				NullPointerException.class,
				() -> Mapper.INSTANCE.map(new DepartmentEntity(name), new CyclicDependencyTracking()));
	}

	@Test void mapInvalidNamedEmployeeEntity()
	{
		String           name       = null;
		DepartmentEntity department = new DepartmentEntity("name");

		assertThrows(
				NullPointerException.class,
				() -> Mapper.INSTANCE.map(new EmployeeEntity(name, department), new CyclicDependencyTracking()));
	}

	@Test void mapValidDepartmentDTO()
	{
		String        name       = "name";
		DepartmentDTO department = new DepartmentDTO(name);

		DepartmentEntity departmentEntity = Mapper.INSTANCE.map(department, new CyclicDependencyTracking());

		assertThat(departmentEntity          , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentEntity.getName(), Matchers.is(name));
	}

	@Test void mapValidDepartmentEntity()
	{
		String           name       = "name";
		DepartmentEntity department = new DepartmentEntity(name);

		DepartmentDTO departmentDTO = Mapper.INSTANCE.map(department, new CyclicDependencyTracking());

		assertThat(departmentDTO          , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentDTO.getId()  , is(department.getId()));
		assertThat(departmentDTO.getName(), Matchers.is(name));
	}

	@Test void mapValidEmployeeDTO()
	{
		String         name       = "name";
		DepartmentDTO  department = new DepartmentDTO(name);
		EmployeeDTO    employee   = new EmployeeDTO(name, department);

		EmployeeEntity employeeEntity = Mapper.INSTANCE.map(employee, new CyclicDependencyTracking());

		assertThat(employeeEntity                          , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(employeeEntity.getName()                , Matchers.is(name));
		assertThat(employeeEntity.getDepartment().getName(), Matchers.is(name));
	}

	@Test void mapValidEmployeeEntity()
	{
		String            name       = "name";
		DepartmentEntity  department = new DepartmentEntity(name);
		EmployeeEntity    employee   = new EmployeeEntity(name, department);

		EmployeeDTO employeeDTO = Mapper.INSTANCE.map(employee, new CyclicDependencyTracking());

		assertThat(employeeDTO                          , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(employeeDTO.getName()                , Matchers.is(name));
		assertThat(employeeDTO.getDepartment().getName(), Matchers.is(name));
	}

	@Test void mapValidDepartmentDTOWithEmployees()
	{
		String        name              = "name";
		DepartmentDTO department        = new DepartmentDTO(name);
		int           numberOfEmployees = 3;

		for (int i = 0; i < numberOfEmployees; i++)
		{
			department.add(new EmployeeDTO("name." + i, department));
		}

		DepartmentEntity departmentEntity = Mapper.INSTANCE.map(department, new CyclicDependencyTracking());

		assertThat(departmentEntity                                    , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentEntity.getOptionalEmployees()             , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentEntity.getOptionalEmployees().isPresent() , Matchers.is(true));
		assertThat(departmentEntity.getOptionalEmployees().get().size(), Matchers.is(numberOfEmployees));
	}

	@Test void mapValidDepartmentEntityWithEmployees()
	{
		String           name              = "name";
		DepartmentEntity department        = new DepartmentEntity(name);
		int              numberOfEmployees = 3;

		for (int i = 0; i < numberOfEmployees; i++)
		{
			department.add(new EmployeeEntity("name." + i, department));
		}

		DepartmentDTO departmentDTO = Mapper.INSTANCE.map(department, new CyclicDependencyTracking());

		assertThat(departmentDTO                                    , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentDTO.getOptionalEmployees()             , Matchers.is(Matchers.not(Matchers.nullValue())));
		assertThat(departmentDTO.getOptionalEmployees().isPresent() , Matchers.is(true));
		assertThat(departmentDTO.getOptionalEmployees().get().size(), Matchers.is(numberOfEmployees));
	}
}