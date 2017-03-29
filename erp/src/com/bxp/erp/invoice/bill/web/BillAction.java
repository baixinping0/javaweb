package com.bxp.erp.invoice.bill.web;

import java.util.List;

import com.bxp.erp.invoice.bill.bussiness.ebi.BillEbi;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BillAction extends ActionSupport {
	private BillEbi billEbi;

	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String buyBillList() {
		List<Object[]> billList = billEbi.getBuyBill();
//		for (Object[] objArry : billList) {
//			// System.out.println(((OrderDetailModel)objArry[1]).getGoods().getName());
//			// for(Object obj)
//			System.out.println(objArry[0]);
//			System.out.println(objArry[1]);
//		}
		ActionContext.getContext().put("billList", billList);
		return "buyBillList";
	}
}
