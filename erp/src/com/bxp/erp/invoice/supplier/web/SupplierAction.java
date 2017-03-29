package com.bxp.erp.invoice.supplier.web;

import java.util.List;

import com.bxp.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.invoice.supplier.vo.SupplierQueryModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupplierAction extends ActionSupport {
	private SupplierEbi supplierEbi;

	public SupplierModel supplier = new SupplierModel();
	public SupplierQueryModel supplierq = new SupplierQueryModel();

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public String list() {
		// 获取所有的数据
		List<SupplierModel> supplierList = supplierEbi.getAll(supplierq);
		ActionContext.getContext().put("supplierList", supplierList);
		return "list";
	}

	public String input() {
		if (supplier.getUuid() != null)
			supplier = supplierEbi.get(supplier.getUuid());
		return "input";
	}

	public String save() {
		if (supplier.getUuid() == null)
			supplierEbi.save(supplier);
		else
			supplierEbi.update(supplier);
		return "doList";
	}
	
	public String delete(){
		supplierEbi.delete(supplier);
		return "doList";
	}
}
