/**
 *
 *  @author Kowalski Robert S18290
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Anagrams {
	List<String> listOfWords;
	List<List> listOfList;
	File reader;
	Charset usethis = Charset.forName("UTF-8");

	public Anagrams(String wordsToList) {
		reader = new File(wordsToList);
		BufferedReader br;
		listOfWords = new ArrayList<String>();
		String memory;
		try {
			FileReader fr = new FileReader(reader);
			br = new BufferedReader(fr);
			while ((memory = br.readLine()) != null) {
				String[] tmpStringTab = memory.split(" ");
				for (int i = 0; i < tmpStringTab.length; i++)
					listOfWords.add(tmpStringTab[i]);
			}
		} catch (FileNotFoundException e1) {
			System.err.println("FileNotFoundException in Anagrams !");
		} catch (IOException e) {
			System.err.println("IOException in Anagrams !");
		}
	}

	public boolean anagramTest(String ana1, String ana2) {
		// ana1.replaceAll("[\\s]", "");
		// ana2.replaceAll("[\\s]", "");
		char[] first = ana1.toCharArray();
		char[] second = ana2.toCharArray();
		Arrays.sort(first);
		Arrays.sort(second);
		return Arrays.equals(first, second);
	}

	public List<List> getSortedByAnQty() {
		listOfList = new ArrayList<List>();
		List<String> testedWords = new ArrayList<String>();
		boolean tester;
		for (int i = 0; i < listOfWords.size(); i++) {
			tester = !testedWords.contains(listOfWords.get(i));
			if (tester) {
				List<String> adder = new ArrayList<String>();
				for (int j = 0; j < listOfWords.size(); j++) {
					String test1 = listOfWords.get(i);
					String test2 = listOfWords.get(j);
					if (anagramTest(test1, test2)) {
						testedWords.add(test2);
						adder.add(test2);
					}
				}
				listOfList.add(adder);
			}
		}
		listOfList.sort(new Comparator<List>() {
			public int compare(List l1, List l2) {
				Integer i1 = l1.size();
				Integer i2 = l2.size();
				if(i2.compareTo(i1) == 0) {
				String[] ar1 = new String[l1.size()];
				l1.toArray(ar1);
				String[] ar2 = new String[l2.size()];
				l2.toArray(ar2);
				return (ar1[0].compareTo(ar2[0]));
				}
				else
				return i2.compareTo(i1);
			}
		});
		return listOfList;
	}

	public String getAnagramsFor(String whatAreYouLooking) {
		for (int i = 0; i < listOfList.size(); i++) {
			List<String> testedList = new ArrayList<String>(listOfList.get(i));
			for (int j = 0; j < testedList.size(); j++) {
				boolean test = anagramTest(whatAreYouLooking,testedList.get(j)); 	
				if(test) {
				if ( testedList.get(j).equals(whatAreYouLooking)) {
					testedList.remove(j);
					return whatAreYouLooking + ": " + testedList;
				}
				else
					return  whatAreYouLooking + ": " + testedList;
			}}

		}
		return whatAreYouLooking + ":  null";
	}
}