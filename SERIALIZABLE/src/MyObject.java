import java.io.Serializable;
import java.util.List;

public class MyObject implements Serializable {
    private String firstName;
    private double valor;


    public MyObject(String firstName,  double valor) {
        this.firstName = firstName;
        this.valor = valor;
     
    }
	
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getValor() {
        return valor;
    }

    public void setLastName(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", valor=" + valor
                + "]";
    }

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8689347479081965601L;

}
