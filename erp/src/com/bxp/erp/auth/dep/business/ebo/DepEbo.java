package com.bxp.erp.auth.dep.business.ebo;

import java.util.List;

import com.bxp.erp.auth.dep.business.ebi.DepEbi;
import com.bxp.erp.auth.dep.dao.dao.DepDao;
import com.bxp.erp.auth.dep.vo.DepModel;
import com.bxp.erp.auth.dep.vo.DepQueryModel;

public class DepEbo implements DepEbi{
	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	@Override
	public void save(DepModel dep) {
		depDao.save(dep);
		
	}

	@Override
	public List<DepModel> getAll(DepQueryModel depq) {
		return depDao.getAll(depq);
	}

	@Override
	public DepModel findByUuid(Long uuid) {
		
		return depDao.findByUuid(uuid);
	}

	@Override
	public void update(DepModel dep) {
		// TODO Auto-generated method stub
		depDao.update(dep);
	}

	@Override
	public void delete(DepModel dep) {
		depDao.delete(dep);
	}

	@Override
	public List<DepModel> getAll(DepQueryModel depq, Integer pageNum,
			Integer pageCount) {
		return depDao.getAll(depq, pageNum, pageCount);
	}

	@Override
	public Integer getCount(DepQueryModel depq) {
		return depDao.getCount(depq);
	}

	@Override
	public List<DepModel> getAll() {

		return depDao.getAll();
	}

}
