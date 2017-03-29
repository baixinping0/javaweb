package com.bxp.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

public class GoodsTypeImpl extends HibernateDaoSupport implements GoodsTypeDao {

	@Override
	public void save(GoodsTypeModel goodsType) {
		this.getHibernateTemplate().save(goodsType);
	}

	@Override
	public List<GoodsTypeModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GoodsTypeModel.class);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public GoodsTypeModel get(Long uuid) {
		return this.getHibernateTemplate().get(GoodsTypeModel.class, uuid);
	}

	@Override
	public void delete(GoodsTypeModel goodsType) {
		this.getHibernateTemplate().delete(goodsType);
	}

	@Override
	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel goodsTypeq) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(GoodsTypeModel.class);
		if (goodsTypeq.getName() != null
				&& goodsTypeq.getName().trim().length() > 0)
			criteria.add(Restrictions.like("name", "%"
					+ goodsTypeq.getName().trim() + "%"));
		if (goodsTypeq.getSupplier() != null && goodsTypeq.getSupplier().getUuid() != null
				&& goodsTypeq.getSupplier().getUuid() != -1)
			criteria.add(Restrictions.eq("supplier", goodsTypeq.getSupplier()));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<GoodsTypeModel> getAll(Long supplierUuid) {
		String hql = "from GoodsTypeModel where supplier.uuid = ?";
		return this.getHibernateTemplate().find(hql, supplierUuid);
	}

	@Override
	public List<GoodsTypeModel> getAllHasGoods(Long supplierUuid) {
		String hql = "select distinct gtm from GoodsModel gm join gm.goodsType gtm where gtm.supplier.uuid = ?";
		return this.getHibernateTemplate().find(hql, supplierUuid);
	}
}
