package com.bxp.erp.auth.role.dao.dao;

import java.util.List;

import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.auth.role.vo.RoleQueryModel;

public interface RoleDao{

	public void save(RoleModel role);

	public List<RoleModel> getAll();

	public void delete(RoleModel role);

	public RoleModel get(Long uuid);

	public void update(RoleModel role);

	public List<RoleModel> getAll(RoleQueryModel roleq);

}
