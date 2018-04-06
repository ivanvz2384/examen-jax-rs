package com.gapsi.examenjaxrs.exception;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.gapsi.examenjaxrs.model.ExceptionResponse;

@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<ArticuloNotFoundException> {

	@Override
	public Response toResponse(ArticuloNotFoundException arg0) {
		ExceptionResponse ex = new ExceptionResponse(new Date(), "No encontrado", arg0.getMessage());
		return Response.status(404).entity(ex).build();
	}

}
