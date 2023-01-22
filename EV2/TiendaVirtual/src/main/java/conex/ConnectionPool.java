package conex;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;


public class ConnectionPool {
	private static final BasicDataSource dataSource = new BasicDataSource();
	

    
    static {
    	 dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    	 dataSource.setUrl("jdbc:mysql://localhost/tiendavirtual");
    	 dataSource.setUsername("root");
    	 dataSource.setPassword("");

    }
	public static Connection getConnection () throws SQLException {
		return dataSource.getConnection();
	}
}
