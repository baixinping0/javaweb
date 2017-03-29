package com.bxp.erp.invoice.supplier.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.invoice.supplier.vo.SupplierQueryModel;

public interface SupplierDao {

	public void save(SupplierModel supplier);

	public List<SupplierModel> getAll();

	public SupplierModel getSupplierByUuid(Long uuid);

	public void delete(SupplierModel supplier);

	public List<SupplierModel> getAll(SupplierQueryModel supplierq);

	/**
	 * 获取所有的具有商品类别的供应商
	 * @return
	 */
	public List<SupplierModel> getAllHasGoodsType();

	public List<SupplierModel> getAllHasGtmAndGm();
	
}
