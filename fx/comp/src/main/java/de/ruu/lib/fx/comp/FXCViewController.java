package de.ruu.lib.fx.comp;

import jakarta.enterprise.inject.spi.CDI;
import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;

/**
 * Defines the behavior of a <code>FXVComp</code> view (see {@link FXCView}).
 *
 * @author r-uu
 */
public interface FXCViewController<S extends FXCView>
{
	/**  @author r-uu */
	@Slf4j
	abstract class DefaultFXCViewController implements FXCViewController
	{
		@FXML protected void initialize()
		{
			log.debug("\n" + "-".repeat(10) + "firing component ready event");
			CDI.current().getBeanManager().getEvent().fire(new FXComponentReadyEvent(this, null));
		}
	}
}