package com.bxp.erp.auth.role.web;

import java.util.List;
import java.util.Set;

import com.bxp.erp.auth.menu.business.ebi.MenuEbi;
import com.bxp.erp.auth.menu.vo.MenuModel;
import com.bxp.erp.auth.res.business.ebi.ResEbi;
import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.role.business.ebi.RoleEbi;
import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.auth.role.vo.RoleQueryModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport {
	public RoleModel role = new RoleModel();
	public RoleQueryModel roleq = new RoleQueryModel();
	public Long[] resUuids;
	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	
	public Long[] menuUuids;
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi; 
	}

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public String list() {
		List<RoleModel> roleList = roleEbi.getAll(roleq);
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	public String input() {
		//获取资源列表
		List<ResModel> resList = resEbi.getAll();
		ActionContext.getContext().put("resList", resList);
		
		//获取所有的菜单
		List<MenuModel> menuList = menuEbi.getAll();
		ActionContext.getContext().put("menuList", menuList);
		
		if (role.getUuid() != null){
			role = roleEbi.get(role.getUuid());
			
			resUuids = new Long[role.getRess().size()];
			int i = 0;
			for(ResModel res : role.getRess()){
				resUuids[i] = res.getUuid();
				i++;
			}
			
			menuUuids = new Long[role.getMenus().size()];
			int j = 0;
			for(MenuModel menu : role.getMenus()){
				menuUuids[j] = menu.getUuid();
				j++;
			}
			
		}
		return "input";
	}

	public String save() {
		if (role.getUuid() == null)
			roleEbi.save(role, resUuids, menuUuids);
		else
			roleEbi.update(role, resUuids, menuUuids);
		return "doList";
	}

	public String delete() {
		roleEbi.delete(role);
		return "doList";
	}
}
