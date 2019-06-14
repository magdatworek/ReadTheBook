package read_the_book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

class Reader implements Runnable{
	private BlockingQueue<String> blockingQueue;
	public boolean readingFinished=false;

	public Reader(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
		}
	@Override
	public void run() {
		BufferedReader in=null;
		String oneLine = "";
		try {
			in = new BufferedReader(new FileReader("aFile"));
			while((oneLine=in.readLine())!=null){
                blockingQueue.put(oneLine);
			}
			readingFinished=true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
