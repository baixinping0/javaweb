package com.bxp.erp.invoice.order.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.order.dao.dao.OrderDao;
import com.bxp.erp.invoice.order.vo.OrderModel;
import com.bxp.erp.invoice.order.vo.OrderQueryModel;

public class OrderImpl extends HibernateDaoSupport implements OrderDao{

	@Override
	public void saveByOrder(OrderModel order) {
		this.getHibernateTemplate().save(order);
	}

	@Override
	public List<OrderModel> AllBuyOrder() {
		String hql = "from OrderModel o where o.orderType = ?";
		return this.getHibernateTemplate().find(hql, OrderModel.ORDER_ORDERTYPE_OF_BUY);
	}

	@Override
	public OrderModel getByOrderUuid(Long uuid) {
		return this.getHibernateTemplate().get(OrderModel.class, uuid);
	}

	@Override
	public List<OrderModel> getAllBuyOrder(OrderQueryModel orderq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderModel.class);
		addCondition(criteria, orderq);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	private void addCondition(DetachedCriteria criteria, OrderQueryModel orderq){
		//订单类型
		if(orderq.getType() != null && orderq.getType() != -1){
			criteria.add(Restrictions.eq("type", orderq.getType()));
		}
		//创建人
		if(orderq.getCreater() != null && orderq.getCreater().getName() != null && orderq.getCreater().getName().length() > 0){
			criteria.createAlias("creater", "c");
			criteria.add(Restrictions.like("c.name", "%" + orderq.getCreater().getName() + "%"));
		}
		//总数量
		if(orderq.getTotalNum() != null){
			criteria.add(Restrictions.ge("totalNum", orderq.getTotalNum()));
		}
		if(orderq.getTotalNum1() != null){
			criteria.add(Restrictions.le("totalNum", orderq.getTotalNum1()));
		}
		//总价格
		if(orderq.getTotalPrice() != null){
			criteria.add(Restrictions.ge("totalPrice", orderq.getTotalPrice()));
		}
		if(orderq.getTotalPrice1() != null){
			criteria.add(Restrictions.le("totalPrice", orderq.getTotalPrice1()));
		}
		//创建时间
		if(orderq.getCreateTime() != null){
			System.out.println("执行到查询1 ：" + orderq.getCreateTime() + "===" + orderq.getCreateTime1());
			criteria.add(Restrictions.ge("createTime", orderq.getCreateTime()));
			System.out.println("哈哈哈");
		}
		if(orderq.getCreateTime1() != null){
			System.out.println("执行到查询2 ：" + orderq.getCreateTime() + "===" + orderq.getCreateTime1());
			criteria.add(Restrictions.le("createTime", orderq.getCreateTime1() + 24 * 60 * 60 * 1000 - 1));
			System.out.println("哈哈哈");
		}
		//供应商
		if(orderq.getSupplier() != null && orderq.getSupplier().getUuid() != null && orderq.getSupplier().getUuid() != -1){
			criteria.add(Restrictions.eq("supplier.uuid", orderq.getSupplier().getUuid()));
		}
		//审核时间
		if(orderq.getCheckTime() != null){
			criteria.add(Restrictions.ge("checkTime", orderq.getCheckTime()));
		}
		if(orderq.getCheckTime1() != null){
			criteria.add(Restrictions.le("checkTime", orderq.getCheckTime1() + 24 * 60 * 60 * 1000 - 1));
		}
		//发货方式
		if(orderq.getSupplier() != null && orderq.getSupplier().getNeeds() != null && orderq.getSupplier().getNeeds() != -1){
			criteria.createAlias("supplier", "s");
			criteria.add(Restrictions.eq("s.needs", orderq.getSupplier().getNeeds()));
		}
		//审核人
		if(orderq.getChecker() != null && orderq.getChecker().getName() != null && orderq.getChecker().getName().length() > 0){
			criteria.createAlias("checker", "c");
			criteria.add(Restrictions.like("c.name", "%" + orderq.getChecker().getName() + "%"));
		}
		//跟单人
		if(orderq.getCompleter() != null && orderq.getCompleter().getName() != null && orderq.getCompleter().getName().length() > 0){
			criteria.createAlias("completer", "c");
			criteria.add(Restrictions.like("c.name", "%" + orderq.getCompleter().getName() + "%"));
		}
		
	}
	@Override
	public List<OrderModel> getAllOrderByOrderType(OrderQueryModel orderq, Integer[] orderTypes) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderModel.class);
		criteria.add(Restrictions.in("orderType", orderTypes));
		addCondition(criteria, orderq);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<OrderModel> getAllTaskByTaskTypes(OrderQueryModel orderq,
			Integer[] taskTypes) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderModel.class);
		addCondition(criteria, orderq);
		criteria.add(Restrictions.in("type", taskTypes));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<OrderModel> getOrderByCompleter(Long completerUuid,
			OrderQueryModel orderq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderModel.class);
		addCondition(criteria, orderq);
		criteria.add(Restrictions.eq("completer.uuid", completerUuid));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<OrderModel> getAllInstore(OrderQueryModel orderq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderModel.class);
		addCondition(criteria, orderq);
		return this.getHibernateTemplate().findByCriteria(criteria);
	}


}
