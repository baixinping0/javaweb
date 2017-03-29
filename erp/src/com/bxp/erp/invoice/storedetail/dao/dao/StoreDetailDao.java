package com.bxp.erp.invoice.storedetail.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.storedetail.vo.StoreDetailModel;

public interface StoreDetailDao {

	public StoreDetailModel getBuyStoreAndGoods(Long storeUuid, Long uuid);

	public void save(StoreDetailModel storeDetail);

	public List<Object[]> getWarnInfo();

}
