package com.bxp.erp.invoice.goods.web;

import java.util.List;

import com.bxp.erp.invoice.goods.business.ebi.GoodsEbi;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goods.vo.GoodsQueryModel;
import com.bxp.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsAction extends ActionSupport {
	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	public GoodsModel goods = new GoodsModel();
	public GoodsQueryModel goodsq = new GoodsQueryModel();

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public String list() {
		// 获取所有的供应商
		List<SupplierModel> supplierLisr = supplierEbi.getAll();
		ActionContext.getContext().put("supplierLisr", supplierLisr);
		// 获取所有的商品
		List<GoodsModel> goodsList = goodsEbi.getAll(goodsq);
		ActionContext.getContext().put("goodsList", goodsList);
		return "list";
	}

	public String input() {
		if (goods.getUuid() != null)
			goods = goodsEbi.get(goods.getUuid());
		// 获取所有的具有商品类型供应商
		List<SupplierModel> supplierList = supplierEbi.getAllHasGoodsType();
		ActionContext.getContext().put("supplierList", supplierList);
		// 获取所有的商品类别,通过供应商的ID
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(supplierList
				.get(0).getUuid());
		ActionContext.getContext().put("goodsTypeList", goodsTypeList);

		return "input";
	}

	public String save() {
		if (goods.getUuid() == null)
			goodsEbi.save(goods);
		else
			goodsEbi.update(goods);
		return "doList";
	}
	public String delete(){
		goodsEbi.delete(goods);
		return "doList";
	}
}
