package future;

import java.util.Random;
import java.util.concurrent.Callable;

public class OrderFetcher implements Callable<Integer>{
	
	public static Random random;
	
	
	public OrderFetcher (){
		random = new Random();
    }

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("In Order Fetcher");
		Thread.sleep(200);
		
		 return random.nextInt(6);
	}

}
