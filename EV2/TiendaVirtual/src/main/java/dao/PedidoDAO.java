package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import beans.Item;
import beans.LineaPedido;
import beans.Pedido;
import conex.ConnectionPool;

public class PedidoDAO {
	
	public static Map<Integer, Item> todosItems() {

		Map<Integer, Item> items = new HashMap<Integer, Item>();

		String sql = "SELECT * FROM items";

		try {
			Connection con = ConnectionPool.getConnection();
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
				items.put(item.getId(), item);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		return items;
	}
	
	public Item buscaItemPorId (int idItem) {
		String sql = "SELECT * FROM items where id=?";
        try {
        	Connection con = ConnectionPool.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idItem);
            ResultSet rs = st.executeQuery();
            
            if(rs.next())
            	return new Item(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return null;
	}
	
	public void guardaPedido (Pedido p) {

		String sql = "INSERT INTO pedidos (id, total, fecha, idcliente) VALUES (?, ?, ?, ?)";
		 try {
	            Connection con = ConnectionPool.getConnection();
	            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            st.setInt(1, ClavesDAO.siguienteId("pedidos"));
	            st.setDouble(2, p.getImporteTotal());
	            st.setDate(3, (Date) p.getFecha());
	            st.setInt(4, p.getId());

	            st.executeUpdate();
	            

	          
	            st.close();
	            con.close();
	        } catch (SQLException ex) {
	        	System.err.println(ex.getMessage());
	        }
		       
				
	}
	
	public void guardaLineaPedido (LineaPedido lp) {

		String sql = "INSERT INTO lineaspedido (id, cantidad, idpedido, iditem) VALUES (?, ?, ?, ?)";
		 try {
	            Connection con = ConnectionPool.getConnection();
	            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            st.setInt(1, ClavesDAO.siguienteId("lineaspedido"));
	            st.setInt(2, lp.getCantidad());
	            st.setInt(3, lp.getPedido().getId());
	            st.setInt(4, lp.getItem().getId());

	            st.executeUpdate();
	            

	          
	            st.close();
	            con.close();
	        } catch (SQLException ex) {
	        	System.err.println(ex.getMessage());
	        }  
	}

}
