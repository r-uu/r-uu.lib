package de.ruu.lib.mapstruct.explore.abstract_classes;

import de.ruu.lib.mapstruct.MappableCyclic;
import de.ruu.lib.mapstruct.ReferenceCycleTracking;
import lombok.NonNull;
import org.mapstruct.*;

public abstract class MappableCyclicMapper<IN extends MappableCyclic<IN, OUT>, OUT extends MappableCyclic<OUT, IN>>
{
	public abstract @NonNull OUT map(@NonNull IN in, @NonNull @Context ReferenceCycleTracking context);

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@BeforeMapping protected void beforeMapping(
			@NonNull                IN                     in,
			@NonNull @MappingTarget OUT                    out,
			@NonNull @Context ReferenceCycleTracking context)
	{
		out.beforeMapping(out, in, context);
	}

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@AfterMapping protected void afterMapping(
			@NonNull                IN               in,
			@NonNull @MappingTarget OUT              out,
			@NonNull @Context ReferenceCycleTracking context)
	{
		out.afterMapping(out, in, context);
	}

	protected abstract @NonNull Class<OUT> outType();
	protected abstract @NonNull OUT        create(IN in);

	/**
	 * object factory will be called by mapstruct during generated {@link #map(MappableCyclic, ReferenceCycleTracking)}
	 * implementation
	 */
	@ObjectFactory
	public @NonNull OUT lookupOrCreate(@NonNull IN in, @NonNull ReferenceCycleTracking context)
	{
		OUT out = context.get(in, outType());
		if (out == null)
		{
			out = create(in);
			context.put(in, out);
		}
		return out;
	}
}