package beans;

public class Imagen {
	private String ruta, nombre;
	private long tamanio;
	
	public Imagen(String ruta, String nombre, long tamanio) {

		this.ruta = ruta;
		this.nombre = nombre;
		this.tamanio = tamanio;
	}

	public String tamanioDesglosado () {
		
		long bytes = this.tamanio;
        long mb = bytes / (1024 * 1024);
        long kb = bytes % (1024 * 1024) / 1024;
        bytes = bytes % (1024 * 1024) % 1024;
		
		return mb + "Mb " + kb +"Kb " + bytes + "bytes";
	}
	
	public String getRuta() {
		return ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public long getTamanio() {
		return tamanio;
	}
	public static void main(String[] args) {
		Imagen i= new Imagen(null, null, 8499106);
		System.out.println(i.tamanioDesglosado());
	}
}
