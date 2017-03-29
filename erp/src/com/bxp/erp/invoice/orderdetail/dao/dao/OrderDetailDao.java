package com.bxp.erp.invoice.orderdetail.dao.dao;

import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

public interface OrderDetailDao {

	public OrderDetailModel getByUuid(Long uuid);

}
