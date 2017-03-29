package com.bxp.erp.invoice.orderdetail.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

public class OrderDetailImpl extends HibernateDaoSupport implements OrderDetailDao{

	@Override
	public OrderDetailModel getByUuid(Long uuid) {
		return this.getHibernateTemplate().get(OrderDetailModel.class, uuid);
	}

}
