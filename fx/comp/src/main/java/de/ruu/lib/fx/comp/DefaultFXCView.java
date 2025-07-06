package de.ruu.lib.fx.comp;

import jakarta.enterprise.inject.spi.CDI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

import static de.ruu.lib.util.BooleanFunctions.not;
import static java.util.Objects.isNull;

/**
 * Abstract base class for JavaFX visual components (<code>FXComp</code> views).
 * <p>
 * Implementations of {@link DefaultFXCView}s can be run and tested as independent JavaFX applications using {@link
 * FXCApp} and {@link FXCAppRunner}.
 * <p>
 * To facilitate easy integration in large JavaFX applications {@link DefaultFXCView} provides:
 * <ul>
 *   <li><code>protected</code> access to the controller that defines the behavior of the view and implements the
 *       services of the component (see {@link FXCView#service()}).</li>
 *   <li><code>protected</code> access to various methods that allow to override default naming conventions if they do
 *       not fit the actual needs for a component.</li>
 *   <li>CDI support. This means that custom extensions of this class can be injected into other extensions of it.</li>
 *   <li>During bootstrapping {@link DefaultFXCView} instances will notify listeners to {@link FXComponentReadyEvent}s
 *       as soon as bootstrapping reaches its last step.</li>
 * </ul>
 *
 * @author r-uu
 */
@Slf4j
public abstract class DefaultFXCView<
		V extends FXCView<S>,
		S extends FXCService,
		C extends FXCController<V, S>> implements FXCView<S>
