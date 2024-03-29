package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Cliente;
import conex.ConnectionPool;

public class ClientesDAO {
	
	
	
	
	public static Cliente buscaCliente(String nom, String pass) {
		Cliente cliente = null;
		String sql = "Select * from clientes where nombre = ? and password = ?";
		try {
			Connection con = ConnectionPool.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nom);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				cliente = new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("password"),
						rs.getString("domicilio"), rs.getString("codigopostal"), rs.getString("telefono"),
						rs.getString("email"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return cliente;

	}

	public static boolean buscaCliente(String nom) {
		String sql = "SELECT * FROM clientes where nombre = ?";
		try {
			Connection con = ConnectionPool.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, nom);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return false;
	}
	
	public static boolean guardarCliente(Cliente cliente)
	{        
        int id = -1;
        String sql = "INSERT INTO clientes(id, nombre, password, domicilio, codigopostal, telefono, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = ConnectionPool.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, ClavesDAO.siguienteId("clientes"));
            st.setString(2, cliente.getNombre());
            st.setString(3, cliente.getPassword());
            st.setString(4, cliente.getDomicilio());
            st.setString(5, cliente.getCodigopostal());
            st.setString(6, cliente.getTelefono());
            st.setString(7, cliente.getEmail());
            
            st.execute();
            
            
            st.close();
            con.close();
            return true;
        } catch (SQLException ex) {
        	System.err.println(ex.getMessage());
        }
	        
		return false;
	}
	
	public static boolean actualizarCliente(Cliente cliente)
	{
		return true;
	}
}
