package com.bxp.erp.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.res.vo.ResQueryModel;

@Transactional
public interface ResEbi {

	public void save(ResModel res);

	public List<ResModel> getAll();

	public void delete(ResModel res);

	public ResModel get(Long uuid);

	public void update(ResModel res);

	public List<ResModel> getAll(ResQueryModel resq);

	public List<ResModel> getAllByEmp(Long uuid);

}
