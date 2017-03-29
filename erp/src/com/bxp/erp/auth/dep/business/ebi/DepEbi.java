package com.bxp.erp.auth.dep.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.auth.dep.vo.DepModel;
import com.bxp.erp.auth.dep.vo.DepQueryModel;

@Transactional
public interface DepEbi {

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
