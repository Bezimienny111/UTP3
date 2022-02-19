/**
 *
 *  @author Kowalski Robert S18290
 *
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CustomersPurchaseSortFind {
	
	List<Purchase> listReaded;
	File fileToRead;
	Scanner scan = null;
	
	public CustomersPurchaseSortFind() {
	super();
	}
	
	public void readFile(String whereIsTxt) {
		listReaded = new ArrayList<Purchase>();
		fileToRead = new File(whereIsTxt);
		
		try {
			scan = new Scanner(fileToRead);
			while (scan.hasNextLine() ) {
				String[] purchases  = scan.nextLine().split(";| ");	
				Purchase adding = new Purchase(purchases[0],purchases[1],purchases[2],purchases[3],Double.parseDouble(purchases[4]),Double.parseDouble(purchases[5]));
				listReaded.add(adding);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error, no file!");
		}
	}
		
	public void showSortedBy(String what) {
			if (what.equals("Nazwiska")) {
				System.out.println(what);
				listReaded.sort(new Comparator<Purchase>(){
					public int compare(Purchase p1, Purchase p2) {
					int test = p1.surname.compareTo(p2.surname);
					if (test == 0)
							return p1.id.compareTo(p2.id);
					else
						return test;
					}				
				});
				for (Purchase p : listReaded) {
					System.out.println(p);
				}
				System.out.println();
			}
			if(what.equals("Koszty")) {
				System.out.println(what);
				listReaded.sort(new Comparator<Purchase>(){
					public int compare(Purchase p1, Purchase p2) {
					if (p1.cost < p2.cost)	
						return 1;
					else if (p1.cost == p2.cost) 
							return p1.id.compareTo(p2.id);
					else
						return -1;				
					}
					});
				for (Purchase p : listReaded) {
					System.out.println(p.toStringWithCost());
				}
				System.out.println();
			}
			if (!(what.equals("Koszty")) && !(what.equals("Nazwiska")))
				System.out.println("error");
		
		
	}
	public void showPurchaseFor(String idYouWant) {
		int test = 0;
		for (Purchase p : listReaded) 	
			if (idYouWant.equals(p.id))
				test++;
		
		if (test > 0 ) {
		System.out.println("Klient "+idYouWant);
		for (Purchase p : listReaded) 
			if (idYouWant.equals(p.id))
				System.out.println(p);
		
		System.out.println();
		test =0;
		}
		
		
	}
	
	
	
}
	
	

