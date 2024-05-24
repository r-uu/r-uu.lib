package de.ruu.lib.fx.control.autocomplete.textfield;

import de.ruu.lib.fx.control.autocomplete.textfield.AutoCompleteTextField.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.util.Objects.isNull;

public class AutoCompleteTextFieldBuilder<T>
{
	private final ObservableList<T        > DEFAULT_ITEMS             = FXCollections.observableArrayList(new ArrayList<>());
	private final BiPredicate   <T, String> DEFAULT_SUGGESTION_FILTER = (t, string) -> t.toString().equals(string);
	private final BiPredicate   <T, String> DEFAULT_CONVERTER_TEST    = (t, string) -> t.toString().equals(string);
	private final Function      <T, Node  > DEFAULT_GRAPHICS_PROVIDER = t -> null;
	private final Function      <T, String> DEFAULT_TEXT_PROVIDER     = t -> t.toString();
	private final Function      <T, String> DEFAULT_TOOLTIP_PROVIDER  = t -> t.toString();
	private final String                    DEFAULT_PROMPT            = "";
	private final Position                  DEFAULT_POSITION          = Position.BELOW;

	private ObservableList<T        > items;
	private BiPredicate   <T, String> suggestionFilter;
	private BiPredicate   <T, String> converterTest;
	private Function      <T, Node  > graphicsProvider;
	private Function      <T, String> textProvider;
	private Function      <T, String> toolTipProvider;
	private String                    prompt;
	private Position                  position;

	public AutoCompleteTextFieldBuilder()
	{
		items            = DEFAULT_ITEMS;
		suggestionFilter = DEFAULT_SUGGESTION_FILTER;
		converterTest    = DEFAULT_CONVERTER_TEST;
		graphicsProvider = DEFAULT_GRAPHICS_PROVIDER;
		textProvider     = DEFAULT_TEXT_PROVIDER;
		toolTipProvider  = DEFAULT_TOOLTIP_PROVIDER;
		prompt           = DEFAULT_PROMPT;
		position         = DEFAULT_POSITION;
	}

	public static <T> AutoCompleteTextFieldBuilder<T> create() { return new AutoCompleteTextFieldBuilder<>(); }

	public AutoCompleteTextFieldBuilder<T> items(final List<T> items)
	{
		if (isNull(items))            this.items            = DEFAULT_ITEMS;
		else                          this.items            = FXCollections.observableArrayList(items);
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> suggestionFilter(final BiPredicate<T, String> suggestionFilter)
	{
		if (isNull(suggestionFilter)) this.suggestionFilter = DEFAULT_SUGGESTION_FILTER;
		else                          this.suggestionFilter = suggestionFilter;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> converterTest   (final BiPredicate<T, String> converterTest)
	{
		if (isNull(converterTest))    this.converterTest    = DEFAULT_CONVERTER_TEST;
		else                          this.converterTest    = converterTest;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> graphicsProvider(final Function<T, Node> graphicsProvider)
	{
		if (isNull(graphicsProvider)) this.graphicsProvider = DEFAULT_GRAPHICS_PROVIDER;
		else                          this.graphicsProvider = graphicsProvider;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> textProvider(final Function<T, String> textProvider)
	{
		if (isNull(textProvider))     this.textProvider     = DEFAULT_TEXT_PROVIDER;
		else                          this.textProvider     = textProvider;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> toolTipProvider(final Function<T, String> toolTipProvider)
	{
		if (isNull(toolTipProvider)) this.toolTipProvider   = DEFAULT_TOOLTIP_PROVIDER;
		else                         this.toolTipProvider   = toolTipProvider;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> prompt(final String prompt)
	{
		if (isNull(prompt))          this.prompt            = DEFAULT_PROMPT;
		else                         this.prompt            = prompt;
		return this;
	}

	public AutoCompleteTextFieldBuilder<T> position(final Position position)
	{
		if (isNull(position))        this.position          = DEFAULT_POSITION;
		else                         this.position          = position;
		return this;
	}

	public AutoCompleteTextField<T> build()
	{
		return
				new AutoCompleteTextField<>
				(
						items,
						suggestionFilter,
						converterTest,
						graphicsProvider,
						textProvider,
						toolTipProvider,
						prompt,
						position
				);
	}
}