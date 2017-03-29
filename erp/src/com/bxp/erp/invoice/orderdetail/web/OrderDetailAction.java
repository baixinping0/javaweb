package com.bxp.erp.invoice.orderdetail.web;

import com.bxp.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.opensymphony.xwork2.ActionSupport;

public class OrderDetailAction extends ActionSupport{
	
	public OrderDetailModel orderDetail = new OrderDetailModel();
	
	private OrderDetailEbi orderDetailEbi;

	public void setOrderDetailEbi(OrderDetailEbi orderDetailEbi) {
		this.orderDetailEbi = orderDetailEbi;
	}
	
	
	//AJAX------------------------
	//AJAX------------------------
	//AJAX------------------------
	//AJAX------------------------
	 
	public String ajaxGetSurplus(){
		orderDetail = orderDetailEbi.getByUuid(orderDetail.getUuid());
		return "ajaxGetSurplus";
	}


	public OrderDetailModel getOrderDetail() {
		return orderDetail;
	}
	
}
