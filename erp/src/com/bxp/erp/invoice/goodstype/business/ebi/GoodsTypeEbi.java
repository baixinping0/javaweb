package com.bxp.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

@Transactional
public interface GoodsTypeEbi {

	public void save(GoodsTypeModel goodsType);

	public List<GoodsTypeModel> getAll();

	public GoodsTypeModel get(Long uuid);

	public void update(GoodsTypeModel goodsType);

	public void delete(GoodsTypeModel goodsType);

	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel goodsTypeq);

	public List<GoodsTypeModel> getAll(Long supplierUuid);

	/**
	 * 获取所有的具有商品的商品类别
	 * @param supplierUuid
	 * @return
	 */
	public List<GoodsTypeModel> getAllHasGoods(Long supplierUuid);

}
