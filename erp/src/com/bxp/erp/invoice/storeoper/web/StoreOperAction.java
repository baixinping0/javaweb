package com.bxp.erp.invoice.storeoper.web;

import com.bxp.erp.invoice.storeoper.business.ebi.StoreOperEbi;
import com.opensymphony.xwork2.ActionSupport;

public class StoreOperAction extends ActionSupport{
	
	private StoreOperEbi storeOperEbi;

	public void setStoreOperEbi(StoreOperEbi storeOperEbi) {
		this.storeOperEbi = storeOperEbi;
	}
	
	
}
