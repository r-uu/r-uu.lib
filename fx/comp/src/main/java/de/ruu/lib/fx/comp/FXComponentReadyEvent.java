package de.ruu.lib.fx.comp;

import de.ruu.lib.cdi.se.EventDispatcher;
import de.ruu.lib.util.AbstractEvent;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/** Event that can be thrown to indicate that a {@link FXCApp} has started successfully. */
@Slf4j
public class FXComponentReadyEvent extends AbstractEvent<FXCView, FXCService>
{
	@ApplicationScoped public static class FXComponentReadyEventDispatcher extends EventDispatcher<FXComponentReadyEvent> { }

	public FXComponentReadyEvent(final FXCView source, final FXCService data) { super(source, data); }

	/** programmatically specify command line vm option {@code --add-reads de.ruu.lib.fx.comp=ALL-UNNAMED} */
	public static void addReadsUnnamedModule()
	{
		FXComponentReadyEvent
				.class
				.getModule()
				.addReads(FXComponentReadyEvent.class.getClassLoader().getUnnamedModule());
	}
}