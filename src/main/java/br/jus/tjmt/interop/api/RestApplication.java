package br.jus.tjmt.interop.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * REST Application
 */
@ApplicationPath("/api")
public class RestApplication extends Application {

	private Set<Object> singletons = new HashSet<>();
	private Set<Class<?>> classes = new HashSet<>();

	public RestApplication(){
		classes.add(AirportAPI.class);
	}
	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
