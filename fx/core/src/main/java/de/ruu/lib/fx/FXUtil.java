package de.ruu.lib.fx;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

@Slf4j
public abstract class FXUtil
{
	public static Optional<Stage> getStage(final Node node)
	{
		final Scene scene = node.getScene();

		if (scene == null) return Optional.empty();

		final Window window = scene.getWindow();

		if (window instanceof Stage)
		{
			return Optional.of((Stage) window);
		}

		return Optional.empty();
	}

	public static <T> void bindSetToList(final ObservableSet<T> set, final ObservableList<T> list)
	{
		list.setAll(set);
		set.addListener
		(
				(SetChangeListener<T>) change ->
				{
					if      (change.wasAdded())   list.add(   change.getElementAdded());
					else if (change.wasRemoved()) list.remove(change.getElementRemoved());
				}
		);
	}

	public static <T> void bindListToList(final ObservableList<T> listSource, final ObservableList<T> listTarget)
	{
		listTarget.setAll(listSource);
		listSource.addListener
		(
				(ListChangeListener<T>) change ->
				{
					if      (change.wasAdded())   listTarget.addAll(   change.getAddedSubList());
					else if (change.wasRemoved()) listTarget.removeAll(change.getRemoved());
				}
		);
	}

	public static HostServices getHostServices()
	{
		final Application application =
				new Application()
				{
					@Override public void start(final Stage primaryStage) throws Exception { throw new RuntimeException("never start this application"); }
				};
		return application.getHostServices();
	}

	public static Optional<Region> asRegion(final Parent parent)
	{
		if (parent instanceof Region) return Optional.of((Region) parent);
		return Optional.empty();
	}

	public static void bindSizeOfRegionToParent(final Region region, final Parent parent)
	{
		final Optional<Region> regionOfParent = asRegion(parent);

		if (regionOfParent.isPresent())
		{
			region.prefWidthProperty ().bind(regionOfParent.get().widthProperty ());
			region.prefHeightProperty().bind(regionOfParent.get().heightProperty());
		}
		else
		{
			log.warn("could not obtain parent as region");
		}
	}

	public static void setAnchorsInAnchorPaneTo(final Node childInAnchorPane, final double value)
	{
		AnchorPane.setTopAnchor   (childInAnchorPane, value);
		AnchorPane.setRightAnchor (childInAnchorPane, value);
		AnchorPane.setBottomAnchor(childInAnchorPane, value);
		AnchorPane.setLeftAnchor  (childInAnchorPane, value);
	}

	public static Parent loadFrom(final FXMLLoader loader)
	{
		// load from fxmlLoader
		try
		{
			final Object object = loader.load();
			log.debug("loaded object from " + loader.getLocation().toExternalForm());

			if (object instanceof Parent)
			{
				log.debug("loaded " + Parent.class.getName() + " object from " + loader.getLocation().toExternalForm());
				return (Parent) object;
			}
			else
			{
				throw new IllegalStateException(
						"unexpected " + object.getClass() + " loaded from " + loader.getLocation().toExternalForm());
			}
		}
		catch (final IOException e)
		{
			throw new IllegalStateException("failure loading object from " + loader.getClass(), e);
		}
	}

	public static void setChildrenInTreeEditable(final Parent root, final boolean editable)
	{
		for (final Node node : root.getChildrenUnmodifiable())
		{
			if (node instanceof TextInputControl)
			{
				((TextInputControl) node).setEditable(editable);
			}
			else if (node instanceof Parent)
			{
				setChildrenInTreeEditable((Parent) node, editable);
			}
		}
	}

	public static StackPane createTitledBorder(String title, Region content)
	{
		// set a border on the content region
		content.setBorder(
				new Border(
						new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(1))));
		content.setPadding(new Insets(10, 5, 5, 5));

		// label as the title
		Label label = new Label(" " + title + " ");

		label.setBackground(
				new Background(
						new BackgroundFill(Color.web("#f4f4f4"), CornerRadii.EMPTY, Insets.EMPTY)));
		label.setFont(Font.font("System", FontWeight.NORMAL, 12));
		label.setTranslateY(-8); // exakt wie in CSS
		label.setTranslateX( 8);

//		label.setStyle("-fx-background-color: white; -fx-translate-y: -8; -fx-translate-x: 8;");

		// stack everything
		StackPane wrapper = new StackPane();
		wrapper.getChildren().addAll(content, label);

		// proper positioning
		StackPane.setAlignment(label, Pos.TOP_LEFT);

