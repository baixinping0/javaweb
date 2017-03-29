package com.bxp.erp.invoice.storeoper.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.storeoper.dao.dao.StoreOperDao;
import com.bxp.erp.invoice.storeoper.vo.StoreOperModel;

public class StoreOperImpl extends HibernateDaoSupport implements StoreOperDao{

	@Override
	public void save(StoreOperModel storeOper) {
		this.getHibernateTemplate().save(storeOper);
	}

}
