package com.bxp.erp.invoice.orderdetail.vo;

import java.util.HashMap;
import java.util.Map;

import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.util.format.FormatUtil;

public class OrderDetailModel {

	private Long uuid;
	
	private Integer num;
	private Integer surplus;
	
	private Double price;
	
	private String priceView;
	
	private GoodsModel goods;
	private OrderModel order;
	
	
	
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	public String getPriceView() {
		return priceView;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatUtil.formatMoney(price);
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	public OrderModel getOrder() {
		return order;
	}
	public void setOrder(OrderModel order) {
		this.order = order;
	}
	
}


























