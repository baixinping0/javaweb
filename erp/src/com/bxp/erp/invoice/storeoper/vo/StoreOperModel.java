package com.bxp.erp.invoice.storeoper.vo;

import java.util.HashMap;
import java.util.Map;

import com.bxp.erp.auth.emp.vo.EmpModel;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.store.vo.StoreModel;
import com.bxp.erp.util.format.FormatUtil;

public class StoreOperModel {
	
	public static final Integer STOREOPER_TYPE_OF_IN = 1;
	public static final Integer STOREOPER_TYPE_OF_OUT = 2;
	
	public static final String STOREOPER_TYPE_OF_IN_VIEW = "入库";
	public static final String STOREOPER_TYPE_OF_OUT_VIEW = "出库";
	public static Map<Integer, String> typeMap = new HashMap<Integer, String>();
	static{
		typeMap.put(STOREOPER_TYPE_OF_IN, STOREOPER_TYPE_OF_IN_VIEW);
		typeMap.put(STOREOPER_TYPE_OF_OUT, STOREOPER_TYPE_OF_OUT_VIEW);
	}

	private Long uuid;
	
	private Integer num;
	
	private Long operTime;
	private Integer type;
	
	private String operTimeView;
	private String typeView;
	
	private EmpModel emp;
	private GoodsModel goods;
	private StoreModel store;
	
	
	public String getOperTimeView() {
		return operTimeView;
	}
	public String getTypeView() {
		return typeView;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getOperTime() {
		return operTime;
	}
	public void setOperTime(Long operTime) {
		this.operTime = operTime;
		this.operTimeView = FormatUtil.formatDate(operTime);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
	}
	public EmpModel getEmp() {
		return emp;
	}
	public void setEmp(EmpModel emp) {
		this.emp = emp;
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	public StoreModel getStore() {
		return store;
	}
	public void setStore(StoreModel store) {
		this.store = store;
	}
	
	
}
