package com.bxp.erp.auth.menu.dao.dao;

import java.util.List;

import com.bxp.erp.auth.menu.vo.MenuModel;
import com.bxp.erp.auth.menu.vo.MenuQueryModel;

public interface MenuDao {

	/**
	 * 获取所有父菜单为0或1的菜单
	 * @return
	 */
	public List<MenuModel> getALlParentIsOneOrZero();

	public void save(MenuModel menu);

	public List<MenuModel> getAll();

	public MenuModel getMenuByUuid(Long uuid);

	public void delete(MenuModel menu);

	public List<MenuModel> getAll(MenuQueryModel menuq);

	public List<MenuModel> getAllOneLevelByEmpUuid(Long uuid);

	public List<MenuModel> getAllTwoLevelByEmpAndOneLevel(Long uuid,
			Long menuUuid);

	public Integer getCount();

	public List<MenuModel> getALl(MenuQueryModel menuq, Integer pageNum,
			Integer pageCount);

}
