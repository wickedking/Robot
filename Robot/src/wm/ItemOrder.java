package wm;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order and the quantity and the skus involved. 
 * @author Cody Shafer
 *
 */
public class ItemOrder {
	
	/**
	 * A list containing the sku's and the associated quantitys.
	 */
	private List<SkuOrder> my_orders;
	
	private String my_order_number;
	
	/**
	 * Public constructor with the prebuilt list of items.
	 * @param the_orders The list of items that are ordered.
	 */
	public ItemOrder(List<SkuOrder> the_orders, String the_order_number){
		my_orders = the_orders;
		my_order_number = the_order_number;
	}
	
	/**
	 * Returns the sku's associated with this order. 
	 * @return A list of sku's
	 */
	public List<Integer> getSkus(){
		ArrayList<Integer> skus = new ArrayList<Integer>(); 
		for(SkuOrder order : my_orders){
			skus.add(order.my_sku);
		}
		return skus;
	}
	
	/**
	 * Returns the List of ordered items.
	 * @return The list of items that have been ordered. 
	 */
	public List<SkuOrder> getOrders(){
		return my_orders;
	}
	
	/*
	 * Class to represent an order and all item within it
	 */

}
