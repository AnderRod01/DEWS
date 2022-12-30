package beans;

import java.util.ArrayList;

public class Pregunta {
	private String enunciado, pista;
	private String[] respuestas;
	private int indiceRespuestaCorrecta;
	
	public Pregunta(String enunciado, String pista, String[] respuestas, int indiceRespuestaCorrecta) {
		this.enunciado = enunciado;
		this.pista = pista;
		this.respuestas = respuestas;
		this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
	}

	public int getIndiceRespuestaCorrecta() {
		return indiceRespuestaCorrecta;
	}
	
	
}
