package com.bxp.erp.invoice.order.business.ebo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.order.business.ebi.OrderEbi;
import com.bxp.erp.invoice.order.dao.dao.OrderDao;
import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.order.vo.OrderQueryModel;
import com.bxp.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.bxp.erp.invoice.store.vo.StoreModel;
import com.bxp.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import com.bxp.erp.invoice.storedetail.vo.StoreDetailModel;
import com.bxp.erp.invoice.storeoper.dao.dao.StoreOperDao;
import com.bxp.erp.invoice.storeoper.vo.StoreOperModel;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.util.exception.AppException;
import com.bxp.erp.util.num.NumUtil;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private StoreOperDao storeOperDao;
	
	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void saveByOrder(OrderModel order, Long[] goodsUuids,
			Integer[] nums, Double[] prices, EmpModel loginEmp) {
		//组装订单数据
		//创建时间为当前时间
		order.setCreateTime(System.currentTimeMillis());
		
		//订单类型为采购订单
		order.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//订单状态为未审核
		order.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//制单人为当前登录用户
		order.setCreater(loginEmp);
		
		Double totalPrice = 0.0d;
		Integer totalNum = 0;
		//创建订单明细
		Set<OrderDetailModel> orderDetails = new HashSet<OrderDetailModel>();
		for(int i = 0; i < goodsUuids.length; i++){
			
			OrderDetailModel orderDetail = new OrderDetailModel();
			orderDetail.setNum(nums[i]);
			orderDetail.setPrice(prices[i]);
			//未入库商品的数量
			orderDetail.setSurplus(nums[i]);
			GoodsModel goods= new GoodsModel();
			goods.setUuid(goodsUuids[i]);
			orderDetail.setGoods(goods);
			
			orderDetail.setOrder(order);
			
			totalPrice += prices[i];
			totalNum += nums[i];
			
			orderDetails.add(orderDetail);
		}
		order.setOrderDetails(orderDetails);
		order.setTotalNum(totalNum);
		order.setTotalPrice(totalPrice);
		
		order.setOrderNum(NumUtil.generatorOrderNum());
		
		orderDao.saveByOrder(order);
	}

	@Override
	public List<OrderModel> getAllBuyOrder() {
		return orderDao.AllBuyOrder();
	}

	@Override
	public OrderModel getOrder(Long uuid) {
		return orderDao.getByOrderUuid(uuid);
	}

	@Override
	public List<OrderModel> getAllBuyOrder(OrderQueryModel orderq) {
		return orderDao.getAllBuyOrder(orderq);
	}

	@Override
	public List<OrderModel> getAllCheckOrder(OrderQueryModel orderq) {
		Integer[] orderTypes = new Integer[]{OrderModel.ORDER_ORDERTYPE_OF_BUY, OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY};
		return orderDao.getAllOrderByOrderType(orderq, orderTypes);
	}

	@Override
	public void buyOrderCheckPass(OrderModel order, EmpModel loginEmp) {
		OrderModel orderTemp = orderDao.getByOrderUuid(order.getUuid());
		if(orderTemp.getType() != OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK){
			throw new AppException("对不起，请不要进行非法操作");
		}
		orderTemp.setChecker(loginEmp);
		orderTemp.setCheckTime(System.currentTimeMillis());
		orderTemp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
	}

	@Override
	public void buyOrderCheckNoPass(OrderModel order, EmpModel loginEmp) {
		OrderModel orderTemp = orderDao.getByOrderUuid(order.getUuid());
		if(orderTemp.getType() != OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK){
			throw new AppException("对不起，请不要进行非法操作");
		}
		orderTemp.setChecker(loginEmp);
		orderTemp.setCheckTime(System.currentTimeMillis());
		orderTemp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
	}
	private Integer[] taskTypes = new Integer[]{
		OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
		OrderModel.ORDER_TYPE_OF_BUY_BUYING,
		OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
		OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		};
	@Override
	public List<OrderModel> getAllTask(OrderQueryModel orderq) {
		
		return orderDao.getAllTaskByTaskTypes(orderq, taskTypes);
	}

	@Override
	public void assignTask(OrderModel order) {
		OrderModel orderTemp = getOrder(order.getUuid());
		if(orderTemp.getType() != OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS){
			throw new AppException("请不要进行非法操作");
		}
		orderTemp.setCompleter(order.getCompleter());
		orderTemp.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
	}

	@Override
	public List<OrderModel> getOrderByCompleter(Long completerUuid,
			OrderQueryModel orderq) {
		return 	orderDao.getOrderByCompleter(completerUuid, orderq);
	}

	@Override
	public void endTask(OrderModel order) {
		OrderModel orderTemp = getOrder(order.getUuid());
		if(!orderTemp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_BUYING)){
			throw new AppException("请不要进行非法操作");
		}
		orderTemp.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		orderTemp.setEndTime(System.currentTimeMillis());
	}

	@Override
	public List<OrderModel> getAllInstore(OrderQueryModel orderq) {
		
		orderq.setType(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE);
		return orderDao.getAllInstore(orderq);
	}

	
	
	@Override
	public OrderDetailModel inGoods(Long orderDetailUuid, Long storeUuid,
			Integer num, EmpModel loginEmp) {
		
		StoreModel store = new StoreModel();
		store.setUuid(storeUuid);
		
		
		//订单明细中的未入库数量减少
		OrderDetailModel orderDetail = orderDetailDao.getByUuid(orderDetailUuid);
		OrderModel order = orderDetail.getOrder();
		 if(!order.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_IN_STORE)){
			 throw new AppException("请不要进行非法操作");
		 }
		
		
		if(orderDetail.getSurplus() < num){
			
			throw new AppException("请不要进行非法操作");
		}
		GoodsModel goods = orderDetail.getGoods();
		orderDetail.setSurplus(orderDetail.getSurplus() - num);
		//仓库中的商品数量增加
		//先按照仓库和商品进行查询，
		StoreDetailModel storeDetail = storeDetailDao.getBuyStoreAndGoods(storeUuid, goods.getUuid());
		if(storeDetail != null){
			//更新
			storeDetail.setNum(storeDetail.getNum() + num);
		}else {
			//新增
			storeDetail = new StoreDetailModel();
			storeDetail.setNum(num);
			storeDetail.setGoods(goods);
			
			
			storeDetail.setStore(store);
			storeDetailDao.save(storeDetail);
		}
		//操作可追踪
		
		StoreOperModel storeOper = new StoreOperModel();
		storeOper.setEmp(loginEmp);
		storeOper.setOperTime(System.currentTimeMillis());
		storeOper.setType(StoreOperModel.STOREOPER_TYPE_OF_IN);
		storeOper.setGoods(goods);
		storeOper.setStore(store);
		storeOper.setNum(num);
		
		storeOperDao.save(storeOper);
		
		//入库完毕之后结单
		
		Set<OrderDetailModel> orderDetails = order.getOrderDetails();
		int surplusSum = 0;
		for(OrderDetailModel orderDetailTemp : orderDetails){
			surplusSum += orderDetailTemp.getSurplus();
		}
		if(surplusSum == 0){
			order.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			order.setEndTime(System.currentTimeMillis());
		}
 		return orderDetail;
	}

	
}
