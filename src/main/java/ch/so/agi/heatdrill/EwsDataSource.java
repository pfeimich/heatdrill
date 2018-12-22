package ch.so.agi.heatdrill;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.server.ResponseStatusException;


@Configuration
@ConfigurationProperties(prefix = "db-connection")
public class EwsDataSource {

	private String url;
	private String userName;
	private String password;
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    @Bean
    public DataSource postgresDataSource() {
    	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();    	
    	
    	String driverClassName = "org.postgresql.Driver";
    	Driver driver = null;
        try {
            driver = (Driver)Class.forName(driverClassName).newInstance();
        }
        catch(Exception e){
            throw new RuntimeException("Could not find and load jdbc Driver Class " + driverClassName, e);
        }
    	
        dataSource.setDriver(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
 
        return dataSource;
    }
}
