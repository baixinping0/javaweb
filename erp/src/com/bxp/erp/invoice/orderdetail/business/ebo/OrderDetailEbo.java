package com.bxp.erp.invoice.orderdetail.business.ebo;

import com.bxp.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import com.bxp.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

public class OrderDetailEbo implements OrderDetailEbi{
	private OrderDetailDao orderDetailDao;

	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	@Override
	public OrderDetailModel getByUuid(Long uuid) {
		return orderDetailDao.getByUuid(uuid);
	}
	
	
}
