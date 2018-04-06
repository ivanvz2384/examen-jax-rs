package com.gapsi.examenjaxrs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ArticuloAlreadyCreatedException extends RuntimeException
		implements ExceptionMapper<ArticuloNotFoundException> {

	private static final long serialVersionUID = 1906002969303089401L;

	public ArticuloAlreadyCreatedException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(ArticuloNotFoundException arg0) {
		 return Response.status(408).entity(arg0.getMessage())
                 .type("text/plain").build();
	}

}
