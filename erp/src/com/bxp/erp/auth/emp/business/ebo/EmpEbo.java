package com.bxp.erp.auth.emp.business.ebo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bxp.erp.auth.emp.business.ebi.EmpEbi;
import com.bxp.erp.auth.emp.dao.dao.EmpDao;
import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.auth.emp.vo.EmpQueryModel;
import com.bxp.erp.auth.role.vo.RoleModel;
import com.bxp.erp.util.format.MD5Utils;

public class EmpEbo implements EmpEbi {
	//注入数据层接口
	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	@Override
	public EmpModel login(String userName, String pwd, String loginIp) {
		//md5加密
		pwd = MD5Utils.md5(pwd);
		//调用数据层
		EmpModel emp = empDao.getByNameAndPwd(userName, pwd);
		if(emp != null){
			//登录成功
			emp.setLastLoginIp(loginIp);
			emp.setLastLoginTime(System.currentTimeMillis());
			emp.setLoginTimes(emp.getLoginTimes() + 1);
		}
		return emp;
	}
	@Override
	public void save(EmpModel emp) {
		//将密码进行加密
		emp.setPwd(MD5Utils.md5(emp.getPwd()));
		
		//设置最后登录IP默认值
		emp.setLastLoginIp("-");
		//设置默认最后登录时间
		emp.setLastLoginTime(System.currentTimeMillis());
		//获取登录次数
		emp.setLoginTimes(0);
		
		empDao.save(emp);
	}
	@Override
	public List<EmpModel> getAll() {
		return empDao.getAll();
	}
	@Override
	public EmpModel get(Long uuid) {
		return empDao.get(uuid);
	}
	@Override
	public void update(EmpModel emp) {
		/**
		 * 此处在进行数据更新的时候，不能够采用全部更新
		 * 只更新：真实姓名， 电子邮箱， 电话号码， 性别，地址，出生日期，所属部门
		 * 所以采用快照更新
		 */
		EmpModel em = empDao.get(emp.getUuid());
		em.setName(emp.getName());
		em.setEmail(emp.getEmail());
		em.setTele(emp.getTele());
		em.setGender(emp.getGender());
		em.setAddress(emp.getAddress());
		em.setBirthday(emp.getBirthday());
		em.setDep(emp.getDep());
		em.setRoles(emp.getRoles());
		//empDao.update(emp);
	}
	@Override
	public void delete(EmpModel emp) {
		empDao.delete(emp);
	}
	@Override
	public List<EmpModel> getAll(EmpQueryModel empq) {
		return empDao.getAll(empq);
	}
	@Override
	public boolean changePed(String userName, String pwd, String newPwd) {
		//对密码进行加密
		pwd = MD5Utils.md5(pwd);
		newPwd = MD5Utils.md5(newPwd);
		return empDao.updatePwdByPwnAndUsername(userName, pwd, newPwd);
	}
	@Override
	public void save(EmpModel emp, Long[] roleUuids) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel role = new RoleModel();
			role.setUuid(uuid);
			roles.add(role);
		}
		emp.setRoles(roles);
		save(emp);
	}
	@Override
	public void update(EmpModel emp, Long[] roleUuids) {
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid:roleUuids){
			RoleModel role = new RoleModel();
			role.setUuid(uuid);
			roles.add(role);
		}
		emp.setRoles(roles);
		update(emp);
	}
	@Override
	public List<EmpModel> getEmpByDep(Long depUuid) {
		return empDao.getEmpByDep(depUuid);
	} 
	
}
