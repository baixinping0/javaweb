package com.bxp.erp.invoice.bill.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.bill.dao.dao.BillDao;
import com.bxp.erp.invoice.orderdetail.vo.OrderDetailModel;

public class BillImpl extends HibernateDaoSupport implements BillDao{

	@Override
	public List<Object[]> getBuyBillList() {
	
	DetachedCriteria criteria = DetachedCriteria.forClass(OrderDetailModel.class);
	
	ProjectionList pList = Projections.projectionList();
	//分组
	pList.add(Projections.groupProperty("goods"));
	//select内容
	pList.add(Projections.sum("num"));
	
	criteria.setProjection(pList);
	
	
	/*
	select 
		g.uuid, 
		g.name,
		sum(od.num) 
	from 
		tbl_orderDetail od, 
		tbl_goods g
	where 
		od.goodsUuid = g.uuid 
	group by 
		goodsUuid;*/
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

}