//public abstract class DefaultFXCView<
//		S extends FXCService> implements FXCView<S>
{
	private S service;
	private C controller;

	private Parent localRoot;
	private Scene  scene;
	private String fxmlResourceName;
	private String cssResourceName;

	/**
	 * Loads the component's tree of nodes from an <code>.fxml</code> file. It looks for the file by leveraging the <code>
	 * FXCView</code> default naming conventions (see {@link de.ruu.lib.fx.comp}) or the overridden return value from
	 * {@link #getFXMLResourceName()}
	 */
	@Override public @NonNull Parent localRoot()
	{
		if (not(isNull(localRoot))) return localRoot;

		final FXMLLoader fxmlLoader = createAndConfigureFXMLLoader();

		localRoot = FXMLUtil.loadFrom(fxmlLoader);

		return localRoot;
	}

	/**
	 * Returns the service of this component. The service is usually a controller that implements the {@link
	 * FXCService} interface.
	 * <p>
	 * If no local root is set yet, it will be created by calling {@link #localRoot()}. #localRoot() will also initialize
	 * the component's tree of nodes from an <code>.fxml</code> file
	 * <p>
	 * If no service is set yet, it will be created by calling {@link #serviceClass()} and using CDI to instantiate the
	 * service.
	 *
	 * @return the service of this component
	 */
	@Override public @NonNull S service()
	{
		if (isNull(localRoot)) localRoot();
		if (not(isNull(service))) return service;

		final Class<S> serviceClass = serviceClass();
		service = CDI.current().select(serviceClass).get();

		return service;
	}

	/**
	 * Returns the controller of this component. The controller is usually a class that implements the {@link
	 * FXCController} interface.
	 * <p>
	 * If no local root is set yet, it will be created by calling {@link #localRoot()}. #localRoot() will also initialize
	 * the component's tree of nodes from an <code>.fxml</code> file
	 * <p>
	 * If no controller is set yet, it will be created by calling {@link #controllerClass()} and using CDI to
	 * instantiate the controller. Immediately after instantiation, this view is passed to the controller via
	 * {@link FXCController#view(FXCView)}.
	 *
	 * @return the controller of this component
	 */
	protected C controller()
	{
		if (isNull(localRoot)) localRoot();
		if (not(isNull(controller))) return controller;

		final Class<C> controllerClass = controllerClass();

		if (not(isNull(controllerClass)))
		{
			// use CDI to instantiate controller
			controller = CDI.current().select(controllerClass).get();
			// pass this view to the controller
			controller.view((V) this); // safe cast: by contract, this is V, f-bounded polymorphism (Curiously Recurring Template Pattern)
		}
		else
		{
			throw new ExceptionInInitializerError("failure creating controller for " + getClass().getName());
		}

		return controller;
	}

	protected Scene scene()
	{
		if (not(isNull(scene))) { return scene; }
		scene = new Scene(localRoot());
		addStylesheet(scene);
		return scene;
	}

	protected void addStylesheet(final Scene scene)
	{
		final String resourceName = getCSSResourceName();
		final URL resource = getClass().getResource(resourceName);

		if (null == resource)
		{
			log.warn("no resource exists at " + resourceName);
			return;
		}

		final String externalForm = resource.toExternalForm();
		scene.getStylesheets().add(externalForm);
		log.debug("added stylesheet " + externalForm + " to " + getClass().getName() + " scene");
	}

	/** @return <code>.fxml</code> resource file name based on default naming convention */
	protected String getFXMLResourceName()
	{
		if (null == fxmlResourceName) { fxmlResourceName = getClass().getSimpleName() + ".fxml"; }
		return fxmlResourceName;
	}

	/** @return <code>.css</code> resource file name based on default naming convention */
	protected String getCSSResourceName()
	{
		if (null == cssResourceName) { cssResourceName = getClass().getSimpleName() + ".css"; }
		return cssResourceName;
	}

	@SuppressWarnings("unchecked")
	protected Class<S> serviceClass()
	{
		Class<S>     klass            = null;
		final String serviceClassName = serviceClassName();

		try
		{
			klass = (Class<S>) Class.forName(serviceClassName);
			if (not(FXCService.class.isAssignableFrom(klass)))
			{
				throw new UnsupportedOperationException(klass.getName() + " is not a " + FXCService.class.getName());
			}
		}
		catch (final ClassNotFoundException e)
		{
			throw new IllegalStateException("could not find class " + serviceClassName, e);
		}

		return klass;
	}

	@SuppressWarnings("unchecked")
	protected Class<C> controllerClass()
	{
		Class<C> klass = null;
		final String controllerClassName = controllerClassName();

		try
		{
			klass = (Class<C>) Class.forName(controllerClassName);
		}
		catch (final ClassNotFoundException e)
		{
			throw new IllegalStateException("could not find class " + controllerClassName, e);
		}

		return klass;
	}

	/**
	 * @return {@link FXCService} class name that by default is the same as to the name of the current
	 *         class plus a trailing "Service". This complies to the naming conventions.
	 */
	protected String serviceClassName() { return getClass().getName() + "Service"; }

	/**
	 * @return {@link FXCController} class name that by default is the same as to the name of the current
	 *         class plus a trailing "Controller". This complies to the naming conventions.
	 */
	protected String controllerClassName() { return getClass().getName() + "Controller"; }

	/**
	 * @return {@link FXCApp} class name that by default is the same as to the name of the current
	 *         class plus a trailing "App". This complies to the naming conventions.
	 */
//	protected String classNameApp() { return getClass().getName() + "App"; }

	/**
	 * Creates a new {@link FXMLLoader} instance that is configured to load the component's <code>.fxml</code> file.
	 * <p>
	 * The <code>.fxml</code> file is looked up by leveraging the <code>FXCView</code> default naming conventions (see
	 * {@link de.ruu.lib.fx.comp}) or the overridden return value from {@link #getFXMLResourceName()}.
	 * <p>
	 * In addition, the controller of the component is set on the {@link FXMLLoader} if it is available.
	 *
	 * @return a new {@link FXMLLoader} instance
	 */
	private FXMLLoader createAndConfigureFXMLLoader()
	{
		final FXMLLoader fxmlLoader = new FXMLLoader();

		// configure fxmlLoader
		//   find and set location
		final String fxmlResourceName = getFXMLResourceName();
		final URL    fxmlLocation     = getClass().getResource(fxmlResourceName);

		if (fxmlLocation == null)
		{
			log.error(
					"no resource exists for {} at location {}, does module {} export _and_ open package {}?",
					getClass().getName(),
					fxmlResourceName,
					getClass().getModule().getName(),
					getClass().getPackage().getName());
		}
		else
		{
			log.debug(
					"configured {} to load fxml from {}",
					FXMLLoader.class.getName(),
					fxmlLocation.toExternalForm());
			fxmlLoader.setLocation(fxmlLocation);
		}

		final Object controllerFromFXML = fxmlLoader.getController();

		if (controllerFromFXML != null)
		{
			log.warn("found {} controller, controller might be replaced", controllerFromFXML.getClass().getName());
		}

		//   set controller
		fxmlLoader.setController(controller());

		return fxmlLoader;
	}
}