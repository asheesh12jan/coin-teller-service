package com.asheesh.cointellerservice;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Autowired
    private AppProps appProps;

    @Bean(name = "h2DataSource")
    public DataSource h2DataSource()
    {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setConnectionTestQuery("VALUES 1");
        config.addDataSourceProperty("URL", "jdbc:h2:~/test");
        config.addDataSourceProperty("user", "sa");
        config.addDataSourceProperty("password", "sa");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }


    @Bean(name = "postgresDataSource")
    @Primary
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(appProps.getPostgresUrl());
        config.setUsername(appProps.getPostgresUserName());
        config.setPassword(appProps.getPostgresPassword());
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }


}
