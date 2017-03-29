package com.bxp.erp.invoice.storedetail.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StoreDetailEbi {

	public List<Object[]> getWarnInfo();

}
