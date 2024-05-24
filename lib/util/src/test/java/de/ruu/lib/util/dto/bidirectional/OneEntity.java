package de.ruu.lib.util.dto.bidirectional;

import de.ruu.lib.util.dto.DTOProvider;
import de.ruu.lib.util.dto.MappedObjects;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

public class OneEntity implements DTOProvider<OneDTO>
{
	private String name;

	// may explicitly be null, null indicates that there was no attempt to load related objects from db (lazy)
	private Set<ManyEntity> manys;

	// jpa needs a default constructor
	protected OneEntity() { }

	public OneEntity(OneDTO one, MappedObjects mappedObjects)
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

			for (ManyDTO many : one.getManys().get())
			{
				// do not forget to pass mappedObjects into toSource method
				manys.add(many.toSource(mappedObjects));
			}
		}
	}

	@Override public OneDTO toDTO(MappedObjects mappedObjects)
	{
		// check if this Entity had already been mapped to a DTO
		OneDTO result = mappedObjects.getMappedInstance(this, OneDTO.class);

		// if so, return mapped DTO
		if (result != null) return result;

		// this Entity had not been mapped so far, so create a new DTO
		result = new OneDTO(this, mappedObjects);

// constructor's responsibility
//		// store the new DTO as target in mappedObjects to avoid infinite recursion
//		mappedObjects.storeMappedInstance(this, result);
//
//		// map related objects (if any)
//		if (not(isNull(manys)))
//		{
//			for (ManyEntity many : manys)
//			{
//				// do not forget to pass mappedObjects into toSource method
//				result.add(many.toDTO(mappedObjects));
//			}
//		}

		return result;
	}

	public String getName() { return name; }

	public Optional<Set<ManyEntity>> getManys() { return Optional.ofNullable(manys); }

	public boolean add(ManyEntity many) { return nonNullManys().add(many); }

	private Set<ManyEntity> nonNullManys()
	{
		if (manys == null) manys = new HashSet<>();
		return manys;
	}
}