		return wrapper;
	}

	/**
	 * Aktualisiert nur den BorderStrokeStyle einer Region,
	 * alle anderen Border-Eigenschaften bleiben erhalten.
	 *
	 * @param region Die JavaFX-Region (z. B. Pane, StackPane, VBox, ...)
	 * @param newStyle Der gewünschte neue BorderStrokeStyle (z. B. DASHED, DOTTED, etc.)
	 */
	public static void updateBorderStrokeStyle(Region region, BorderStrokeStyle newStyle)
	{
		Border currentBorder = region.getBorder();

		if (currentBorder == null || currentBorder.getStrokes().isEmpty()) { return; }  // Nichts zu ändern

		BorderStroke oldStroke = currentBorder.getStrokes().get(0);

		// Neuer Stroke mit gleichem Setup, aber geändertem Stil
		BorderStroke newStroke =
				new BorderStroke(
						oldStroke.getTopStroke(),
						newStyle,
						oldStroke.getRadii(),
						oldStroke.getWidths(),
						oldStroke.getInsets());

		region.setBorder(new Border(newStroke));
	}

	/**
	 * Aktualisiert nur den BorderStrokeWidth einer Region, alle anderen Border-Eigenschaften bleiben erhalten.
	 *
	 * @param region Die JavaFX-Region (z. B. Pane, StackPane, VBox, ...)
	 * @param newBorderWidth Der gewünschte neue BorderWidths (z. B. new BorderWidths(2))
	 */
	public static void updateBorderWidth(Region region, double newBorderWidth)
	{
		Border currentBorder = region.getBorder();

		if (currentBorder == null || currentBorder.getStrokes().isEmpty()) { return; }  // Nichts zu ändern

		BorderStroke      oldBorderStroke      = currentBorder.getStrokes().get(0);
		BorderStrokeStyle oldBorderStrokeStyle =
				oldBorderStroke.isStrokeUniform() ? oldBorderStroke.getTopStyle() : BorderStrokeStyle.SOLID;

		// Neuer Stroke mit gleichem Setup, aber geändertem BorderWidth
		BorderStroke newStroke =
				new BorderStroke(
						oldBorderStroke.getTopStroke(),
						oldBorderStrokeStyle,
						oldBorderStroke.getRadii(),
						new BorderWidths(newBorderWidth),
						oldBorderStroke.getInsets());

		region.setBorder(new Border(newStroke));
	}

	/**
	 * moves children of sourcePane into targetPane and returns targetPane
	 *
	 * @param sourcePane pane the children will be moved from
	 * @param targetPane pane the children will be moved to
	 * @param <SOURCE_PANE> the type of the source pane
	 * @param <TARGET_PANE> the type of the target pane
	 * @return targetPane, which is the wrapping pane that now contains the children of sourcePane
	 */
	public static <SOURCE_PANE extends Pane, TARGET_PANE extends Pane> TARGET_PANE moveChildrenOfSourceIntoTarget(
			@NonNull SOURCE_PANE sourcePane, @NonNull TARGET_PANE targetPane)
	{
		// make sure that children are copied into a new array list, because the source pane will be cleared
		List<Node> children = new ArrayList<>(sourcePane.getChildren()); // store              children of   source pane
		sourcePane.getChildren().clear();                                // remove             children from source pane
		targetPane.getChildren().addAll(children);                       // restore removed    children in   target pane
		sourcePane.getChildren().add   (targetPane);                     // add target pane to children of   source pane
		return targetPane;                                               // return target pane, which is now the wrapping pane
	}

	// TODO also check suggestions made here:
	//      https://stackoverflow.com/questions/46299084/how-to-set-position-of-a-node-relative-to-another-node-in-javafx
	public static void showPopupBelowNode(final Node node, final Popup popup)
	{
		if (isNull(node.getScene()))
		{
			// if node is not on the scene no popup is to be shown
			return;
		}

		final Window window = node.getScene().getWindow();

		double x = window.getX() + node.localToScene(0, 0).getX() + node.getScene().getX();
		double y = window.getY() + node.localToScene(0, 0).getY() + node.getScene().getY() +
		           node.getBoundsInParent().getHeight();

		popup.show(window, x, y);

		if (!popup.getContent().isEmpty())
		{
			final Node content = popup.getContent().get(0);
			x -= content.localToScene(0, 0).getX();
			y -= content.localToScene(0, 0).getY();
		}

		popup.show(window, x, y);
	}

	// TODO also check suggestions made here:
	//      https://stackoverflow.com/questions/46299084/how-to-set-position-of-a-node-relative-to-another-node-in-javafx
	public static void showPopupAboveNode(final Node node, final Popup popup)
	{
		if (isNull(node.getScene()))
		{
			// if node is not on the scene no popup is to be shown
			return;
		}

		final Window window = node.getScene().getWindow();

		double x = window.getX() + node.localToScene(0, 0).getX() + node.getScene().getX();
		double y = window.getY() + node.localToScene(0, 0).getY() + node.getScene().getY() -
		           node.getBoundsInParent().getHeight() -
		           popup.getHeight();

		popup.show(window, x, y);

		if (!popup.getContent().isEmpty())
		{
			final Node content = popup.getContent().get(0);
			x -= content.localToScene(0, 0).getX();
			y -= content.localToScene(0, 0).getY();
		}

		popup.show(window, x, y);
	}

	public static String toRGBCode(final Color color)
	{
		return
				String.format
				(
						"#%02X%02X%02X",
						(int) (color.getRed  () * 255),
						(int) (color.getGreen() * 255),
						(int) (color.getBlue () * 255)
				);
	}

	public static void setStyleToChildren(Parent parent, String style, Predicate<Node> predicate)
	{
		for (Node child : parent.getChildrenUnmodifiable())
		{
			if (predicate.test(child)) child.setStyle(style);
			if (child instanceof Parent) setStyleToChildren((Parent) child, style, predicate);
		}
	}

	public static void setTextFillToChildren(Parent parent, Paint paint, Predicate<Node> predicate)
	{
		for (Node child : parent.getChildrenUnmodifiable())
		{
			if (child instanceof Labeled)
			{
				if (predicate.test(child)) ((Labeled) child).setTextFill(paint);
			}
			if (child instanceof Parent) setTextFillToChildren((Parent) child, paint, predicate);
		}
	}
}