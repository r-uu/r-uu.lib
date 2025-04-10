package de.ruu.lib.mapstruct;

import lombok.NonNull;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;

public interface MappableCyclicMapper<IN extends MappableCyclic<IN, OUT>, OUT extends MappableCyclic<OUT, IN>>
{
	@NonNull OUT map(@NonNull IN in, @NonNull @Context ReferenceCycleTracking context);

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@BeforeMapping default void beforeMapping(
			@NonNull                IN                     in,
			@NonNull @MappingTarget OUT                    out,
			@NonNull @Context       ReferenceCycleTracking context)
	{
		out.beforeMapping(out, in, context);
	}

	/**
	 * annotating parameter {@code out} with {@link MappingTarget} is essential for this method being called as well as
	 * annotating parameter {@code context} with {@link Context}
	 */
	@AfterMapping default void afterMapping(
			@NonNull                IN               in,
			@NonNull @MappingTarget OUT              out,
			@NonNull @Context ReferenceCycleTracking context)
	{
		out.afterMapping(out, in, context);
	}

	@NonNull Class<OUT> outType();
	@NonNull OUT        create(IN in);

	/**
	 * object factory will be called by mapstruct during generated {@link #map(MappableCyclic, ReferenceCycleTracking)}
	 * implementation
	 */
	@ObjectFactory default @NonNull OUT lookupOrCreate(@NonNull IN in, @NonNull ReferenceCycleTracking context)
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