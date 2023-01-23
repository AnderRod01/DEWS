package beans;

public class Cuenta {
	private String titular;
	private double saldo;
	public Cuenta(String titular, double saldo) {
		super();
		this.titular = titular;
		this.saldo = saldo;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	public boolean gastar (double cantidad) {
		if (cantidad<=saldo) {
			saldo -= cantidad;
			return true;
		}
		return false;
	}
	
	public void ingresar (double cantidad) {
		saldo += cantidad;
	}
	
}
