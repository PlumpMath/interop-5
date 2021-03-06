package br.jus.tjmt.interop.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import br.jus.tjmt.interop.model.Airport;
import br.jus.tjmt.interop.model.AirportDAO;

/**
 * Sample Airport REST API
 */
@Path("/airports")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirportAPI {

	@Inject
	AirportDAO dao;

	@Inject
	Logger log;

	@GET
	public List<Airport> list() {
		log.debug("listing all airports");
		return dao.getAll();
	}

	@GET
	@Path("/search/{str}")
	public List<Airport> list(@PathParam("str") String str) {
		log.debug("search airports for string %" + str + "%");
		return dao.getByString(str);
	}

	@GET
	@Path("/{id}")
	public Airport get(@PathParam("id") String id) {
		log.debug("search airports for id " + id);
		if (Utils.isNumber(id)) {
			return dao.getOne(Long.valueOf(id));
		} else {
			return dao.getOne(id);
		}
	}

	@POST
	public Airport save(Airport airport) {
		if (airport != null && airport.getId() != null) {
			log.debug("updating airport for id " + airport.getId());
			return dao.update(airport);
		} else {
			log.debug("inserting airport for code " + airport.getCode());
			return dao.insert(airport);
		}
	}

	@PUT
	public Airport update(Airport airport) {
		log.debug("updating airport for id " + airport.getId());
		return dao.update(airport);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		log.debug("removing airport for id " + id);
		dao.remove(id);
	}

}
