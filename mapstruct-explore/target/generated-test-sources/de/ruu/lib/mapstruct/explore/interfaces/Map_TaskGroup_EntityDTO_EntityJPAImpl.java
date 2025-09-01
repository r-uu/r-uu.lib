package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskGroupEntityJPA;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T13:49:58+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class Map_TaskGroup_EntityDTO_EntityJPAImpl implements Map_TaskGroup_EntityDTO_EntityJPA {

    @Override
    public TaskGroupEntityJPA map(TaskGroupEntityDTO in, ReferenceCycleTracking context) {
        TaskGroupEntityJPA target = context.get( in, TaskGroupEntityJPA.class );
        if ( target != null ) {
            return target;
        }

        if ( in == null ) {
            return null;
        }

        TaskGroupEntityJPA taskGroupEntityJPA = lookupOrCreate( in, context );

        context.put( in, taskGroupEntityJPA );
        beforeMapping( in, taskGroupEntityJPA, context );

        afterMapping( in, taskGroupEntityJPA, context );

        return taskGroupEntityJPA;
    }
}
