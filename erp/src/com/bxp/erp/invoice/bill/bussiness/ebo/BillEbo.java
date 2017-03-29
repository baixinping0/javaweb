package com.bxp.erp.invoice.bill.bussiness.ebo;

import java.util.List;

import com.bxp.erp.invoice.bill.bussiness.ebi.BillEbi;
import com.bxp.erp.invoice.bill.dao.dao.BillDao;

public class BillEbo implements BillEbi{
	
	private BillDao billDao;

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	@Override
	public List<Object[]> getBuyBill() {
		return billDao.getBuyBillList();
	}
	
}
