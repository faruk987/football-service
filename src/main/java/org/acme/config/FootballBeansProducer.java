package org.acme.config;

import com.orbitz.consul.Consul;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class FootballBeansProducer {

	@Produces
	Consul consulClient = Consul.builder().build();

}
