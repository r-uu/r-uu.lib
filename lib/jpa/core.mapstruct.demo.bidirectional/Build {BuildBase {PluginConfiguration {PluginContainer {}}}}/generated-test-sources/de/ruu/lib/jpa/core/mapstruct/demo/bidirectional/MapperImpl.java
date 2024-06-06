package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import de.ruu.lib.mapstruct.CyclicDependencyTracking;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-06T11:39:20+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class MapperImpl extends Mapper {

    @Override
    DepartmentEntity map(DepartmentDTO department, CyclicDependencyTracking context) {
        DepartmentEntity target = context.getMappedInstance( department, DepartmentEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( department == null ) {
            return null;
        }

        String name = null;

        name = department.getName();

        DepartmentEntity departmentEntity = new DepartmentEntity( name );

        context.storeMappedInstance( department, departmentEntity );
        beforeMapping( department, departmentEntity, context );

        departmentEntity.setDescription( department.getDescription() );

        afterMapping( department, departmentEntity, context );

        return departmentEntity;
    }

    @Override
    DepartmentDTO map(DepartmentEntity department, CyclicDependencyTracking context) {
        DepartmentDTO target = context.getMappedInstance( department, DepartmentDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( department == null ) {
            return null;
        }

        String name = null;

        name = department.getName();

        DepartmentDTO departmentDTO = new DepartmentDTO( name );

        context.storeMappedInstance( department, departmentDTO );
        beforeMapping( department, departmentDTO, context );

        departmentDTO.setDescription( department.getDescription() );

        afterMapping( department, departmentDTO, context );

        return departmentDTO;
    }

    @Override
    EmployeeEntity map(EmployeeDTO employee, CyclicDependencyTracking context) {
        EmployeeEntity target = context.getMappedInstance( employee, EmployeeEntity.class );
        if ( target != null ) {
            return target;
        }

        if ( employee == null ) {
            return null;
        }

        String name = null;
        DepartmentEntity department = null;

        name = employee.getName();
        department = map( employee.getDepartment(), context );

        EmployeeEntity employeeEntity = new EmployeeEntity( name, department );

        context.storeMappedInstance( employee, employeeEntity );

        return employeeEntity;
    }

    @Override
    EmployeeDTO map(EmployeeEntity employee, CyclicDependencyTracking context) {
        EmployeeDTO target = context.getMappedInstance( employee, EmployeeDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( employee == null ) {
            return null;
        }

        String name = null;
        DepartmentDTO department = null;

        name = employee.getName();
        department = map( employee.getDepartment(), context );

        EmployeeDTO employeeDTO = new EmployeeDTO( name, department );

        context.storeMappedInstance( employee, employeeDTO );

        return employeeDTO;
    }
}
