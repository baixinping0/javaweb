package com.bxp.erp.invoice.store.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.store.vo.StoreModel;

public interface StoreDao {

	public void save(StoreModel store);

	public List<StoreModel> getAll();

}
