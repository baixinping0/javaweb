package com.bxp.erp.invoice.store.web;

import java.util.List;

import com.bxp.erp.auth.emp.business.ebi.EmpEbi;
import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.store.business.ebi.StoreEbi;
import com.bxp.erp.invoice.store.vo.StoreModel;
import com.bxp.erp.invoice.store.vo.StoreQueryModel;
import com.opensymphony.xwork2.ActionContext;

public class StoreAction {
	
	public StoreModel store = new StoreModel();
	public StoreQueryModel storeq = new StoreQueryModel();
	private StoreEbi storeEbi;
	private EmpEbi empEbi;

	
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}
	
	public String list(){
		List<StoreModel> storeList = storeEbi.getAll();
		ActionContext.getContext().put("storeList", storeList);
		return "list";
	}
	public String input(){
		
		List<EmpModel> empList = empEbi.getAll();
		ActionContext.getContext().put("empList", empList);
		return "input";
	}
	public String save(){
		storeEbi.save(store);
		return "doList";
	}
}
