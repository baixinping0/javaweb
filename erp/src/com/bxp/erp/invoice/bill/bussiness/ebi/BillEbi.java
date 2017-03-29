package com.bxp.erp.invoice.bill.bussiness.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BillEbi {

	public List<Object[]> getBuyBill();

}
