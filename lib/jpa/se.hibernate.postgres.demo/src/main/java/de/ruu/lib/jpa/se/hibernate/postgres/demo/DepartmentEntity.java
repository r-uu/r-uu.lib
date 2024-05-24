package de.ruu.lib.jpa.se.hibernate.postgres.demo;

import de.ruu.lib.jpa.core.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "department")
@Table(name = "department", schema = "test")
@Data
@Accessors(fluent = true)
@NoArgsConstructor // required by jpa
class DepartmentEntity extends AbstractEntity implements Department
{
	private String name;

	public DepartmentEntity(String name) { this.name  = name; }
}