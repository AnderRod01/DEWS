package beans;

public class ConversionCF {
	
	private float fah, cel;

	public ConversionCF(float temp,char tipo) {
		if (tipo== 'c') {
			this.cel = temp;
			this.fah = temp * 9/5 + 32;
		}else {
			this.fah = temp;
			this.cel = (temp - 32)* 5/9;
		}
	}

	public float getFah() {
		return fah;
	}

	public float getCel() {
		return cel;
	}
	
	
}
