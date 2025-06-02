package de.ruu.lib.fx.comp;

import de.ruu.lib.util.AbstractEvent;
import jakarta.enterprise.inject.spi.CDI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static de.ruu.lib.util.BooleanFunctions.not;
import static java.util.Objects.isNull;

/**
 * Base class for JavaFX {@link Application}s with CDI support. <code>FXCApp</code>s provide convenient support
 * for automatic initialisation of various parts of JavaFX applications with CDI support:
 * <ul>
 *   <li>CDI is bootstrapped from {@link FXCAppRunner}s which call {@link Application#launch(Class, String...)}
 *       after bootstrapping CDI. This allows JavaFX call {@link #start(Stage)}, which is the common way to start JavaFX
 *       applications, to stay independent from any CDI bootstrapping efforts.
 *   </li>
 *   <li>For a {@link FXCApp} named <code>XApp</code> you can define a {@link DefaultFXCView} class named
 *       <code>XAppView</code> that will automatically be used to obtain a {@link Scene} object for the JavaFX {@link
 *       Application}. Therefore {@link DefaultFXCView} will load a file named <code>XAppView.fxml</code> if it can
 *       be found in the same package as <code>XAppView</code>. Analogously a <code>XAppView.css</code> file from the
 *       same package (if present) is used to apply custom css definitions.
 *       <p>
 *       Note: Above mentioned naming conventions can be overridden in {@link DefaultFXCView#getFXMLResourceName()}
 *             and {@link DefaultFXCView#getCSSResourceName()}.
 *   </li>
 *   <li>For a {@link FXCApp} named <code>XApp</code> you can define a {@link FXCViewController} class
 *       named <code>XAppController</code> that will (if present) automatically be used to obtain a controller object
 *       for the {@link DefaultFXCView}.
 *       <p>
 *       Note: Above mentioned naming conventions can be overridden in {@link
 *             DefaultFXCView#getControllerClassName()} and / or {@link DefaultFXCView#getControllerClass()}.
 *   </li>
 * </ul>
 *
 * While {@link FXCApp} instances itself are not CDI managed, the above mentioned {@link DefaultFXCView} and
 * its {@link FXCViewController} objects are CDI managed. This makes CDI available for JavaFX applications while
 * preserving benefits from JavaFX injection via <code>@FXML</code> annotations.
 *
 * @author r-uu
 */
@Slf4j
public abstract class FXCApp extends Application
{
	/** Event that will be fired as soon as the primary stage of this {@link FXCApp} was shown. */
	public static class StageShowingEvent extends AbstractEvent<FXCApp, Stage>
	{
		public StageShowingEvent(final FXCApp source, final Stage data) { super(source, data); }
	}

	private Stage                    primaryStage;
	private Optional<DefaultFXCView> primaryViewOptional;

	/**
	 * Starts a {@link FXCApp}. The main purpose of this method is to obtain a {@link DefaultFXCView} instance
	 * and to pass the {@link Scene} object of that instance to the primary stage of this application. Finally the primary
	 * stage will be displayed and events were being fired to signal the display and start of the application.
	 * <p>
	 * fires {@link StageShowingEvent}<p>
	 * fires {@link FXCAppStartedEvent}
	 *
	 * @throws ExceptionInInitializerError if a {@link DefaultFXCView} instance can not be obtained successfully
	 *
	 * @param primaryStage
	 * @throws ExceptionInInitializerError
	 */
	@Override public void start(final Stage primaryStage) throws ExceptionInInitializerError
	{
		this.primaryStage = primaryStage;

		primaryStage.initStyle(getStageStyle());
		primaryStage.setTitle (getStageTitle());

		if (getStageIcon().isPresent()) { primaryStage.getIcons().add(getStageIcon().get()); }

		primaryStage.setOnShowing(e -> onStageShowing());
//	TODO property add listener style does not work - why???
//		primaryStage.onShowingProperty().addListener((obs, old, newValue) -> onStageShowing());

		final Optional<DefaultFXCView> optionalView = optionalPrimaryView();

		if (optionalView.isPresent())
		{
			final DefaultFXCView view = optionalView.get();

			final Scene scene = view.getScene();
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();

			onApplicationStarted(view);
		}
		else
		{
			throw new ExceptionInInitializerError("could not lookup view for " + getClass());
		}
	}

