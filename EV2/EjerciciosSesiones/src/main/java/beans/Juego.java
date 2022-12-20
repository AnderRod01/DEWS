package beans;

import java.util.HashSet;

public class Juego {
	private String palabra;
	private HashSet<String> visibles; 
	private int vidas;
	
	public Juego(final String palabra) {
	    this.palabra = palabra;
	    this.vidas = palabra.length() / 2;
	    this.visibles = new HashSet<>();
	}
	
	public String getPalabra() {
	    return palabra;
	}
	
	public int getVidas() {
	    return vidas;
	}
	
	public int fallo() {
	    vidas--;
	    return vidas;
	}
	
	public void mostrar(final String letra) {
	    this.visibles.add(letra + "");
	}
	
	public boolean puedeMostrarse(final String letra) {
	    return this.visibles.contains(letra);
	}
}
