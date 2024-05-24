package de.ruu.lib.util.dto.bidirectional;

import de.ruu.lib.util.dto.DTO;
import de.ruu.lib.util.dto.MappedObjects;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static de.ruu.lib.util.Strings.isNullOrEmptyOrBlank;
import static java.util.Objects.isNull;

public class OneDTO implements DTO<OneEntity>
{
	private static final long serialVersionUID = 1L;

	private String name;

	// may explicitly be null, null indicates that there was no attempt to load related objects from db (lazy)
	private Set<ManyDTO> manys;

	// serializers (json / jaxb) need a default constructor
	protected OneDTO() { }

	public OneDTO(String name)
	{
		this(); // just in case something important happens in the default constructor

		if (isNullOrEmptyOrBlank(name)) throw new IllegalArgumentException("param name must not be null nor empty nor blank");

		this.name = name;
	}

	public OneDTO(OneEntity one, MappedObjects mappedObjects)
	{
		this(); // just in case something important happens in the default constructor

		if (isNull(one)) throw new NullPointerException("param one must not be null");

		// store this Entity as target in mappedObjects to avoid infinite recursion
		mappedObjects.storeMappedInstance(one, this);

		// copy all the "simple" attributes
		name = one.getName();

		// map related objects (if any)
		if (one.getManys().isPresent())
		{
			manys = new HashSet<>();

			for (ManyEntity many : one.getManys().get())
			{
				// do not forget to pass mappedObjects into toDTO method
				manys.add(many.toDTO(mappedObjects));
			}
		}
	}

	@Override public OneEntity toSource(MappedObjects mappedObjects)
	{
		// check if this DTO had already been mapped to an Entity
		OneEntity result = mappedObjects.getMappedInstance(this, OneEntity.class);

		// if so, return mapped entity
		if (result != null) return result;

		// this DTO had not been mapped so far, so create a new Entity
		result = new OneEntity(this, mappedObjects);

// constructor's responsibility
//		// store the new Entity as target in mappedObjects to avoid infinite recursion
//		mappedObjects.storeMappedInstance(this, result);
//
//		// map related objects (if any)
//		if (not(isNull(manys)))
//		{
//			for (ManyDTO many : manys)
//			{
//				// do not forget to pass mappedObjects into toSource method
//				result.add(many.toSource(mappedObjects));
//			}
//		}

		return result;
	}

	public String getName() { return name; }

	public Optional<Set<ManyDTO>> getManys() { return Optional.ofNullable(manys); }

	public boolean add(ManyDTO many) { return nonNullManys().add(many); }

	private Set<ManyDTO> nonNullManys()
	{
		if (isNull(manys)) manys = new HashSet<>();
		return manys;
	}
}