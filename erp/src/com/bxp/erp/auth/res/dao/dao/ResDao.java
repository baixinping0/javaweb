package com.bxp.erp.auth.res.dao.dao;

import java.util.List;

import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.res.vo.ResQueryModel;

public interface ResDao {

	public void save(ResModel res);

	public List<ResModel> getAll();

	public void delete(ResModel res);

	public ResModel get(Long uuid);

	public void update(ResModel res);

	public List<ResModel> getAll(ResQueryModel resq);

	public List<ResModel> getAllByEmpId(Long uuid);

}
