package beans;

import java.util.Date;

public class Prestamo {
	private int idLibro, id;
	private Date fecha;
	
	
	public Prestamo(int id,Date fecha,int idLibro) {
		this.id = id;
		this.idLibro = idLibro;
		this.fecha = fecha;
	}
	
	
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
}
