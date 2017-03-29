package com.bxp.erp.invoice.goodstype.web;

import java.util.List;

import com.bxp.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.bxp.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import com.bxp.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsTypeAction extends ActionSupport {
	private GoodsTypeEbi goodsTypeEbi;
	private SupplierEbi supplierEbi;
	public GoodsTypeModel goodsType = new GoodsTypeModel();
	public GoodsTypeQueryModel goodsTypeq = new GoodsTypeQueryModel();

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public String list() {
		// 获取所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		ActionContext.getContext().put("supplierList", supplierList);

		// 获取所有的商品类型
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(goodsTypeq);
		ActionContext.getContext().put("goodsTypeList", goodsTypeList);
		return "list";
	}

	public String input() {
		if (goodsType.getUuid() != null)
			goodsType = goodsTypeEbi.get(goodsType.getUuid());
		// 获取所有的供应商
		List<SupplierModel> supplierList = supplierEbi.getAll();
		ActionContext.getContext().put("supplierList", supplierList);
		return "input";
	}

	public String save() {
		if (goodsType.getUuid() == null)
			goodsTypeEbi.save(goodsType);
		else
			goodsTypeEbi.update(goodsType);
		return "doList";
	}

	public String delete() {
		goodsTypeEbi.delete(goodsType);
		return "doList";
	}

	// AJAX--------------------
	// AJAX--------------------
	// AJAX--------------------
	// AJAX--------------------
	// AJAX--------------------
	/*
	 * 1、导入struts-json响应的jar包。
	 * 2、设置struts的返回结果集的类型为json
	 * 3、设置对应的action所在包继承json-default
	 * 4、对将要返回的数据提供get方法
	 */
	private List<GoodsTypeModel> goodsTypeList;
	public List<GoodsTypeModel> getGoodsTypeList() {
		return goodsTypeList;
	}
	public String ajaxGetBySupplierUuid() {
		goodsTypeList = goodsTypeEbi.getAll(goodsType.getSupplier().getUuid());
		return "ajaxGetBySupplierUuid";
	}
}
