package com.github.gmsdeveloper04.imdbconsumer.dependencyinjection;

import com.github.gmsdeveloper04.imdbconsumer.imdbadapter.ImdbAdapter;
import com.github.gmsdeveloper04.imdbconsumer.imdbadapter.impl.ImdbHttpAdapter;
import com.github.gmsdeveloper04.imdbconsumer.multithreadserver.ImdbStreamProcessorFactory;
import com.github.gmsdeveloper04.imdbconsumer.multithreadserver.impl.ImdbStreamProcessor;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class DependencyInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ImdbAdapter.class).to(ImdbHttpAdapter.class);
		bind(Gson.class).asEagerSingleton();
		binder().install(new FactoryModuleBuilder()
				.implement(com.github.gmsdeveloper04.imdbconsumer.multithreadserver.ImdbStreamProcessor.class,ImdbStreamProcessor.class)
				.build(ImdbStreamProcessorFactory.class));
	}


}
