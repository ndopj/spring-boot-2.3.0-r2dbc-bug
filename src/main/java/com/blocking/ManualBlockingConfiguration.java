package com.blocking;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
@EnableJpaRepositories(basePackages = {"com.blocking"},
        entityManagerFactoryRef = "blockingEntityManagerFactory",
        transactionManagerRef = "blockingTransactionManager")
public class ManualBlockingConfiguration {

    @Primary
    @Bean(name = "blockingDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "blockingDataSource")
    @ConfigurationProperties("spring.datasource.configuration")
    public DataSource dataSource(@Qualifier("blockingDataSourceProperties")
                                         DataSourceProperties blockingDataSourceProperties) {
        LoggerFactory.getLogger(this.getClass()).info("[blocking data config] blocking datasource init");
        return blockingDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "blockingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("blockingDataSource") DataSource blockingDataSource) {
        LoggerFactory.getLogger(this.getClass()).info("[blocking data config] blocking entity manager factory init");
        return builder
                .dataSource(blockingDataSource)
                .packages(BlockingModel.class)
                .persistenceUnit("blocking")
                .properties(additionalProperties())
                .build();
    }

    @Primary
    @Bean(name = "blockingTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("blockingEntityManagerFactory")
                    EntityManagerFactory blockingEntityManagerFactory) {
        LoggerFactory.getLogger(this.getClass()).info("[blocking data config] blocking transaction manager factory init");
        return new JpaTransactionManager(blockingEntityManagerFactory);
    }

    Map<String, Object> additionalProperties() {
        return new HashMap<>() {{
            put("hibernate.hbm2ddl.auto", "create-drop");
            put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        }};
    }
}
