package com.bxp.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.order.vo.OrderQueryModel;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface OrderEbi {
	
	/**
	 * 保存进货订单
	 * @param order 订单
	 * @param goodsUuids 商品集合
	 * @param nums 商品数量集合
	 * @param prices 商品价格集合
	 * @param loginEmp 当前登录用户
	 */
	public void saveByOrder(OrderModel order, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel loginEmp);

	public List<OrderModel> getAllBuyOrder();

	public OrderModel getOrder(Long uuid);

	public List<OrderModel> getAllBuyOrder(OrderQueryModel orderq);

	/**
	 * 才后界面获取订单
	 * @param orderq 
	 * @return 
	 */
	public List<OrderModel> getAllCheckOrder(OrderQueryModel orderq);

	public void buyOrderCheckPass(OrderModel order, EmpModel loginEmp);

	public void buyOrderCheckNoPass(OrderModel order, EmpModel loginEmp);

	public List<OrderModel> getAllTask(OrderQueryModel orderq);

	public void assignTask(OrderModel order);

	public List<OrderModel> getOrderByCompleter(Long uuid,
			OrderQueryModel orderq);

	public void endTask(OrderModel order);

	public List<OrderModel> getAllInstore(OrderQueryModel orderq);

	public OrderDetailModel inGoods(Long orderDetailUuid, Long storeUuid,
			Integer num, EmpModel loginEmp);



}
