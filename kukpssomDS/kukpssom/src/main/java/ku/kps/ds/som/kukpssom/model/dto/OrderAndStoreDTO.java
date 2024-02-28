package ku.kps.ds.som.kukpssom.model.dto;

import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.model.Store;

public class OrderAndStoreDTO {

	private OrderFood order;
	private Store store;

	public OrderFood getOrder() {
		return order;
	}

	public void setOrder(OrderFood order) {
		this.order = order;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
