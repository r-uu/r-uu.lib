module de.ruu.util.config.mp
{
	exports de.ruu.util.config.mp;

	requires microprofile.config.api;
	requires jakarta.el;

	requires static lombok;
	requires jakarta.cdi;
}