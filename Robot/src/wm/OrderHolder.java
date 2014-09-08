package wm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderHolder {
	
	private List<ItemOrder> my_queue;
	
	public OrderHolder(){
		my_queue = new ArrayList<ItemOrder>();
	}
	
	public void addItemOrder(ItemOrder the_order){
		my_queue.add(the_order);
	}
	
	public List<ItemOrder> getAllOrders(){
		ArrayList<ItemOrder> current_orders = new ArrayList<ItemOrder>();
		Iterator<ItemOrder> itr = my_queue.iterator();
		while(itr.hasNext()){
			ItemOrder the_order = itr.next();
			itr.remove();
			current_orders.add(the_order);
		}
		return current_orders;
		
	}
	
	//Recieves orders from website/outside and holds them until asked

}
