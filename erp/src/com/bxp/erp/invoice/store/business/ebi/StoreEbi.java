package com.bxp.erp.invoice.store.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.invoice.store.vo.StoreModel;

@Transactional
public interface StoreEbi {

	public  void save(StoreModel store);

	public List<StoreModel> getAll();

}
