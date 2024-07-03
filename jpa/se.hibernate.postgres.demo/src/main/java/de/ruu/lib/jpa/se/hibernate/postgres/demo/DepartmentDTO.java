package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.jpa.core.AbstractDTO;

class DepartmentDTO extends AbstractDTO<DepartmentEntity> implements Department
{
	private String name;

	DepartmentDTO(String name) { this.name = name; }

	@Override public String name() { return name; }

	public DepartmentEntity toSource()
	{
		return new DepartmentEntity(name);
	}
}