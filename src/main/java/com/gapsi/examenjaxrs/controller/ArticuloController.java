package com.gapsi.examenjaxrs.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gapsi.examenjaxrs.exception.ArticuloAlreadyCreatedException;
import com.gapsi.examenjaxrs.exception.ArticuloNotFoundException;
import com.gapsi.examenjaxrs.model.Articulo;
import com.gapsi.examenjaxrs.repository.ArticuloRepository;

@Path("/v2/articulos")
@Component
public class ArticuloController {

	@Autowired
	private ArticuloRepository repository;

	@GET
	@Produces("application/json")
	public Iterable<Articulo> obtenerArticulos() {
		return repository.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response obtenerArticuloById(@PathParam("id") String id) throws URISyntaxException, ArticuloNotFoundException {
		Optional<Articulo> optionalArticulo = repository.findById(id);
		if (!optionalArticulo.isPresent()) {
			throw new ArticuloNotFoundException(String.format("El articulo con id %s no existe", id));
		}

		return Response.status(200).entity(optionalArticulo.get())
				.contentLocation(new URI("/" + optionalArticulo.get().getId())).build();

	}

	@POST
	@Consumes("application/json")
	public Response crearArticulo(@Valid Articulo articulo) throws URISyntaxException {
		boolean existsById = repository.existsById(articulo.getId());
		if (existsById) {
			throw new ArticuloAlreadyCreatedException(
					String.format("El articulo con id %s ya ha sido creado anteriormente", articulo.getId()));
		}
		Articulo articuloGuardado = repository.save(articulo);
		return Response.status(201).contentLocation(new URI("/" + articuloGuardado.getId())).build();

	}
	
	@PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
	public Response actualizarArticulo(@PathParam("id") String id, @Valid Articulo articulo) throws URISyntaxException {
		Optional<Articulo> optionalArticulo = repository.findById(id);
		if (!optionalArticulo.isPresent()) {
			throw new ArticuloNotFoundException(String.format("El articulo con id %s no existe", id));
		}
		
		Articulo articuloAGuardar =  optionalArticulo.get();
		articuloAGuardar.setDescripcion(articulo.getDescripcion());
		articuloAGuardar.setModelo(articulo.getModelo());
		repository.save(articuloAGuardar);
		return Response.status(200).entity(articuloAGuardar).build();
	}

}
