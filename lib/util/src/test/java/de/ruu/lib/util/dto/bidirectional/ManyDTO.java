package de.ruu.lib.util.dto.bidirectional;

import de.ruu.lib.util.dto.DTO;
import de.ruu.lib.util.dto.MappedObjects;

import static de.ruu.lib.util.Strings.isNullOrEmptyOrBlank;
import static java.util.Objects.isNull;

public class ManyDTO implements DTO<ManyEntity>
{
	private static final long serialVersionUID = 1L;

	private String name;
	// may not be null to enforce bidirectional relation
	private OneDTO one;

	// serializers (json / jaxb) need a default constructor
	protected ManyDTO() { }

	public ManyDTO(String name, OneDTO one)
	{
		this(); // just in case something important happens in the default constructor

		if (isNullOrEmptyOrBlank(name)) throw new IllegalArgumentException("param name must not be null nor empty nor blank");
		if (isNull              (one )) throw new NullPointerException("param one must not be null");

		this.name = name;
		this.one  = one;

		// enforce bidirectional relation
		one.add(this);
	}

	public ManyDTO(ManyEntity many, MappedObjects mappedObjects)
	{
		this(); // just in case something important happens in the default constructor

		if (isNull(many)) throw new NullPointerException("param many must not be null");

		// store this DTO as target in mappedObjects to avoid infinite recursion
		mappedObjects.storeMappedInstance(many, this);

		// copy all the "simple" attributes
		name = many.getName();

		// map related objects (if any)
		// none in this case

		// do not forget to pass mappedObjects into toDTO method
		one = many.getOne().toDTO(mappedObjects);
	}

	@Override public ManyEntity toSource(MappedObjects mappedObjects)
	{
		// check if this DTO had already been mapped to an Entity
		ManyEntity result = mappedObjects.getMappedInstance(this, ManyEntity.class);

		// if so, return mapped entity
		if (result != null) return result;

		// this DTO had not been mapped so far, so create a new Entity
		result = new ManyEntity(this, mappedObjects);

// constructor's responsibility
//		// store the new Entity as target in mappedObjects to avoid infinite recursion
//		mappedObjects.storeMappedInstance(this, result);
//
//		// map related objects (if any)
//		// none in this case

		return result;
	}

	public String getName() { return name; }
	public OneDTO getOne () { return one;  }
}