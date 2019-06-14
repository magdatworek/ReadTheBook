package read_the_book;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

class WriteToMap implements Runnable{
	private BlockingQueue<String> blockingQueue = null;
	private ConcurrentHashMap<String,Integer> wordCount = null;
	private Reader reader;
	
	public WriteToMap(BlockingQueue<String>blockingQueue,ConcurrentHashMap<String,Integer> wordCount,Reader reader) {
		this.blockingQueue=blockingQueue;
		this.wordCount=wordCount;
		this.reader=reader;
	}
	@Override
	public void run() {
		try {
			while(true) {
				String oneLine = blockingQueue.take();

				if(oneLine.isEmpty()&&reader.readingFinished==true) {
					break;   
				}
				oneLine=oneLine.replaceAll("[^\\w~/\\-+]"," ").toLowerCase();
				String[]oneLineArr=oneLine.split(" +");
				for (int i=0; i<oneLineArr.length;++i) {
					if(!oneLineArr[i].isBlank()) {

						if(wordCount.containsKey(oneLineArr[i])) {
							wordCount.put(oneLineArr[i], wordCount.get(oneLineArr[i]).intValue()+1);
						}
						else {
							wordCount.put(oneLineArr[i], 1);
						}
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
