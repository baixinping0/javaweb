package com.bxp.erp.auth.dep.dao.dao;

import java.util.List;

import com.bxp.erp.auth.dep.vo.DepModel;
import com.bxp.erp.auth.dep.vo.DepQueryModel;

public interface DepDao{

	public void save(DepModel dep);

	public List<DepModel> getAll(DepQueryModel depq);

	public DepModel findByUuid(Long uuid);

	public void update(DepModel dep);

	public void delete(DepModel dep);

	public List<DepModel> getAll(DepQueryModel depq, Integer pageNum,
			Integer pageCount);
	
	public Integer getCount(DepQueryModel depq);

	public List<DepModel> getAll();

}
