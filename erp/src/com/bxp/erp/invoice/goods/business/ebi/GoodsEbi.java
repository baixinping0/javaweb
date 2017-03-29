package com.bxp.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goods.vo.GoodsQueryModel;

@Transactional
public interface GoodsEbi {

	public void save(GoodsModel goods);

	public List<GoodsModel> getAll();

	/**
	 * 通过条件获取所有满足条件的商品
	 * @param goodsq
	 * @return
	 */
	public List<GoodsModel> getAll(GoodsQueryModel goodsq);

	public GoodsModel get(Long uuid);

	public void update(GoodsModel goods);

	public void delete(GoodsModel goods);

	/**
	 * 通过商品类别获取所有的商品
	 * @param uuid
	 * @return
	 */
	public List<GoodsModel> getAll(Long goodsTypeUuid);

	public void useNumUpdate();
	

}
