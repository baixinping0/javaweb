package com.bxp.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.invoice.supplier.vo.SupplierQueryModel;

@Transactional
public interface SupplierEbi {

	public void save(SupplierModel supplier);

	public List<SupplierModel> getAll();

	public SupplierModel get(Long uuid);

	public void update(SupplierModel supplier);

	public void delete(SupplierModel supplier);

	public List<SupplierModel> getAll(SupplierQueryModel supplierq);

	/**
	 * 获取所有的具有商品类型的供应商
	 * @return
	 */
	public List<SupplierModel> getAllHasGoodsType();

	/**
	 * 获取所有的具有商品类别冰球每个商品类别都具有商品的供应商
	 * @return
	 */
	public List<SupplierModel> getAllHasGtmAndGm();

}
