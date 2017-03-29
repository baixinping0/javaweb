package com.bxp.erp.invoice.goods.vo;

import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;

public class GoodsModel {

	private Long uuid;
	private String name;
	private String origin;
	private String producer;
	private String unit;
	
	private Integer useNum;
	private Integer minNum;
	private Integer maxNum;
	
	private Double inPrice;
	private Double outPrice;
	private GoodsTypeModel goodsType;
	
	
	
	public Integer getMinNum() {
		return minNum;
	}
	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
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
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getInPrice() {
		return inPrice;
	}
	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
	}
	public Double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
	}
	public GoodsTypeModel getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(GoodsTypeModel goodsType) {
		this.goodsType = goodsType;
	}
	
}
