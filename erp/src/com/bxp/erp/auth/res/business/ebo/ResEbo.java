package com.bxp.erp.auth.res.business.ebo;

import java.util.List;

import com.bxp.erp.auth.res.business.ebi.ResEbi;
import com.bxp.erp.auth.res.dao.dao.ResDao;
import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.res.vo.ResQueryModel;

public class ResEbo implements ResEbi{
	private ResDao resDao;
	
	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}

	@Override
	public void save(ResModel res) {
		resDao.save(res);
	}

	@Override
	public List<ResModel> getAll() {
		return resDao.getAll();
	}

	@Override
	public void delete(ResModel res) {
		resDao.delete(res);
	}

	@Override
	public ResModel get(Long uuid) {
		return resDao.get(uuid);
	}

	@Override
	public void update(ResModel res) {
		resDao.update(res);
	}

	@Override
	public List<ResModel> getAll(ResQueryModel resq) {
		return resDao.getAll(resq);
	}

	@Override
	public List<ResModel> getAllByEmp(Long uuid) {

		return resDao.getAllByEmpId(uuid);
	}
}
