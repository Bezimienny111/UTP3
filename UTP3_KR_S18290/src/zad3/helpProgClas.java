/**
 *
 *  @author Kowalski Robert S18290
 *
 */

package zad3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class helpProgClas {
	String programmerName;
	Set<String> whatHeKnows = new LinkedHashSet<String>();
	
	public helpProgClas(String hisName, Set<String> hisLangs) {
		super();
		this.programmerName = hisName;
		this.whatHeKnows =  hisLangs;
	}
	
	public String toString() {
		return "" + whatHeKnows;	
	}
	
	public Integer sizeOfLangs() {
		return whatHeKnows.size();
	}
	
}
