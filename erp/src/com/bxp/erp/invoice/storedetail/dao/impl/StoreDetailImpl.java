package com.bxp.erp.invoice.storedetail.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bxp.erp.invoice.storedetail.dao.dao.StoreDetailDao;
import com.bxp.erp.invoice.storedetail.vo.StoreDetailModel;

public class StoreDetailImpl extends HibernateDaoSupport implements StoreDetailDao{

	@Override
	public StoreDetailModel getBuyStoreAndGoods(Long storeUuid, Long goodsUuid) {
		String hql = "from StoreDetailModel where store.uuid = ? and goods.uuid = ?";
		List<StoreDetailModel> storeDetailList = this.getHibernateTemplate().find(hql, storeUuid, goodsUuid);
		return storeDetailList.size() > 0 ? storeDetailList.get(0):null;
	}

	@Override
	public void save(StoreDetailModel storeDetail) {
		this.getHibernateTemplate().save(storeDetail);
	}

	@Override
	public List<Object[]> getWarnInfo() {
		//使用sql进行查询
		
		String sql = "select g.name,g.minNum > sum(sd.num) min,g.maxNum < sum(sd.num) max from tbl_storeDetail sd, tbl_goods g where  g.uuid = sd.goodsUuid  group by g.uuid";
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();
		SQLQuery sq = session.createSQLQuery(sql);
		return sq.list();
	}

}
