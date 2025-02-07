package de.ruu.lib.util.rs.filter.logging;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import static de.ruu.lib.util.BooleanFunctions.not;
import static de.ruu.lib.util.StringBuilders.sb;

@Provider
@Slf4j
public class ContainerLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter
{
	@Override public void filter(ContainerRequestContext requestContext) throws IOException
	{
		StringBuilder result =
				sb("\nrequest uriInfo.absolutePath=" + requestContext.getUriInfo().getAbsolutePath().toString());

		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		for (String key : headers.keySet())
		{
			result.append("\nheaders - key: " + key+ ", value: " + headers.get(key));
		}

		log.debug(result.toString());
	}

	@Override public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException
	{
		StringBuilder result = sb("\nresponse");

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		if (not(headers.isEmpty()))     result.append("\n" + Util.toString(headers));

//		if (requestContext.hasEntity()) result.append("entity=" + responseContext.getEntity().toString());

		log.debug(result.toString());
	}
}