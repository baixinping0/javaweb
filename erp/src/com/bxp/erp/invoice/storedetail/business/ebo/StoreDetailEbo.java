package com.bxp.erp.invoice.storedetail.business.ebo;

import java.util.List;

import com.bxp.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import com.bxp.erp.invoice.storedetail.dao.dao.StoreDetailDao;

public class StoreDetailEbo implements StoreDetailEbi {

	private StoreDetailDao storeDetailDao;

	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	@Override
	public List<Object[]> getWarnInfo() {
		// TODO Auto-generated method stub
		return storeDetailDao.getWarnInfo();
	}
	
}
