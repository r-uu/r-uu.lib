package de.ruu.lib.mapstruct.explore.interfaces;

import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import de.ruu.lib.mapstruct.explore.common.TaskEntityDTO;
import de.ruu.lib.mapstruct.explore.common.TaskEntityJPA;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T13:49:58+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class Map_Task_EntityDTO_EntityJPAImpl implements Map_Task_EntityDTO_EntityJPA {

    @Override
    public TaskEntityJPA map(TaskEntityDTO in, ReferenceCycleTracking context) {
        TaskEntityJPA target = context.get( in, TaskEntityJPA.class );
        if ( target != null ) {
            return target;
        }

        if ( in == null ) {
            return null;
        }

        TaskEntityJPA taskEntityJPA = lookupOrCreate( in, context );

        context.put( in, taskEntityJPA );
        beforeMapping( in, taskEntityJPA, context );

        afterMapping( in, taskEntityJPA, context );

        return taskEntityJPA;
    }
}
