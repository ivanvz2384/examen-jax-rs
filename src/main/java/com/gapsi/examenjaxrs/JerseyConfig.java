package com.gapsi.examenjaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig  extends ResourceConfig{

	public JerseyConfig() {
		packages("com.gapsi.examenjaxrs.controller");
	}
}
