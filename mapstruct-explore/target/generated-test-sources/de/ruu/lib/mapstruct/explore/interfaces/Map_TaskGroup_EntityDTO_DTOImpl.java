package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T13:49:58+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class Map_TaskGroup_EntityDTO_DTOImpl implements Map_TaskGroup_EntityDTO_DTO {

    @Override
    public TaskGroupDTO map(TaskGroupEntityDTO in, ReferenceCycleTracking context) {
        TaskGroupDTO target = context.get( in, TaskGroupDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( in == null ) {
            return null;
        }

        TaskGroupDTO taskGroupDTO = lookupOrCreate( in, context );

        context.put( in, taskGroupDTO );
        beforeMapping( in, taskGroupDTO, context );

        afterMapping( in, taskGroupDTO, context );

        return taskGroupDTO;
    }

    @Override
    public TaskGroupDTO create(TaskGroupEntityDTO in, ReferenceCycleTracking context) {
        if ( in == null && context == null ) {
            return null;
        }

        String name = null;

        TaskGroupDTO taskGroupDTO = new TaskGroupDTO( name );

        return taskGroupDTO;
    }
}
