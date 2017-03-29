package com.bxp.erp.auth.role.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.auth.role.vo.RoleQueryModel;

@Transactional
public interface RoleEbi {

	public void save(RoleModel role);

	public List<RoleModel> getAll();

	public void delete(RoleModel role);

	public RoleModel get(Long uuid);

	public void update(RoleModel role);

	public List<RoleModel> getAll(RoleQueryModel roleq);

	public void save(RoleModel role, Long[] resUuids, Long[] menuUuids);

	public void update(RoleModel role, Long[] resUuids, Long[] menuUuids);
}
