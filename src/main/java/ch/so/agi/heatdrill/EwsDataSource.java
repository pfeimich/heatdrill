package ch.so.agi.heatdrill;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;


@Configuration
@ComponentScan("org.baeldung.jdbc")
public class EwsDataSource {
    @Bean
    public DataSource postgresDataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();    	
    	
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/dev");
        dataSource.setUsername("dmluser");
        dataSource.setPassword("dmluser");
 
        return dataSource;
    }
}
