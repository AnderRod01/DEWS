package beans;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AlmacenPalabras {
	private static final List<String> LISTA_PALABRAS = Arrays.asList("instrumento", "rodilla", "pandemia", "rama", "zorro","chancleta","osteoporosis","");
	
	
	
	public static String getPalabra () {
		Random generadorRandom = new Random();
		int index = generadorRandom.nextInt(LISTA_PALABRAS.size());
		return LISTA_PALABRAS.get(index);
	}
}
