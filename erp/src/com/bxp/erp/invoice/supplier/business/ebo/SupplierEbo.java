package com.bxp.erp.invoice.supplier.business.ebo;

import java.util.List;

import com.bxp.erp.invoice.supplier.business.ebi.SupplierEbi;
import com.bxp.erp.invoice.supplier.dao.dao.SupplierDao;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.invoice.supplier.vo.SupplierQueryModel;

public class SupplierEbo implements SupplierEbi {
	private SupplierDao supplierDao;

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	@Override
	public void save(SupplierModel supplier) {
		supplierDao.save(supplier);
	}

	@Override
	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	@Override
	public SupplierModel get(Long uuid) {
		return supplierDao.getSupplierByUuid(uuid);
	}

	@Override
	public void update(SupplierModel supplier) {
		SupplierModel cSupplier = get(supplier.getUuid());
		cSupplier.setName(supplier.getName());
		cSupplier.setAddress(supplier.getAddress());
		cSupplier.setContact(supplier.getContact());
		cSupplier.setTele(supplier.getTele());
		cSupplier.setNeeds(supplier.getNeeds());
	}

	@Override
	public void delete(SupplierModel supplier) {
		supplierDao.delete(supplier);
	}

	@Override
	public List<SupplierModel> getAll(SupplierQueryModel supplierq) {
		return supplierDao.getAll(supplierq);
	}

	@Override
	public List<SupplierModel> getAllHasGoodsType() {
		return supplierDao.getAllHasGoodsType();
	}

	@Override
	public List<SupplierModel> getAllHasGtmAndGm() {
		return supplierDao.getAllHasGtmAndGm();
	}

}
