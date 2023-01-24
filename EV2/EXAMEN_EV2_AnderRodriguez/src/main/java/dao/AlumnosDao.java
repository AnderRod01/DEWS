package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.Alumno;
import conex.ConexPoolBD;

public class AlumnosDao {

	public static Alumno getAlumno(String dni) {
		Alumno alumno = null;
		String sql = "Select * from alumno where dni = ?";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				alumno = new Alumno(rs.getString("dni"), rs.getString("apellidos"), rs.getString("nombre"),
						rs.getString("telefono"), rs.getString("email"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return alumno;
	}

}
