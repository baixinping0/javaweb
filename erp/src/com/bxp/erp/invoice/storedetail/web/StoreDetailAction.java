package com.bxp.erp.invoice.storedetail.web;

import com.bxp.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import com.opensymphony.xwork2.ActionSupport;

public class StoreDetailAction extends ActionSupport{
	private StoreDetailEbi storeDetailEbi;

	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}
	
	
}
