package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T13:49:59+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
class MapperNodeSimpleNodeEntityImpl extends MapperNodeSimpleNodeEntity {

    @Override
    NodeEntity map(NodeSimple input) {
        if ( input == null ) {
            return null;
        }

        NodeEntity nodeEntity = new NodeEntity();

        beforeMapping( input, nodeEntity );

        return nodeEntity;
    }

    @Override
    NodeSimple map(NodeEntity input) {
        if ( input == null ) {
            return null;
        }

        NodeSimple nodeSimple = new NodeSimple();

        beforeMapping( input, nodeSimple );

        return nodeSimple;
    }
}
