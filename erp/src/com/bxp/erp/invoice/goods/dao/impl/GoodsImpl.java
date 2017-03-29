package com.bxp.erp.invoice.goods.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.goods.dao.dao.GoodsDao;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goods.vo.GoodsQueryModel;

public class GoodsImpl extends HibernateDaoSupport implements GoodsDao {

	@Override
	public void save(GoodsModel goods) {
		this.getHibernateTemplate().save(goods);
	}

	@Override
	public List<GoodsModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(GoodsModel.class);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<GoodsModel> getAll(GoodsQueryModel goodsq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GoodsModel.class);
		if (goodsq.getGoodsType() != null
				&& goodsq.getGoodsType().getSupplier() != null
				&& goodsq.getGoodsType().getSupplier().getUuid() != null
				&& goodsq.getGoodsType().getSupplier().getUuid() != -1) {
			criteria.createAlias("goodsType", "g");
			criteria.add(Restrictions.eq("g.supplier", goodsq.getGoodsType()
					.getSupplier()));
		}
		if (goodsq.getName() != null && goodsq.getName().trim().length() > 0)
			criteria.add(Restrictions.like("name", "%"
					+ goodsq.getName().trim() + "%"));
		if (goodsq.getProducer() != null && goodsq.getProducer().trim().length() > 0)
			criteria.add(Restrictions.like("producer", "%"
					+ goodsq.getProducer().trim() + "%"));
		if (goodsq.getUnit() != null && goodsq.getUnit().length() > 0)
			criteria.add(Restrictions.eq("unit", goodsq.getUnit().trim()));
		if (goodsq.getInPrice() != null)
			criteria.add(Restrictions.gt("inPrice", goodsq.getInPrice()));
		if (goodsq.getInPrice1() != null)
			criteria.add(Restrictions.lt("inPrice", goodsq.getInPrice1()));
		if (goodsq.getOutPrice() != null)
			criteria.add(Restrictions.gt("outPrice", goodsq.getOutPrice()));
		if (goodsq.getOutPrice1() != null)
			criteria.add(Restrictions.lt("outPrice", goodsq.getOutPrice1()));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public GoodsModel get(Long uuid) {
		return this.getHibernateTemplate().get(GoodsModel.class, uuid);
	}

	@Override
	public void delete(GoodsModel goods) {
		this.getHibernateTemplate().delete(goods);
	}

	@Override
	public List<GoodsModel> getAll(Long goodsTypeUuid) {
		String hql = "from GoodsModel where goodsType.uuid = ?";
		return this.getHibernateTemplate().find(hql, goodsTypeUuid);
	}

	@Override
	public void useNumUpdate() {
		String hql = "update GoodsModel g set useNum = (select count(od.num) from OrderDetailModel od where od.goods.uuid = g.uuid)";
		this.getHibernateTemplate().bulkUpdate(hql);
		/*
		 * 
update 
	tbl_goods g
set 
	useNum = (
		select 
			count(od.num)
		from 
			tbl_orderDetail od
		where
			g.uuid = od.goodsUuid
	);
		*/
	}
}
