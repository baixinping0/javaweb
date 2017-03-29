package com.bxp.erp.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.supplier.dao.dao.SupplierDao;
import com.bxp.erp.invoice.supplier.vo.SupplierModel;
import com.bxp.erp.invoice.supplier.vo.SupplierQueryModel;

public class SupplierImpl extends HibernateDaoSupport implements SupplierDao {

	@Override
	public void save(SupplierModel supplier) {
		this.getHibernateTemplate().save(supplier);
	}

	@Override
	public List<SupplierModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(SupplierModel.class);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
	@Override
	public SupplierModel getSupplierByUuid(Long uuid) {
		return this.getHibernateTemplate().get(SupplierModel.class, uuid);
	}

	@Override
	public void delete(SupplierModel supplier) {
		this.getHibernateTemplate().delete(supplier);
	}

	@Override
	public List<SupplierModel> getAll(SupplierQueryModel supplierq) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(SupplierModel.class);
		if (supplierq.getName() != null && supplierq.getName().length() > 0)
			criteria.add(Restrictions.like("name", "%"
					+ supplierq.getName().trim() + "%"));
		if (supplierq.getAddress() != null && supplierq.getAddress().length() > 0)
			criteria.add(Restrictions.like("address", "%"
					+ supplierq.getAddress().trim() + "%"));
		if (supplierq.getContact() != null && supplierq.getContact().length() > 0)
			criteria.add(Restrictions.like("contact", "%"
					+ supplierq.getContact().trim() + "%"));
		if (supplierq.getTele() != null && supplierq.getTele().length() > 0)
			criteria.add(Restrictions.like("tele", "%"
					+ supplierq.getTele().trim() + "%"));
		if (supplierq.getNeeds() != null && supplierq.getNeeds() != -1)
			criteria.add(Restrictions.eq("needs", supplierq.getNeeds()));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<SupplierModel> getAllHasGoodsType() {
		String hql = "select distinct  sm from GoodsTypeModel gm join gm.supplier sm";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<SupplierModel> getAllHasGtmAndGm() {

		String hql = "select distinct sm from GoodsModel gm join gm.goodsType gtm join gtm.supplier sm";
		return this.getHibernateTemplate().find(hql);
	}

}
