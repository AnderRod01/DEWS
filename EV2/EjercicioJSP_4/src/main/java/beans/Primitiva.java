package beans;

import java.util.Arrays;
import java.util.HashSet;

public class Primitiva {
		private Fecha fecha;
	    private Integer[] numbers;
	    private int reintegro;
	    
	    public Primitiva(final Fecha date) {
	        this.fecha = date;
	        this.numbers = generadorDeNumeros();  
	        Arrays.sort(this.numbers);
	        
	        this.reintegro = (int) (Math.random() * 10);
	    }
	    
	    private Integer[] generadorDeNumeros() {
	        final HashSet<Integer> generated = new HashSet<>();
	        
	        while(generated.size() < 6){
	            int random = (int) (1 + Math.random() * 49);
	            generated.add(random);
	        }
	        
	        return generated.toArray(new Integer[6]);
	    }
	    
	    
	    private boolean winner(final int number){
	        
	        final String serial = number + "";
	        
	        if(serial.length() < 6)
	            return false;
	        
	        for (int i = 0; i < this.numbers.length; i++) {
	            
	            final int digit = Integer.parseInt(serial.charAt(i) + "");
	            if(this.numbers[i] != digit)
	                return false;
	        }
	        
	        final int lastDigit = Integer.parseInt(serial.charAt(serial.length()-1) + "");
	        
	        if(lastDigit != this.reintegro)
	            return false;
	        
	        return true;
	    }
	    
	    
	    public String result(final Apuesta apuesta) {
	        final StringBuilder result = new StringBuilder();
	        result.append("Aciertos: ");
	        
	        for (int i = 0; i < this.numbers.length; i++) {
	            final Integer current = apuesta.getNumeros()[i];
	            if(current != null && current.equals(this.numbers[i]))
	                result.append(" ").append(this.numbers[i]);                
	        }
	        
	        if(apuesta.getReintegro() == this.reintegro)
	           result.append(" Con reintegro.");
	        else
	           result.append(" Sin reintegro.");
	                
	        return result.toString();
	    }

	    public Fecha getDate() {
	        return fecha;
	    }

	    public int getDrawback() {
	        return reintegro;
	    }

	    public Integer[] getNumbers() {
	        return numbers;
	    }
}
