package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Impartidor;
import conex.ConexPoolBD;

public class ImpartidoresDao {

	public static Impartidor getImpartidor(int id) {
		Impartidor impartidor = null;
		String sql = "Select * from impartidor where id = ?";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				impartidor = new Impartidor(rs.getInt("id"), rs.getString("apellido"), rs.getString("nombre"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return impartidor;
	}

}
