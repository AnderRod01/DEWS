package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Test {
	private int numPreguntas;
	private ArrayList<Pregunta> preguntas;  
	private static Pregunta[] preguntasPorDefecto;
	
	static {
		preguntasPorDefecto = new Pregunta[] {
				new Pregunta("Qué se utiliza puede planear la semana?", "papel", new String[]{"Ala delta", "Agenda", "Acelerador de partículas"}, 1),
				new Pregunta("Capital de España", "Empieza por M :)", new String[]{"Madrid", "Matalascañas", "Murcia"}, 0),
				new Pregunta("De qué color en la primera 'G' del logo de Google?", "Significa 'triste' en inglés", new String[]{"Azul", "Rojo", "Amarillo"}, 0),
				new Pregunta("Qué animal aparece en el logo de GitHub?", "Felino", new String[]{"Puma", "Gato", "Pingüino"}, 1),
				new Pregunta("Cuantos colores tiene la bandera de San Marino?", "mas de uno", new String[] {"uno", "siete", "dos"}, 2)
		};
	}
	
	
	public Test (int cant) {
		ArrayList<Pregunta> listaPorDefecto = new ArrayList<Pregunta>(Arrays.asList(preguntasPorDefecto));
 		Collections.shuffle(listaPorDefecto);
		
 		
		if (cant<=listaPorDefecto.size())
			numPreguntas=cant;
		else
			numPreguntas=listaPorDefecto.size();
		
		preguntas = new ArrayList<Pregunta>(listaPorDefecto.subList(0, numPreguntas));
	}
	
	
	
	public int comprobar (ArrayList<Integer> respuestas) {
		int aciertos=0;
		
		for(int i=0; i<preguntas.size();i++) {
			if (respuestas.get(i) == preguntas.get(i).getIndiceRespuestaCorrecta()) 
				aciertos++;
			
		}
		
		return aciertos;
	}
}
