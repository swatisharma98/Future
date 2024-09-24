package future;

import java.util.concurrent.Callable;

public class PaymentAccepter implements Callable<String>{
	
	
	Boolean inventoryPresent;
	
	
	PaymentAccepter(Boolean inventoryPresent){
		this.inventoryPresent = inventoryPresent;
		
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		if(inventoryPresent) {
			return "Payment Accepted";
		}
		else {
			return "Payment Not Accepted";
		}
	}
	
	

}
