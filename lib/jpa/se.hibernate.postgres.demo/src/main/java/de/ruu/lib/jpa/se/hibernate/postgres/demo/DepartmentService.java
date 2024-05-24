package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import java.util.Optional;

public interface DepartmentService
{
	DepartmentEntity           save(  DepartmentEntity entity);
	DepartmentEntity           update(DepartmentEntity entity);
	Optional<DepartmentEntity> find(  Long id);
	void                       delete(Long id);
}