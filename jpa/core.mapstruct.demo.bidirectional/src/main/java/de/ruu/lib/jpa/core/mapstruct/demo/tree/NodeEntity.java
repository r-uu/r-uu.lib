package de.ruu.lib.jpa.core.mapstruct.demo.tree;

import de.ruu.lib.jpa.core.mapstruct.AbstractMappedEntity;
import de.ruu.lib.jpa.core.mapstruct.demo.tree.DelegationManagement.Delegators;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@ToString
public class NodeEntity extends AbstractMappedEntity<NodeDTO> implements Node<NodeEntity>
{
	@NonNull private final static DelegationManagement delegationManagement = DelegationManagement.INSTANCE;

	@ToString.Exclude
	@NonNull private NodeSimple delegate;

	public NodeEntity(@NonNull String name)
	{
		delegate = new NodeSimple(name);
		delegationManagement.put(delegate, new Delegators(this));
	}
	public NodeEntity(@NonNull String name, @Nullable NodeSimple parent) { delegate = new NodeSimple(name, parent); }
	public NodeEntity(@NonNull NodeSimple nodeSimple)                    { delegate = nodeSimple; }

	@Override public @NonNull String     name() { return delegate.name(); }
	@Override public @NonNull NodeEntity name(@NonNull String name)
	{
		delegate.name(name);
		return this;
	}

	@Override public @NonNull Optional<NodeEntity> parent()
	{
		Optional<Delegators> optionalDelegatorsParent = lookupDelegatorsParent();
		if (optionalDelegatorsParent.isPresent())
		{
			return optionalDelegatorsParent.get().entity();
		}
		return Optional.empty();
	}

	@Override public @NonNull NodeEntity parent(@Nullable NodeEntity parentEntity)
	{
		// lookup or create parent
		NodeSimple parent;
		if (delegate.parent().isPresent()) parent = delegate.parent().get();
		else                               parent = new NodeSimple(parentEntity.name());

		// lookup or create delegators for parent
		Optional<Delegators> optionalDelegatorsParent = lookupDelegators(parent);
		if (optionalDelegatorsParent.isEmpty())
				// register parent with new delegators
				delegationManagement.put(parent, new Delegators(parentEntity));
		return this;
	}

	@Override public @NonNull List<NodeEntity> children()
	{
		List<NodeEntity> result = new ArrayList<>();

		for(NodeSimple child : delegate.children())
		{
			// lookup delegators for child
			Optional<Delegators> optionalDelegatorsChild = lookupDelegators(child);
			if (optionalDelegatorsChild.isPresent())
			{
				Delegators delegators = optionalDelegatorsChild.get();
				Optional<NodeEntity> optionalNodeEntity = delegators.entity();
				if (optionalNodeEntity.isPresent())
						result.add(optionalNodeEntity.get());
				else
						log.warn("no node entity delegate for child {} of {}", child, this);
			}
			else
					log.warn("no delegators for child {}", child);
		}

		return Collections.unmodifiableList(result);
	}

	@Override public boolean add(@NonNull NodeEntity node)
	{
		xxx();
		// lookup or create node entity delegator
		NodeEntity nodeEntityDelegator;
		Optional<NodeEntity> optionalNodeEntity = lookupDelegatorEntity();
		if (optionalNodeEntity.isPresent()) nodeEntityDelegator = optionalNodeEntity.get();
		else                                nodeEntityDelegator = new NodeEntity(delegate.name());
		return false;
	}

	@Override
	public boolean remove(@NonNull NodeEntity node)
	{
		Optional<Delegators> optionalDelegators = lookupDelegators();
		if (optionalDelegators.isPresent())
		{
			Delegators delegators = optionalDelegators.get();
			return delegators.entity().get().remove(node);
		}
		return false;
	}

	@Override
	public void afterMapping(@NonNull NodeDTO input)
	{

	}

	@Override
	public @NonNull NodeDTO toTarget()
	{
		return null;
	}

	/** @return optional {@link Delegators} for {@link #delegate} */
	@NonNull
	private Optional<Delegators> lookupDelegators() { return delegationManagement.get(delegate); }

	@NonNull
	private Optional<NodeEntity> lookupDelegatorEntity()
	{
		Optional<Delegators> optionalDelegators = lookupDelegators();
		if (optionalDelegators.isPresent())
		{
			return optionalDelegators.get().entity();
		}
		return Optional.empty();
	}

	/** @return optional {@link Delegators} for {@link #delegate#parent()} */
	@NonNull
	private Optional<Delegators> lookupDelegatorsParent()
	{
		if (delegate.parent().isPresent())
				return delegationManagement.get(delegate.parent().get());
		else
				return Optional.empty();
	}

	/** @return optional {@link Delegators} for {@link #delegate#parent()} */
	@NonNull
	private Optional<Delegators> lookupDelegators(NodeSimple nodeSimple)
	{
		return delegationManagement.get(nodeSimple);
	}

//	@Override public @NonNull T name(@NonNull String name)
//	{
//		delegate.name(name);
//		return (T) this;
//	}

//	@Override public @NonNull Optional<T> parent()
//	{
//		if (delegate.parent().isPresent())
//				return (Optional<T>) Optional.of(new NodeEntity<>(delegate.parent().get()));
//		return Optional.empty();
//	}
}