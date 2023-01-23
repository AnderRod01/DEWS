/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import beans.Autor;
import beans.Libro;
import beans.Prestamo;

/**
 *
 * @author Amaia
 */
public class GestorBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";
    private static final String PASS = "";
    private static BasicDataSource dataSource;

    public GestorBD() {
        //Creamos el pool de conexiones
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        //Indicamos el tamaño del pool de conexiones
        dataSource.setInitialSize(50);
    }
    
    public ArrayList<Libro> libros(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        String sql = "SELECT * FROM libro";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Libro libro = new Libro(rs.getInt("id"), rs.getString("titulo"),
                                        rs.getInt("paginas"), rs.getString("genero"), 
                                        rs.getInt("idAutor"));
                libros.add(libro);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return libros;
    }
    
    public LinkedHashMap<Integer, String> autores(){
        LinkedHashMap<Integer, String> autores = new LinkedHashMap<Integer, String>();
        String sql = "SELECT id, nombre FROM autor";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                autores.put(rs.getInt("id"), rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
    public boolean existeLibro(Libro libro){
        boolean existe = false;
        String sql = "SELECT id FROM libro WHERE titulo = '" + libro.getTitulo() +
                "' AND idautor = " + libro.getIdAutor();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public int insertarLibro(Libro libro){
        int id = -1;
        String sql = "INSERT INTO libro(titulo, paginas, genero, idautor) "
                + " VALUES(?, ?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, libro.getTitulo());
            st.setInt(2, libro.getPaginas());
            st.setString(3, libro.getGenero());
            st.setInt(4, libro.getIdAutor());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarLibro: " + ex);
        }
        
        return id;
    }
    
    public LinkedList<Autor> getAutores(){
		LinkedList<Autor> autores = new LinkedList();
		String sql = "SELECT id, nombre, fechanac, nacionalidad FROM autor";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                autores.add(new Autor(rs.getInt("id"), rs.getString("nombre"), rs.getDate("fechanac"), rs.getString("nacionalidad")));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
    public LinkedList<Libro> librosAutor(int autor) {
        
        String sql = "SELECT id, titulo, paginas, genero, idautor FROM libro WHERE idautor = ?";
        LinkedList<Libro> librosAutor = new LinkedList<>();
        
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, autor);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                final int id = rs.getInt("id");
                final String titulo = rs.getString("titulo");
                final int paginas = rs.getInt("paginas");
                final String genero = rs.getString("genero");
                
                librosAutor.add(new Libro(id, titulo, paginas, genero, autor));
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo librosAutor: " + ex);
        }
        
        return librosAutor;
    }
 
    public boolean existeAutor(String nombre) {
	     String sql = "SELECT id FROM autor WHERE nombre=?";
	     boolean existe = false;
	     try {
	         
	         Connection con = dataSource.getConnection();
	         PreparedStatement st = con.prepareStatement(sql);          
	         st.setString(1, nombre);
	         ResultSet rs = st.executeQuery();
	         
	         if(rs.next())
	             existe = true;
	                 
	         rs.close();
	         st.close();
	         con.close();
	     } catch (SQLException ex) {
	         System.err.println("Error en metodo existeAutor: " + ex);
	         existe = true;
	     }
	     
	     return existe;
	 }
     
    public int insertarAutor(Autor autor){
        int id = -1;
        String sql = "INSERT INTO autor(nombre, fechanac, nacionalidad) "
                + " VALUES(?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, autor.getNombre());
            

            st.setDate(2,autor.getFechanacSQL() );
            st.setString(3, autor.getNacionalidad());
            
            st.executeUpdate();


            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarAutor: " + ex);
        }
        
        return id;
    }
    
    public String nombreAutor(int id) {
    	String nombre = "";
    	
    	String sql = "SELECT nombre FROM autor WHERE id = ?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo nombreAutor: " + ex);
        }
        
        return nombre;
    }
    
    public boolean prestarLibro(int id) {
    	String sql = "INSERT INTO prestamo(fecha, idlibro) "
                + " VALUES(NOW(), ?)";
    	 try {
             Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

             st.setInt(1, id);
             
             st.executeUpdate();
             ResultSet rs = st.getGeneratedKeys();
             if(rs.next()){
                 id = rs.getInt(1);
             }
             rs.close();
             st.close();
             con.close();
         } catch (SQLException ex) {
             System.err.println("Error en metodo insertarAutor: " + ex);
             return false;
         }
         return true;

    }
    
    public String getTituloLibro (int id) {
    	String sql = "SELECT titulo FROM libro WHERE id = ?";
    	String titulo = "";
         
         try {
             Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             st.setInt(1, id);
             ResultSet rs = st.executeQuery();
             
             if(rs.next()){
                 titulo = rs.getString(1);
             }
             
             rs.close();
             st.close();
             con.close();
         } catch (SQLException ex) {
             System.err.println("Error en metodo getTituloLibro: " + ex);
         }
         
         return titulo;

    }
    
    public LinkedList<Prestamo> getPrestamos(){
    	LinkedList<Prestamo> prestamos = new LinkedList();
		String sql = "SELECT id, fecha, idlibro FROM prestamo";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                prestamos.add(new Prestamo(rs.getInt("id"), rs.getDate("fecha"), rs.getInt("idlibro")));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return prestamos;
    }
    
    public LinkedList<Libro> librosPrestados() {
        
        String sql = "SELECT distinct l.id, l.titulo, l.paginas, l.genero, l.idautor "
        		+ "FROM libro l, prestamo p "
        		+ "WHERE l.id = p.idlibro "
        		+ "ORDER BY p.fecha desc";
        LinkedList<Libro> librosAutor = new LinkedList<>();
        
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                final int id = rs.getInt("id");
                final String titulo = rs.getString("titulo");
                final int paginas = rs.getInt("paginas");
                final String genero = rs.getString("genero");
                final int autor = rs.getInt("idautor");
                librosAutor.add(new Libro(id, titulo, paginas, genero, autor));
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo librosAutor: " + ex);
        }
        
        return librosAutor;
    }
    
    public java.util.Date fechaPrestamo (int idLibro) {
    	String sql = "select distinct fecha from prestamo where idlibro = ? order by fecha desc";
    	java.util.Date fecha = null;
         
         try {
             Connection con = dataSource.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             st.setInt(1, idLibro);
             ResultSet rs = st.executeQuery();
             
             if(rs.next()){
                 fecha = (java.util.Date) rs.getDate(1);
             }
             
             rs.close();
             st.close();
             con.close();
         } catch (SQLException ex) {
             System.err.println("Error en metodo fechaPrestamo: " + ex);
         }
         
         return fecha;
    }
    
    
}
