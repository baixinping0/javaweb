package com.bxp.erp.invoice.store.business.ebo;

import java.util.List;

import com.bxp.erp.invoice.store.business.ebi.StoreEbi;
import com.bxp.erp.invoice.store.dao.dao.StoreDao;
import com.bxp.erp.invoice.store.vo.StoreModel;

public class StoreEbo implements StoreEbi{
	private StoreDao storeDao;

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public void save(StoreModel store) {
		storeDao.save(store);
	}

	@Override
	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}
	
}
