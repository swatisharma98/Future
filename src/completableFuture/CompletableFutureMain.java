package completableFuture;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureMain {
	
	private static int count=0;


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		
		
		
	     Instant start = Instant.now();
	     
	     System.out.println("Start time " + start);
	     
	     Supplier<String> orderFetcher = () -> {
           
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
             count++;
             System.out.println("\n" + String.valueOf(count) + "\n") ;
             String fetcher =Thread.currentThread().getName() +  "order fetched " + String.valueOf(count) + " ";
             System.out.println("Order Fetcher:="+fetcher);
             return fetcher;
        };
        
        Function<String,String> orderEnricher = order -> {
        	System.out.println("Order Enriched:="+order);
            return order + "order Enriched " + Thread.currentThread().getName();
        };

        Consumer<String> emailSender = orderEnriched -> {
            System.out.println(orderEnriched + "Email Sent for order " + Thread.currentThread().getName() + "\n") ;
            Instant end = Instant.now();
            System.out.println("End time " + end);

        };

	     
        CompletableFuture  cf1 = CompletableFuture.supplyAsync(orderFetcher,executor).thenApplyAsync(orderEnricher).thenAcceptAsync(emailSender) ;
        CompletableFuture  cf2 = CompletableFuture.supplyAsync(orderFetcher,executor).thenApplyAsync(orderEnricher).thenAcceptAsync(emailSender);
        
        
        //allOf wait for multiple CompletableFuture instances to complete.
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(cf1, cf2);
        combinedFuture.join(); // Block until both futures complete
        
        //waits for two specific futures to complete and processes their results together.
        //cf1.thenAcceptBoth(cf2,(order1, order2) -> {System.out.println("Accepted Both " +  order1 + "  " +  order2);});
	
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("MAIN STILL HAS LOT OF FREE TIME...." + timeElapsed);
        //executor.shutdown();
        System.out.println("Count="+count);
        Thread.sleep(1000);
        executor.shutdown();
	}

}
