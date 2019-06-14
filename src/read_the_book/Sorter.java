package read_the_book;

import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Sorter {
	private ConcurrentHashMap<String, Integer> wordCount;
	public Sorter(ConcurrentHashMap<String,Integer> wordCount){
		this.wordCount=wordCount;
	}	
	Map<String, Integer> sort(){
		Map<String, Integer> sorted = (wordCount)
	            .entrySet()
	            .stream()
	            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	            .collect(
	                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
	                    LinkedHashMap::new))
				.entrySet()
				.stream()
				.limit(3)
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
	                    LinkedHashMap::new));	                                       
						return sorted;
	}
}
