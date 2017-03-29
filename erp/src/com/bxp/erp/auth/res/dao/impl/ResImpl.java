package com.bxp.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.auth.res.dao.dao.ResDao;
import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.res.vo.ResQueryModel;
import com.bxp.erp.util.exception.AppException;

public class ResImpl extends HibernateDaoSupport implements ResDao {

	@Override
	public void save(ResModel res) {
		this.getHibernateTemplate().save(res);
	}

	@Override
	public List<ResModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ResModel.class);

		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void delete(ResModel res) {
		this.getHibernateTemplate().delete(res);
	}

	@Override
	public ResModel get(Long uuid) {
		return this.getHibernateTemplate().get(ResModel.class, uuid);
	}

	@Override
	public void update(ResModel res) {
		this.getHibernateTemplate().update(res);
	}

	@Override
	public List<ResModel> getAll(ResQueryModel resq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ResModel.class);

		if (resq.getName() != null && resq.getName().trim().length() > 0)
			criteria.add(Restrictions.like("name", "%" + resq.getName().trim()
					+ "%"));
		if(resq.getUrl() != null && resq.getUrl().trim().length() > 0)
			criteria.add(Restrictions.like("url", "%" + resq.getUrl().trim() + "%"));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public List<ResModel> getAllByEmpId(Long uuid) {
		try{
			String hql = "select res from EmpModel emp join emp.roles role join role.ress res where emp.uuid = ?";
			return this.getHibernateTemplate().find(hql, uuid);
		}catch(Exception ex){
			throw new AppException(ex);
		}
		
	}
}
