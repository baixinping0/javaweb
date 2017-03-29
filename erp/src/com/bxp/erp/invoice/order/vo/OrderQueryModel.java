package com.bxp.erp.invoice.order.vo;

import com.bxp.erp.util.format.FormatUtil;

public class OrderQueryModel extends OrderModel{
	private Integer totalNum1;
	private Long createTime1;
	private Long checkTime1;
	private Double totalPrice1;
	
	private String createTime1View;
	private String checkTime1View;
	
	
	
	public Long getCheckTime1() {
		return checkTime1;
	}
	public void setCheckTime1(Long checkTime1) {
		this.checkTime1 = checkTime1;
		this.checkTime1View = FormatUtil.formatDate(checkTime1);
	}
	public String getCheckTime1View() {
		return checkTime1View;
	}
	public String getCreateTime1View() {
		return createTime1View;
	}
	public Integer getTotalNum1() {
		return totalNum1;
	}
	public void setTotalNum1(Integer totalNum1) {
		this.totalNum1 = totalNum1;
	}
	public Long getCreateTime1() {
		return createTime1;
	}
	public void setCreateTime1(Long createTime1) {
		this.createTime1 = createTime1;
		this.createTime1View = FormatUtil.formatDate(createTime1);
	}
	public Double getTotalPrice1() {
		return totalPrice1;
	}
	public void setTotalPrice1(Double totalPrice1) {
		this.totalPrice1 = totalPrice1;
	}
	
}
