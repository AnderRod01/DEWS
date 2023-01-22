package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class CarroCompra {
	private HashMap<Integer, LineaPedido> carro = new HashMap<Integer, LineaPedido>();
	
	public void aniadeLinea (LineaPedido lp) {
		if (carro.containsValue(lp)) {
			Set<Integer> claves = carro.keySet();
			
			for ( int clave : claves) {
				LineaPedido lineaEnCarro = carro.get(clave);
				if (lp.getItem().equals(lineaEnCarro.getItem())) {
					lineaEnCarro.setCantidad(lineaEnCarro.getCantidad()  + lp.getCantidad());
					carro.put (clave, lineaEnCarro);
					break;
				}
			}
		} else {
			carro.put(lp.getItem().getId(),  lp);
		}
	}
	
	public void borrarLinea (int idItem){
		carro.remove(idItem);
	}
	
	public LineaPedido getLineaPedido (int idItem) {
		return carro.get(idItem);
	}
	
	public Collection<LineaPedido> getAllLineaPedido (){
		return carro.values();
	}
	
	public double total() {
		double total = 0;
		
		Set<Integer> claves = carro.keySet();
		for (int clave : claves) {
			total += carro.get(clave).getItem().getPrecio();
		}
		
		return total;
		
	}
	
	public void removeAll() {
		carro.clear();
	}
	
	public boolean estaVacio() {
		return carro.isEmpty();
	}
}


