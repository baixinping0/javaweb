package com.bxp.erp.invoice.goodstype.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

public interface GoodsTypeDao {

	public void save(GoodsTypeModel goodsType);

	public List<GoodsTypeModel> getAll();

	public GoodsTypeModel get(Long uuid);

	public void delete(GoodsTypeModel goodsType);

	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel goodsTypeq);

	public List<GoodsTypeModel> getAll(Long supplierUuid);

	public List<GoodsTypeModel> getAllHasGoods(Long supplierUuid);

}
