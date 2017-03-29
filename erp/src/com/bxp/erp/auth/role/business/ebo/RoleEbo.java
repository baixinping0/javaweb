package com.bxp.erp.auth.role.business.ebo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bxp.erp.auth.menu.vo.MenuModel;
import com.bxp.erp.auth.res.dao.dao.ResDao;
import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.role.business.ebi.RoleEbi;
import com.bxp.erp.auth.role.dao.dao.RoleDao;
import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.auth.role.vo.RoleQueryModel;

public class RoleEbo implements RoleEbi{
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public void save(RoleModel role) {
		roleDao.save(role);
	}
	@Override
	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}
	@Override
	public void delete(RoleModel role) {
		roleDao.delete(role);
	}
	@Override
	public RoleModel get(Long uuid) {
		
		return roleDao.get(uuid);
	}
	@Override
	public void update(RoleModel role) {
		
		RoleModel rolem = roleDao.get(role.getUuid());
		rolem.setName(role.getName());
		rolem.setCode(role.getCode());
		rolem.setRess(role.getRess());
		rolem.setMenus(role.getMenus());
	}
	@Override
	public List<RoleModel> getAll(RoleQueryModel roleq) {
		return roleDao.getAll(roleq);
	}
	@Override
	public void save(RoleModel role, Long[] resUuids , Long[] menuUuids) {
		Set<ResModel> ress = new HashSet<ResModel>();
		for(Long uuid : resUuids){
			ResModel res = new ResModel();
			res.setUuid(uuid);;
			ress.add(res);
		}
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid : menuUuids){
			MenuModel menu = new MenuModel();
			menu.setUuid(uuid);;
			menus.add(menu);
		}
		
		role.setMenus(menus);
		role.setRess(ress);
		save(role);
	}
	@Override
	public void update(RoleModel role, Long[] resUuids, Long[] menuUuids) {
		Set<ResModel> ress = new HashSet<ResModel>();
		for(Long uuid : resUuids){
			ResModel res = new ResModel();
			res.setUuid(uuid);;
			ress.add(res);
		}
		
		Set<MenuModel> menus = new HashSet<MenuModel>();
		for(Long uuid : menuUuids){
			MenuModel menu = new MenuModel();
			menu.setUuid(uuid);;
			menus.add(menu);
		}
		
		role.setMenus(menus);
		role.setRess(ress);
		update(role);
	}
}
