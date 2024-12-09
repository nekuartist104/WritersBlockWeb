package moveslikejagger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

//http://localhost:8080/swagger-ui/index.html
//http://localhost:8080/api-docs
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${server}")
    private String server;

    @Value("${database}")
    private String database;

    @Value("${dbUsername}")
    private String username;

    @Value("${dbPassword}")
    private String password;

    @Bean
    public DataSource dataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(server);
        dataSource.setDatabaseName(database);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setTrustServerCertificate(true);
        return dataSource;
    }
}
