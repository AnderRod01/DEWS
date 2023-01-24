package conex;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConexPoolBD {
	private static final BasicDataSource dataSource = new BasicDataSource();
	
	 static {
    	 dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    	 dataSource.setUrl("jdbc:mysql://localhost/bdactividad");
    	 dataSource.setUsername("root");
    	 dataSource.setPassword("");

    }
	public static Connection getConnection () throws SQLException {
//		InitialContext ctx = new InitialContext();
//		DataSource ds = (DataSource) ctx.lookup("jdbc/poolActividadesDB");
//		return ds.getConnection();
		return dataSource.getConnection();
	}
	
}
