module de.ruu.lib.jackson
{
	exports de.ruu.lib.jackson;

	requires jakarta.ws.rs;

	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.databind;

	requires static lombok;
	requires org.slf4j;
}