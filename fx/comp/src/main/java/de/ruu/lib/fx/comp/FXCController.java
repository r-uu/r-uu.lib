package de.ruu.lib.fx.comp;

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
{
	void view(@NonNull V view) throws UnsupportedOperationException;

	/**  @author r-uu */
	@Slf4j
	abstract class DefaultFXCController<V extends FXCView<S>, S extends FXCService>
			implements FXCController<V, S>, FXCService
	{
		private V view;

		@Override public void view(@NonNull V view) throws UnsupportedOperationException
		{
			if (not(isNull(this.view)))
			{
				throw new UnsupportedOperationException("view already assigned, reassigning view not supported");
			}
			this.view = view;
		}

		protected @NonNull V view()
		{
			if (isNull(view))
			{
				throw new IllegalStateException(
						"view not assigned, assign view by calling view(V view) before accessing the view");
			}
			return view;
		}

		@FXML protected abstract void initialize();
	}
}