/**
 *
 *  @author Kowalski Robert S18290
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import zad2.Purchase;

public class ProgLang<T, Z> {

	
	Map<T,Z> mapOfLangs;
	LinkedHashMap<String, Set<String>> mapForProgramers;
	LinkedHashMap<String,helpProgClas> helpMapProgrammers;
	
	public Map<String, helpProgClas> getHelpMapProgrammers() {
		return helpMapProgrammers;
	}


	public void setHelpMapProgrammers(LinkedHashMap<String, helpProgClas> helpMapProgrammers) {
		this.helpMapProgrammers = helpMapProgrammers;
	}

	BufferedReader fileTSVProgs;
	//BufferedReader fileTSVProgs2;
	//BufferedReader fileTSVProgs3;
	public ProgLang(String progrsTSV) {
		mapOfLangs = new LinkedHashMap<T,Z>();
		//	FileReader file;
			//FileReader file2;
			try {
				//file = new FileReader(progrsTSV);
			    FileInputStream fis = new FileInputStream(progrsTSV);
			    InputStreamReader isr = new InputStreamReader( fis, Charset.forName("UTF-8") );
			fileTSVProgs = new BufferedReader(isr);
			String tester;
			while((tester =fileTSVProgs.readLine()) !=null ) {
				List<String> tmpProgs = new ArrayList<String>();
				String[] progsTokens = tester.split("\\t");
				String key = progsTokens[0];
				for(int i = 1; i<progsTokens.length;i++) 
					tmpProgs.add(progsTokens[i]);
					tmpProgs = tmpProgs.stream().distinct().collect(Collectors.toList());
					mapOfLangs.put((T)key,(Z)tmpProgs);
			}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		/*	try {
				file = new FileReader(progrsTSV);
				
				fileTSVProgs2 = new BufferedReader(file);
				String tester;
				List<String> tmpProgs = new ArrayList<String>();
				while((tester =fileTSVProgs2.readLine()) !=null ) {
				
					String[] progsTokens = tester.split("\\t");
					String key = progsTokens[0];
					for(int i = 1; i<progsTokens.length;i++) 
						tmpProgs.add(progsTokens[i]);
						tmpProgs = tmpProgs.stream().distinct().collect(Collectors.toList());
						//System.out.println(tmpProgs);
				}		
				System.out.println(tmpProgs);
				mapForProgramers = new LinkedHashMap<String,Set<String>>();
				for (String name: tmpProgs) {
					helpProgClas tmpProgg = new helpProgClas (name,new HashSet<String>());
					//System.out.print(name);
					file2 = new FileReader(progrsTSV);
					//Set<String> tmp = new HashSet<>();
					fileTSVProgs3 = new BufferedReader(file2);
					String tester2;
					while((tester2 =fileTSVProgs3.readLine()) !=null ) {
				
					String[] progsTokens2 = tester2.split("\\t");
					String prog = progsTokens2[0];
					//System.out.print(" " +progsTokens2[0]);

					List<String> tmpList = Arrays.asList(progsTokens2);
						for (String nameTwo: tmpList) {
						//	System.out.print("  "+name+"   "+nameTwo+"  ")	;
							//System.out.println(nameTwo);
							if (name.equals(nameTwo))
								tmpProgg.whatHeKnows.add(tmpList.get(0));	
						}
						
					}
					System.out.println(name);
					System.out.println(tmpProgg);
					//helpMapProgrammers.put(name, tmpProgg);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
			
		}

	public <T,Z> Map<T, Z> getLangsMap() {
		// TODO Auto-generated method stub
		return (Map<T, Z>) mapOfLangs;
	}
	
	public LinkedHashMap<String, Set<String>> getProgsMap(){
		mapForProgramers = new LinkedHashMap<String,Set<String>>();
		helpMapProgrammers = new LinkedHashMap<String,helpProgClas>();
		
		for(T mainKey : mapOfLangs.keySet())  {
			//System.out.println(mainKey);
			for (Z nameOfProg : (List<Z>)mapOfLangs.get(mainKey)) {
				if(mapForProgramers.containsKey(nameOfProg)) {
					mapForProgramers.get(nameOfProg).add((String)mainKey);
				//System.out.println(nameOfProg + " added: " + mainKey);
			//	System.out.println();
			//	System.out.println(mapForProgramers);
				}
				else {
					Set<String> newLansgSet = new LinkedHashSet<>();
					newLansgSet.add((String)mainKey);
					mapForProgramers.put((String)nameOfProg, newLansgSet);
					helpMapProgrammers.put((String)nameOfProg , new helpProgClas((String)nameOfProg, newLansgSet));					
					}}}
		return mapForProgramers;	
		
	}
	
	public Map<T,Z> getLangsMapSortedByNumOfProgs(){
		Map<T,Z> toNotChange = mapOfLangs.entrySet().stream().sorted(new Comparator<Map.Entry<T,Z>>() {
			public int compare(Entry<T, Z> z1, Entry<T, Z> z2) {
				List<String> l1 = (List<String>) z1.getValue();
				List<String> l2 = (List<String>) z2.getValue();
				Integer i1 = l1.size();
				Integer i2 = l2.size();	
				if (i2.compareTo(i1) == 0) {
					String s1 = z1.getKey().toString();
					String s2 = z2.getKey().toString();
				return s1.compareTo(s2);
				}
				return i2.compareTo(i1);
				
			}}
			).collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new)));
		
		return toNotChange;
	}
	
	public Map<T,Z> getProgsMapSortedByNumOfLangs(){
		Map<T, Z> toNotChange;
		toNotChange = (LinkedHashMap<T, Z>) this.helpMapProgrammers.entrySet().stream().sorted(new Comparator<Map.Entry<String,helpProgClas>>() {
			public int compare(Entry<String, helpProgClas> z1, Entry<String, helpProgClas> z2) {
				Integer i1 = z1.getValue().sizeOfLangs();
				Integer i2 = z2.getValue().sizeOfLangs();
				if (i2.compareTo(i1) == 0) {
					String s1 = z1.getKey().toString();
					String s2 = z2.getKey().toString();
				//	System.out.println( s1 + "  " +s2);
					return s1.compareTo(s2);
					}
				else
					return i2.compareTo(i1);
				
				
			}}
			).collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new)));
		
		return toNotChange;
	}
	
	public Map<T,Z> getProgsMapForNumOfLangsGreaterThan(int i){
		Map<T, Z> toNotChange;
		toNotChange = (LinkedHashMap<T, Z>) this.helpMapProgrammers.entrySet().stream().filter(new Predicate<Map.Entry<String, helpProgClas>>(){

			@Override
			public boolean test(Entry<String, helpProgClas> t) {
				return t.getValue().sizeOfLangs()>i;

			}
			
			
		})
			.collect((Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new)));
		
		return toNotChange;
	}
	
	
	
	
	
	
	}
	
