/**
 *
 *  @author Kowalski Robert S18290
 *
 */

package zad2;


public class Purchase {
	String id;
	String surname;
	String name;
	String prod;
	double q;
	double price;
	double cost;
	
	
	public Purchase(String idIn, String surnameIn,String nameIn, String prodIn, double qIn, double priceIn) {
		this.id=idIn;
		this.surname=surnameIn;
		this.name=nameIn;
		this.prod=prodIn;
		this.q=qIn;
		this.price=priceIn;
		this.cost=(qIn*priceIn); 
	}
	
	public String toString() {
		return id+";"+surname+" "+name+";"+prod+";"+q+";"+price;
	}
	public String toStringWithCost() {
		return id+";"+surname+" "+name+";"+prod+";"+q+";"+price+" (koszt: "+cost+")";
	}
	
	
}
