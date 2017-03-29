package com.bxp.erp.invoice.goods.dao.dao;

import java.util.List;

import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goods.vo.GoodsQueryModel;

public interface GoodsDao {

	public void save(GoodsModel goods);

	public List<GoodsModel> getAll();

	public List<GoodsModel> getAll(GoodsQueryModel goodsq);

	public GoodsModel get(Long uuid);

	public void delete(GoodsModel goods);

	public List<GoodsModel> getAll(Long goodsTypeUuid);

	public void useNumUpdate();


}
