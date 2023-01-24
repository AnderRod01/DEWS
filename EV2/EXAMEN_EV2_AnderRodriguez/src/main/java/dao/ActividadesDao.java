package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import conex.ConexPoolBD;

public class ActividadesDao {
	
	//metodo para obtener las actividades en las que participa un alumno
	public static ArrayList<Actividad> obtenerActividadesParticipa(String dni) {
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		String sql = "Select * from actividad, participa where participa.alumno_dni = ? and id = participa.actividad_id";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getInt("impartidor_id"));
				
				Actividad actividad = new Actividad(rs.getInt("id"), impartidor, rs.getString("nombre"),
						rs.getFloat("coste_mensual"), rs.getInt("capacidad"));
				actividades.add(actividad);
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return actividades;
	}
	
	//metodo para obtener las actividades disponibles en las que no participa un alumno
	public static ArrayList<Actividad> obtenerActividadesLibresNoParticipa (String dni){
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		String sql = "select * from actividad a "
				+ "where capacidad > (select count(*) "
				+ "		from participa p where a.id = p.actividad_id) "
				+ "and a.id  not in(select p.actividad_id from participa p where p.alumno_dni =  ?);";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getInt("impartidor_id"));
				
				Actividad actividad = new Actividad(rs.getInt("id"), impartidor, rs.getString("nombre"),
						rs.getFloat("coste_mensual"), rs.getInt("capacidad"));
				actividades.add(actividad);
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return actividades;
	}
	
	//Metodo para buscar una actividad dado un id como parametro
	public static Actividad getActividad (int id) {
		Actividad actividad = null;
		String sql = "Select * from actividad where id = ?";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getInt("impartidor_id"));
				
				actividad = new Actividad(rs.getInt("id"), impartidor, rs.getString("nombre"),
						rs.getFloat("coste_mensual"), rs.getInt("capacidad"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return actividad;
	}
	
	//metodo para inscribir a un alumno a una actividad
	public static boolean inscribir (Alumno alumno, Actividad actividad) {
		String sql = "INSERT INTO participa(actividad_id, alumno_dni, ultima_asistencia) "
                + " VALUES(?,?,NOW())";
    	 try {
    		 Connection con = ConexPoolBD.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             st.setInt(1, actividad.getId());
             st.setString(2, alumno.getDni());

             
             st.execute();
             
             st.close();
             con.close();
         } catch (SQLException ex) {
        	 System.err.println(ex.getMessage());
             return false;
         }
         return true;
	}
	
	//metodo para buscar las actividades de un impartidor en concreto
	public static ArrayList <Actividad> actividadesImpartidor (int id){
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		String sql = "select * from actividad where impartidor_id = ?";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getInt("impartidor_id"));
				
				Actividad actividad = new Actividad(rs.getInt("id"), impartidor, rs.getString("nombre"),
						rs.getFloat("coste_mensual"), rs.getInt("capacidad"));
				actividades.add(actividad);
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return actividades;
	}
	
	public static Map<Alumno, Date> mapaAsistenciaActividad(int id){
		Map<Alumno, Date> alumnos = null;
		String sql = "select dni, apellidos, nombre, telefono, email, ultima_asistencia  from alumno a, participa p "
				+ "where a.dni = p.alumno_dni "
				+ "and p.actividad_id in(select id from actividad a2 where a2.impartidor_id = ?) "
				+ "order by p.ultima_asistencia asc ";
		try {
			Connection con = ConexPoolBD.getConnection();

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				Impartidor impartidor = ImpartidoresDao.getImpartidor(rs.getInt("impartidor_id"));
				
				Alumno alumno = new Alumno(rs.getString("dni"), rs.getString("apellidos"), rs.getString("nombre"),
						rs.getString("telefono"), rs.getString("email"));
				
				Date fecha = rs.getDate("ultima_asistencia");
				alumnos.put(alumno, fecha);
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return alumnos;
	}
	
}
