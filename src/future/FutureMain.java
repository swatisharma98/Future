package future;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureMain {

	public static void main(String[] args) throws ExecutionException,InterruptedException{
		
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		Instant start = Instant.now();
		
		
		for(int i=0;i<=9;i++) {
			OrderFetcher orderFetcher = new OrderFetcher();
			
			Future<Integer> future1 = executor.submit(orderFetcher);
			
			
			InventoryChecker inventoryChecker = new InventoryChecker(future1.get());
			
			System.out.println("Order No. is:"+future1.get());
			
			Future<Boolean> future2 = executor.submit(inventoryChecker);
			
			PaymentAccepter paymentAccepter = new PaymentAccepter(future2.get());
			
			System.out.println("Inventory Present:"+future2.get());
			
			Future<String> future3 = executor.submit(paymentAccepter);
			
			System.out.println(future3.get());
			
		}
		
		
		
		
		Instant end = Instant.now();
		
		long timeElapsed = Duration.between(start, end).toMillis();
		
		System.out.println("Total time:"+timeElapsed);
		
		
		executor.shutdown();
		
		

	}

}
