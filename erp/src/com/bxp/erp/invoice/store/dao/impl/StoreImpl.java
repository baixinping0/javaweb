package com.bxp.erp.invoice.store.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.store.dao.dao.StoreDao;
import com.bxp.erp.invoice.store.vo.StoreModel;

public class StoreImpl extends HibernateDaoSupport implements StoreDao{

	@Override
	public void save(StoreModel store) {
		this.getHibernateTemplate().save(store);
	}

	@Override
	public List<StoreModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(StoreModel.class);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

}
