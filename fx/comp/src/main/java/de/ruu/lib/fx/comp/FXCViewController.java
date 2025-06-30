package de.ruu.lib.fx.comp;

import javafx.fxml.FXML;

/**
 * Defines the behavior of a <code>FXVComp</code> view (see {@link FXCView}).
 *
 * @author r-uu
 */
public interface FXCViewController
{
	/**  @author r-uu */
	abstract class DefaultFXCViewController implements FXCViewController
	{
		/**
		 * Default implementation throws {@link ExceptionInInitializerError}, override this to define
		 * an appropriate (if empty) behavior of <code>FXVComp</code> components.
		 */
		@FXML protected void initialize()
		{
			throw new ExceptionInInitializerError("interface default, you likely want to override this");
		}
	}
}