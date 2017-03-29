package com.bxp.erp.auth.emp.dao.dao;

import java.util.List;

import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.auth.emp.vo.EmpQueryModel;

public interface EmpDao {

	public EmpModel getByNameAndPwd(String userName, String pwd);

	public void save(EmpModel emp);

	public List<EmpModel> getAll();

	public EmpModel get(Long uuid);

	public void update(EmpModel emp);

	public void delete(EmpModel emp);

	public List<EmpModel> getAll(EmpQueryModel empq);

	public boolean updatePwdByPwnAndUsername(String userName, String pwd,
			String newPwd);

	public List<EmpModel> getEmpByDep(Long depUuid);


}
