package com.bxp.erp.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

@Transactional
public interface OrderDetailEbi {

	public OrderDetailModel getByUuid(Long uuid);

}
