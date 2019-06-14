package read_the_book;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ReadTheBook {
	public static void main(String []args) {
		BlockingQueue<String> queue = new LinkedBlockingDeque<>(65536);
		ConcurrentHashMap<String,Integer> wordCount = new ConcurrentHashMap<>();
		int threadNumber=6;
		Thread[] threadArray=new Thread[threadNumber];
		WriteToMap[] writeArray=new WriteToMap[threadNumber];
		Reader reader = new Reader(queue);
		Thread readThread = new Thread(reader);
		readThread.start();
		
		for (int i=0;i<threadNumber;++i) {
			writeArray[i]=new WriteToMap(queue,wordCount,reader);
			threadArray[i]=new Thread(writeArray[i]);
			threadArray[i].start();
		}
		for (int i=0;i<threadNumber;++i) {
			try {
				threadArray[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			readThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			Sorter sorter = new Sorter(wordCount);
			Map<String,Integer> sortedMap = sorter.sort();
			System.out.println(sortedMap);
	}
}
