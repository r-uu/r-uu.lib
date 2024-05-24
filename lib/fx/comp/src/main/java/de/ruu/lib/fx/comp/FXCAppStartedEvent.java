package de.ruu.lib.fx.comp;

import de.ruu.lib.cdi.se.EventDispatcher;
import de.ruu.lib.util.AbstractEvent;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

/** Event that can be thrown to indicate that a {@link FXCApp} has started successfully. */
@Slf4j
public class FXCAppStartedEvent extends AbstractEvent<FXCApp, DefaultFXCView>
{
	/** programmatically specify command line vm option {@code --add-reads de.ruu.lib.fx.comp=ALL-UNNAMED} */
	static
	{
		Module addReadsRetriever   = FXCAppStartedEvent.class.getModule();
		Module moduleToBeRetrieved = FXCAppStartedEvent.class.getClassLoader().getUnnamedModule();
		
		log.debug("setting add-reads vm option for module {} to {}", addReadsRetriever.getName(), moduleToBeRetrieved.getName());
		addReadsRetriever.addReads(moduleToBeRetrieved);
		log.debug("set     add-reads vm option for module {} to {}", addReadsRetriever.getName(), moduleToBeRetrieved.getName());
	}

	public FXCAppStartedEvent(final FXCApp source, final DefaultFXCView data) { super(source, data); }

	@ApplicationScoped public static class FXCAppStartedEventDispatcher extends EventDispatcher<FXCAppStartedEvent> { }
}