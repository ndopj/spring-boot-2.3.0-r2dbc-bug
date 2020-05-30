package com.reactive;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.connectionfactory.init.CompositeDatabasePopulator;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
@EnableR2dbcRepositories(basePackages = {"com.reactive"})
public class ManualReactiveConfiguration extends AbstractR2dbcConfiguration {

    @Override
    @Primary
    @Bean("reactiveConnectionFactory")
    public ConnectionFactory connectionFactory() {
        LoggerFactory.getLogger(this.getClass()).info("[reactive data config] Reactive connection factory init");
        return H2ConnectionFactory.inMemory("testDB");
    }

    @Primary
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory reactiveConnectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(reactiveConnectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        initializer.setDatabasePopulator(populator);
        LoggerFactory.getLogger(this.getClass())
                .info("[reactive data config] Reactive connection factory initializer init");
        return initializer;
    }

    @Primary
    @Bean("reactiveTransactionManager")
    ReactiveTransactionManager transactionManager(ConnectionFactory reactiveConnectionFactory) {
        LoggerFactory.getLogger(this.getClass()).info("[reactive data config] reactive transaction manager init");
        return new R2dbcTransactionManager(reactiveConnectionFactory);
    }
}
