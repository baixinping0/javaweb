package com.bxp.erp.invoice.goodstype.vo;

import com.bxp.erp.invoice.supplier.vo.SupplierModel;

public class GoodsTypeModel {
	private Long uuid;
	private String name;
	private SupplierModel supplier;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SupplierModel getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	
	
}
