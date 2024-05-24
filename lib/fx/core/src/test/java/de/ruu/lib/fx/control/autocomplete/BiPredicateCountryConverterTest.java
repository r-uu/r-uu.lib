package de.ruu.lib.fx.control.autocomplete;

import static de.ruu.lib.fx.control.autocomplete.BiPredicateCountryConverter.BIPREDICATE_COUNTRY_CONVERTER;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BiPredicateCountryConverterTest
{
	@Test public void test()
	{
		final Country country = Country.countries().get(0);
		assertTrue(BIPREDICATE_COUNTRY_CONVERTER.test(country, country.getName()));
	}
}