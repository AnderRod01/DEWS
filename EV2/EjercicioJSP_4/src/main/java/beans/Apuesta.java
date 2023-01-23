package beans;

import java.util.ArrayList;
import java.util.Arrays;

import exception.BadBetException;

public class Apuesta {
	private String nombre;
    private int administracion;
    private Integer[] numeros;
    private int reintegro;
    
    public Apuesta(final String name, final int administration, final Integer[] numbers, final int drawback) throws BadBetException {
 
        if(numbers == null)
            throw new BadBetException("Debes introducir numeros");
        
        System.out.println(Arrays.toString(numbers));
        
        if(numbers.length != 6)
            throw new BadBetException("Debes introducir 6 numeros");
        
        final ArrayList<Integer> inserted = new ArrayList<>();
        
        for (int n : numbers) {
            if(n > 0 && n < 50 && !inserted.contains(n))
                inserted.add(n);
        }
        
        if(inserted.size() != 6) {
            throw new BadBetException("Los 6 numeros no deben ser entre 1 y 49");
        } 
        
        if(drawback < 0 || drawback > 9)
            throw new BadBetException("El reintegro debe ser un numero de entre 0 y 9");
        
        
        this.nombre = name;
        this.administracion = administration;
        this.numeros = numbers;
        this.reintegro = drawback;
        
    }

   
    
    public String getNombre() {
		return nombre;
	}



	public int getAdministracion() {
		return administracion;
	}



	public Integer[] getNumeros() {
		return numeros;
	}



	public int getReintegro() {
		return reintegro;
	}



	@Override
    public String toString() {    
        final StringBuilder string = new StringBuilder(); 
        
        
        
        
        return string.toString(); 
    }
}
