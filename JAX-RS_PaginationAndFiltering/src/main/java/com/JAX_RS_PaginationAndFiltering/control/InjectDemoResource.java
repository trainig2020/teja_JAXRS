package com.JAX_RS_PaginationAndFiltering.control;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("/annotation")
	public String getParamUsingAnnoatation(@MatrixParam("param") String matrixParam,
										@HeaderParam("customHeader") String header,
										@CookieParam("name") String cookie) {
		return "Param is : "+ matrixParam+" ; Custom Header value : "+header
				+" ; Cookie is : "+cookie;
	}
	@GET
	@Path("/context")
	public String getParamUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders headers) {
		String path=uriInfo.getAbsolutePath().toString();
	String cookies=	headers.getCookies().toString();
		return "Path: " +path+"Cookies "+cookies;
	}

}
