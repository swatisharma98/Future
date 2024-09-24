package future;

import java.util.concurrent.Callable;

public class InventoryChecker implements Callable<Boolean>{
	
	private Integer order;
	
	
	InventoryChecker(Integer order){
		
		this.order = order;
		
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(200);
		if(order %  2  == 0) {
			return true;
		}else {
			return false;
		}
		
	}

}
