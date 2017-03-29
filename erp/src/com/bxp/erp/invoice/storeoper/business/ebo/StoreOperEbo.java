package com.bxp.erp.invoice.storeoper.business.ebo;

import com.bxp.erp.invoice.storeoper.business.ebi.StoreOperEbi;
import com.bxp.erp.invoice.storeoper.dao.dao.StoreOperDao;

public class StoreOperEbo implements StoreOperEbi{
	private StoreOperDao storeOperDao;

	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}
	
	
}