	@Override public void stop() throws Exception
	{
		super.stop();
		onApplicationStopped();
	}

	/**
	 * @return <code>Optional</code> that contains a {@link DefaultFXCView} instance that was built following a best
	 *         effort approach complying to the naming conventions. If the best effort approach fails the returned <code>
	 *         Optional</code> contains a <code>null</code> value.
	 * @see    FXCApp#optionalViewClass() for a description of the best effort approach
	 */
	protected Optional<DefaultFXCView> optionalPrimaryView()
	{
		if (not(isNull(primaryViewOptional))) return primaryViewOptional;

		final Optional<Class<? extends DefaultFXCView>> viewClassOptional = optionalViewClass();

		if (viewClassOptional.isEmpty())
		{
			log.error("could not create " + DefaultFXCView.class + " instance");
			primaryViewOptional = Optional.empty();
		}
		else
		{
			primaryViewOptional = Optional.of(CDI.current().select(viewClassOptional.get()).get());
		}

		return primaryViewOptional;
	}

	/**
	 * @return <code>Optional</code> that contains a subclass of {@link DefaultFXCView} that was built following a
	 *         best effort approach complying to the naming conventions. If the best effort approach fails the returned
	 *         <code> Optional</code> contains a <code>null</code> value.
	 * @see    FXCApp#getClassNameView() for a description of the best effort approach
	 */
	@SuppressWarnings("unchecked")
	protected Optional<Class<? extends DefaultFXCView>> optionalViewClass()
	{
		Class<?> klass = null;
		final String viewClassName = getClassNameView();

		try
		{
			klass = Class.forName(viewClassName);

			if (klass.isAssignableFrom(DefaultFXCView.class))
			{
				log.error(klass.getName() + " is not a subclass of " + DefaultFXCView.class.getName());
				return Optional.empty();
			}
		}
		catch (final ClassNotFoundException e)
		{
			log.error("could not find " + DefaultFXCView.class.getName() + " class " + viewClassName, e);
			return Optional.empty();
		}

		return Optional.of((Class<? extends DefaultFXCView>) klass);
	}

	/**
	 * @return {@link DefaultFXCView} class name that by default is the same as to the name of the current
	 *         class except the trailing "App". This complies to the naming conventions.
	 */
	protected String getClassNameView()
	{
		String currentClassName = getClass().getName();
		return currentClassName.substring(0, currentClassName.length() - "App".length());
	}

	/** @return default stage style {@link StageStyle#DECORATED} */
	protected StageStyle getStageStyle() { return StageStyle.DECORATED; }

	/** @return default stage title (zero length <code>String</code>) */
	protected String getStageTitle() { return ""; }

	/** @return <code>Optional</code> containing default stage icon (<code>null</code>) */
	protected Optional<Image> getStageIcon () { return Optional.empty(); }

	private void onStageShowing()
	{
		log.debug("firing " + StageShowingEvent.class.getName());
		CDI.current().getBeanManager().getEvent().fire(new StageShowingEvent(this, primaryStage));
	}

	private void onApplicationStarted(final DefaultFXCView view)
	{
		log.debug("firing " + FXCAppStartedEvent.class.getName());
		CDI.current().getBeanManager().getEvent().fire(new FXCAppStartedEvent(this, view));
	}

	private void onApplicationStopped()
	{
		log.debug("firing " + FXCAppStoppedEvent.class.getName());
		CDI.current().getBeanManager().getEvent().fire(new FXCAppStoppedEvent(this));
	}
}