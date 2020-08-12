package com.JAX_RS_PaginationAndFiltering.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.JAX_RS_PaginationAndFiltering.model.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage=new ErrorMessage(ex.getMessage(),404,"http://www.google.com");
		return Response.status(Status.NOT_FOUND).
				entity(errorMessage).build();
	}

}
