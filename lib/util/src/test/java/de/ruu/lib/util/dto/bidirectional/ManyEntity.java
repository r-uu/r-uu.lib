package de.ruu.lib.util.dto.bidirectional;

import de.ruu.lib.util.dto.DTOProvider;
import de.ruu.lib.util.dto.MappedObjects;

import static java.util.Objects.isNull;

public class ManyEntity implements DTOProvider<ManyDTO>
{
	private String name;
	// may not be null to enforce bidirectional relation
	private OneEntity one;

	// jpa needs a default constructor
	protected ManyEntity() { }

	public ManyEntity(ManyDTO many, MappedObjects mappedObjects)
	{
		this(); // just in case something important happens in the default constructor

		if (isNull(many)) throw new NullPointerException("param many must not be null");

		// store this Entity as target in mappedObjects to avoid infinite recursion
		mappedObjects.storeMappedInstance(many, this);

		// copy all the "simple" attributes
		name = many.getName();

		// map related objects (if any)

		// do not forget to pass mappedObjects into toDTO method
		one = many.getOne().toSource(mappedObjects);
	}

	@Override public ManyDTO toDTO(MappedObjects mappedObjects)
	{
		// check if this Entity had already been mapped to an Entity
		ManyDTO result = mappedObjects.getMappedInstance(this, ManyDTO.class);

		// if so, return mapped entity
		if (result != null) return result;

		// this DTO had not been mapped so far, so create a new Entity
		result = new ManyDTO(this, mappedObjects);

		return result;
	}

	public String getName() { return name; }

	public OneEntity getOne() { return one; }
}