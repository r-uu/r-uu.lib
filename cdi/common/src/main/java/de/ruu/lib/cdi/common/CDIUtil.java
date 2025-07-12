package de.ruu.lib.cdi.common;

import jakarta.enterprise.inject.spi.CDI;
import lombok.NonNull;

public abstract class CDIUtil
{
//	public static <E extends Event> void fire(@NonNull E event) { CDI.current().getBeanManager().getEvent().fire(event); }
    public static void fire(@NonNull Object event) { CDI.current().getBeanManager().getEvent().fire(event); }
}