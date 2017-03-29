package com.bxp.erp.invoice.order.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.order.vo.OrderQueryModel;

public interface OrderDao {

	public void saveByOrder(OrderModel order);

	public List<OrderModel> AllBuyOrder();

	public OrderModel getByOrderUuid(Long uuid);

	public List<OrderModel> getAllBuyOrder(OrderQueryModel orderq);

	public List<OrderModel> getAllOrderByOrderType(OrderQueryModel orderq, Integer[] orderTypes);

	public List<OrderModel> getAllTaskByTaskTypes(OrderQueryModel orderq,
			Integer[] taskTypes);

	public List<OrderModel> getOrderByCompleter(Long completerUuid,
			OrderQueryModel orderq);

	public List<OrderModel> getAllInstore(OrderQueryModel orderq);


}
