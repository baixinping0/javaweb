package com.bxp.erp.invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;

public class SupplierModel {
	
	private static final Integer NEEDS_VALUE_SELF = 1;
	private static final Integer NEEDS_VALUE_NOT_SELF = 0;
	private static final String NEEDS_VALUE_SELF_VIEW = "自提";
	private static final String NEEDS_VALUE_NOT_SELF_VIEW = "送货";
	public static Map<Integer, String> needsMap;
	
	static{
		needsMap = new HashMap<Integer, String>();
		needsMap.put(NEEDS_VALUE_NOT_SELF, NEEDS_VALUE_NOT_SELF_VIEW);
		needsMap.put(NEEDS_VALUE_SELF, NEEDS_VALUE_SELF_VIEW);
	}
	private Long uuid;
	private String name;
	private String address;
	private String contact;
	private String tele;
	private Integer needs;
	
	
	//视图值
	private String needsView;
	
	
	public String getNeedsView() {
		return needsView;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Integer getNeeds() {
		return needs;
	}
	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView = needsMap.get(needs);
	}
}
