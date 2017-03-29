package com.bxp.erp.invoice.storedetail.vo;

import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.store.vo.StoreModel;

public class StoreDetailModel {

	private Long uuid;
	private StoreModel store;
	private GoodsModel goods;
	private Integer num;
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public StoreModel getStore() {
		return store;
	}
	public void setStore(StoreModel store) {
		this.store = store;
	}
	public GoodsModel getGoods() {
		return goods;
	}
	public void setGoods(GoodsModel goods) {
		this.goods = goods;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
