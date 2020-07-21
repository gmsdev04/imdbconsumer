package com.github.gmsdeveloper04.imdbconsumer.di;

import com.github.gmsdeveloper04.imdbconsumer.multithreadserver.MultiThreadServerFactory;
import com.github.gmsdeveloper04.imdbconsumer.multithreadserver.impl.MultiThreadServer;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class DependencyInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		binder().install(new FactoryModuleBuilder()
				.implement(com.github.gmsdeveloper04.imdbconsumer.multithreadserver.MultiThreadServer.class,MultiThreadServer.class)
				.build(MultiThreadServerFactory.class));
	}


}
