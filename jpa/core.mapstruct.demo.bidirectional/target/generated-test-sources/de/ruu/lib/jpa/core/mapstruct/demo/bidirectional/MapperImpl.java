package de.ruu.lib.jpa.core.mapstruct.demo.bidirectional;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T13:49:59+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
class MapperImpl extends Mapper {

    @Override
    DepartmentEntity map(DepartmentDTO input) {
        if ( input == null ) {
            return null;
        }

        DepartmentEntity departmentEntity = lookupOrCreate( input );

        beforeMapping( input, departmentEntity );

        departmentEntity.description( input.getDescription() );

        return departmentEntity;
    }

    @Override
    DepartmentDTO map(DepartmentEntity input) {
        if ( input == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = lookupOrCreate( input );

        beforeMapping( input, departmentDTO );

        return departmentDTO;
    }

    @Override
    EmployeeEntity map(EmployeeDTO input) {
        if ( input == null ) {
            return null;
        }

        EmployeeEntity employeeEntity = lookupOrCreate( input );

        beforeMapping( input, employeeEntity );

        employeeEntity.name( input.getName() );

        return employeeEntity;
    }

    @Override
    EmployeeDTO map(EmployeeEntity input) {
        if ( input == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = lookupOrCreate( input );

        beforeMapping( input, employeeDTO );

        employeeDTO.name( input.getName() );

        return employeeDTO;
    }
}
