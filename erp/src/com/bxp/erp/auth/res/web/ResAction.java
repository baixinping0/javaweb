package com.bxp.erp.auth.res.web;

import java.util.List;

import com.bxp.erp.auth.res.business.ebi.ResEbi;
import com.bxp.erp.auth.res.vo.ResModel;
import com.bxp.erp.auth.res.vo.ResQueryModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ResAction extends ActionSupport {
	public ResModel res = new ResModel();
	public ResQueryModel resq = new ResQueryModel();
	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public String list() {
		List<ResModel> resList = resEbi.getAll(resq);
		ActionContext.getContext().put("resList", resList);
		return "list";
	}

	public String input() {
		if (res.getUuid() != null)
			res = resEbi.get(res.getUuid());
		return "input";
	}

	public String save() {
		if (res.getUuid() == null)
			resEbi.save(res);
		else
			resEbi.update(res);
		return "doList";
	}

	public String delete() {
		resEbi.delete(res);
		return "doList";
	}
}
