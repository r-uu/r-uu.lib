package de.ruu.lib.fx.comp;

import jakarta.enterprise.inject.spi.CDI;
import javafx.fxml.FXML;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static de.ruu.lib.util.BooleanFunctions.not;
import static java.util.Objects.isNull;

/**
 * Defines the behavior of a <code>FXVComp</code> view (see {@link FXCView}).
 *
 * @author r-uu
 */
public interface FXCController<V extends FXCView<S>, S extends FXCService>
//public interface FXCController<V extends FXCView<?>>
//	extends FXCView<S>
{
	void view(@NonNull V view) throws UnsupportedOperationException;

	/**  @author r-uu */
	@Slf4j
//	abstract class DefaultFXCController<V extends DefaultFXCView<V, S, C>, S extends FXCService, C extends FXCController<V>>
//			implements FXCController<V>, FXCView<FXCService>
	abstract class DefaultFXCController<V extends FXCView<S>, S extends FXCService>
			implements FXCController<V, S>, FXCView<S>
	{
		private V view;

		@FXML protected void initialize()
		{
			log.debug("\n" + "-".repeat(10) + "firing component ready event");
			CDI.current().getBeanManager().getEvent().fire(new FXComponentReadyEvent(this, view.service()));
		}

		@Override public void view(@NonNull V view) throws UnsupportedOperationException
		{
			if (not(isNull(this.view)))
			{
				throw new UnsupportedOperationException("view already set, overwriting view not supported");
			}
			this.view = view;
		}

		protected @NonNull V view() { return view; }
	}
}