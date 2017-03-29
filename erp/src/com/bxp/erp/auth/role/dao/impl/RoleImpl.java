package com.bxp.erp.auth.role.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.auth.role.dao.dao.RoleDao;
import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.auth.role.vo.RoleQueryModel;

public class RoleImpl extends HibernateDaoSupport implements RoleDao {

	@Override
	public void save(RoleModel role) {
		this.getHibernateTemplate().save(role);
	}

	@Override
	public List<RoleModel> getAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);

		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public void delete(RoleModel role) {
		this.getHibernateTemplate().delete(role);
	}

	@Override
	public RoleModel get(Long uuid) {
		return this.getHibernateTemplate().get(RoleModel.class, uuid);
	}

	@Override
	public void update(RoleModel role) {
		this.getHibernateTemplate().update(role);
	}

	@Override
	public List<RoleModel> getAll(RoleQueryModel roleq) {
		DetachedCriteria criteria = DetachedCriteria.forClass(RoleModel.class);
		if (roleq.getName() != null && roleq.getName().trim().length() > 0)
			criteria.add(Restrictions.like("name", "%" + roleq.getName().trim()
					+ "%"));
		if (roleq.getCode() != null && roleq.getCode().trim().length() > 0)
			criteria.add(Restrictions.like("code", "%" + roleq.getCode().trim()
					+ "%"));
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
}
