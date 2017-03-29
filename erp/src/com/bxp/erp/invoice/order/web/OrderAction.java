package com.bxp.erp.invoice.order.web;

import java.util.List;

import org.apache.log4j.chainsaw.Main;

import com.bxp.erp.auth.emp.business.ebi.EmpEbi;
import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.goods.business.ebi.GoodsEbi;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.order.business.ebi.OrderEbi;
import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.order.vo.OrderQueryModel;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.bxp.erp.invoice.store.business.ebi.StoreEbi;
import com.bxp.erp.invoice.store.vo.StoreModel;
import com.bxp.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport{
	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	private StoreEbi storeEbi;
	
	public OrderModel order = new OrderModel();
	public OrderQueryModel orderq = new OrderQueryModel();
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	
	
	
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}
	
	public String buySave(){
		//获取当前登录的用户
		EmpModel loginEmp = (EmpModel)ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		orderEbi.saveByOrder(order, goodsUuids, nums, prices, loginEmp);
		return "doBuyList";
	}
	
	public String buyList(){
		//获取所有的采购订单（orderType = 采购）
		List<OrderModel> orderList = orderEbi.getAllBuyOrder(orderq);
		ActionContext.getContext().put("orderList", orderList);
		return "buyList";
	}
	
	public String buyInput(){
		//获取所有具有具有商品类别，并且商品类别都具有商品的供应商
		List<SupplierModel> supplierList = supplierEbi.getAllHasGtmAndGm();
		ActionContext.getContext().put("supplierList", supplierList);
		
		//通过供应商获取具有商品的产品类别
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAllHasGoods(supplierList.get(0).getUuid());
		ActionContext.getContext().put("goodsTypeList", goodsTypeList);
		
		//通过商品类别获取所有的商品
		List<GoodsModel> goodsList = goodsEbi.getAll(goodsTypeList.get(0).getUuid());
		ActionContext.getContext().put("goodsList", goodsList);
		return "buyInput";
	}
	
	public String buyDetailList(){
//		获取订单
		order = orderEbi.getOrder(order.getUuid());
		ActionContext.getContext().put("order", order);
		return "buyDetailList";
	}
	
	//×××××××××××××××××××××××××××××××××××××××××××
	//×××××××××××××××××××××××××××××××××××××××××××
	//××××××××××××××采购审批×××××××××××××××××××××××
	//×××××××××××××××××××××××××××××××××××××××××××
	//×××××××××××××××××××××××××××××××××××××××××××
	public String buyCheckList(){
		//获取订单（采购订单，采购退货订单）
		List<OrderModel> orderList = orderEbi.getAllCheckOrder(orderq);
		
		ActionContext.getContext().put("orderList", orderList);
		
		return "buyCheckList";
	}
	
	public String buyCheckDetail(){
		order = orderEbi.getOrder(order.getUuid());
		ActionContext.getContext().put("order", order);
		return "buyCheckDetail";
	}
	public String buyCheckPass(){
		EmpModel loginEmp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		orderEbi.buyOrderCheckPass(order, loginEmp);
		return "doBuyCheckList";
	}
	public String buyCheckNoPass(){
		EmpModel loginEmp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		orderEbi.buyOrderCheckNoPass(order, loginEmp);
		return "doBuyCheckList";
	}
	
	
	//transport-----------------
	//transport-----------------
	//transport-----------------
	//transport-----------------
	
	public String taskList(){
		//获取指派订单，运输的任务必须是已经审核通过的
		List<OrderModel> orderList = orderEbi.getAllTask(orderq);
		ActionContext.getContext().put("orderList", orderList);
		
		//获取所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		ActionContext.getContext().put("supplierList", supplierList);
		return "taskList";
	}
	public String taskDetail(){
		
		//获取订单
		order = orderEbi.getOrder(order.getUuid());
		ActionContext.getContext().put("order", order);
		
		//获取当前登录人
		EmpModel loginEmp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		List<EmpModel> empList = empEbi.getEmpByDep(loginEmp.getDep().getUuid());
		ActionContext.getContext().put("empList", empList);
		return "taskDetail";
	}
	
	public String assignTask(){
		orderEbi.assignTask(order);
		return "doTaskList";
	}
	
	public String tasks(){
		//通过完成人获取订单
		EmpModel loginEmp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		List<OrderModel> orderList = orderEbi.getOrderByCompleter(loginEmp.getUuid(), orderq);
		
		//获取所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		ActionContext.getContext().put("supplierList", supplierList);
		
		ActionContext.getContext().put("orderList", orderList);
		return "tasks";
	}
	
	public String task(){
		order = orderEbi.getOrder(order.getUuid());
		ActionContext.getContext().put("order", order);
		return "task";
	}
	public String endTask(){
		orderEbi.endTask(order);
		return "doTasks";
	}
	
	//入库------------------
	//入库------------------
	//入库------------------
	//入库------------------
	public String inStoreList(){
		List<StoreModel> storeList = storeEbi.getAll();
		ActionContext.getContext().put("storeList", storeList);
		
		List<OrderModel> orderList = orderEbi.getAllInstore(orderq);
		ActionContext.getContext().put("orderList", orderList);
		return "inStoreList";
	}
	
	public String inStoreDetail(){
		order = orderEbi.getOrder(order.getUuid());
		ActionContext.getContext().put("order", order);
		
		//获取所有的仓库信息
		List<StoreModel> storeList = storeEbi.getAll();
		ActionContext.getContext().put("storeList", storeList);
		return "inStoreDetail";
	}
	
	//AJAX------------------
	//AJAX------------------
	//AJAX------------------
	//AJAX------------------
	
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String alreadyUseGoodsUuids = "";
	private List<GoodsTypeModel> goodsTypeList;
	private List<GoodsModel> goodsList;
	//获取第一个商品，用于页面上价格的显示，由于页面只需要第一个商品的价格
	//如果将所有的商品全部返回，数据量过大，效率太低
	private GoodsModel goods;
	/*
	 * 改变供应商的时候，ajax调用此方法
	 */
	public String ajaxGetGoodsTypeBySupplierUuid(){
		//通过供应商获取商品类别
		goodsTypeList = goodsTypeEbi.getAllHasGoods(supplierUuid);
		//通过商品类别获取商品
		goodsList = goodsEbi.getAll(goodsTypeList.get(0).getUuid());
		
		goods = goodsList.get(0);
		return "ajaxGetGoodsTypeBySupplierUuid";
	}
	/*
	 * 此方法在点击新建订单项的时候调用，具有过滤功能
	 * 对已经使用的商品进行过滤
	 */
	public String ajaxGetGoodsTypeBySupplierUuid2(){
		//通过供应商获取商品类别
		goodsTypeList = goodsTypeEbi.getAllHasGoods(supplierUuid);
		
		//对商品已经使用完的商品类别进行过滤
		//此时，对商品类别进行过滤。
		start:
		for(int i = goodsTypeList.size() - 1; i >= 0; i --){
			//获取对应的商品
			List<GoodsModel> goodsListTemp = goodsEbi.getAll(goodsTypeList.get(i).getUuid());
			for(int j = 0; j < goodsListTemp.size(); j++){
				//对商品进行迭代，如果不包含，则说明此商品类别中的商品没有使用完
				//则跳转到下一个商品类别进行判断
				if(!alreadyUseGoodsUuids.contains("'" +goodsListTemp.get(j).getUuid()+ "'")){
					continue start;
				}
			}
			//执行到此处，说明商品都已经使用，此时删除该商品类别
			goodsTypeList.remove(i);
		}
		
		//通过商品类别获取商品
		goodsList = goodsEbi.getAll(goodsTypeList.get(0).getUuid());
		//对已经使用过的商品进行过滤
		for(int i = goodsList.size() - 1; i >= 0 ; i--){
			if(alreadyUseGoodsUuids.contains("'" + goodsList.get(i).getUuid() + "'")){
				goodsList.remove(i);
			}
		}
		goods = goodsList.get(0);
		return "ajaxGetGoodsTypeBySupplierUuid";
	}
	
	/*
	 * 改变商品类型的时候，ajax调用此方法
	 */
	public String ajaxGetGoodsByGoodsType(){
		//通过商品类型id获取商品
		goodsList = goodsEbi.getAll(goodsTypeUuid);
		for(int i = goodsList.size() - 1; i >= 0 ; i--){
			if(alreadyUseGoodsUuids.contains("'" + goodsList.get(i).getUuid() + "'")){
				goodsList.remove(i);
			}
		}
		goods = goodsList.get(0);
		return "ajaxGetGoodsByGoodsType";
	}
	/*
	 * 修改商品，联动价格
	 */
	public String ajaxGetGoodsByGoodsUuid(){
		goods = goodsEbi.get(goodsUuid);
		return "ajaxGetGoodsByGoodsUuid";
	}
	
	
	public GoodsModel getGoods() {
		return goods;
	}
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public List<GoodsModel> getGoodsList() {
		return goodsList;
	}
	
	//入库操作
	public Integer num;
	public Long storeUuid;
	public Long orderDetailUuid;
	
	OrderDetailModel orderDetail;
	
	public OrderDetailModel getOrderDetail() {
		return orderDetail;
	}

	public String ajaxInGoods(){
		EmpModel loginEmp = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		orderDetail = orderEbi.inGoods(orderDetailUuid, storeUuid, num, loginEmp);
		return "ajaxInGoods";
	}
	
}
