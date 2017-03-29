package com.bxp.erp.invoice.goods.business.ebo;

import java.util.List;

import com.bxp.erp.invoice.goods.business.ebi.GoodsEbi;
import com.bxp.erp.invoice.goods.dao.dao.GoodsDao;
import com.bxp.erp.invoice.goods.vo.GoodsModel;
import com.bxp.erp.invoice.goods.vo.GoodsQueryModel;

public class GoodsEbo implements GoodsEbi{
	private GoodsDao goodsDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	@Override
	public void save(GoodsModel goods) {
		goods.setMaxNum(300);
		goods.setMinNum(15);
		goods.setUseNum(0);
		goodsDao.save(goods);
	}

	@Override
	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	@Override
	public List<GoodsModel> getAll(GoodsQueryModel goodsq) {
		return goodsDao.getAll(goodsq);
	}

	@Override
	public GoodsModel get(Long uuid) {
		return goodsDao.get(uuid);
	}

	@Override
	public void update(GoodsModel goods) {
		GoodsModel cgoods = get(goods.getUuid());
		cgoods.setName(goods.getName());
		cgoods.setInPrice(goods.getInPrice());
		cgoods.setOutPrice(goods.getOutPrice());
		cgoods.setOrigin(goods.getOrigin());
		cgoods.setProducer(goods.getProducer());
		cgoods.setUnit(goods.getUnit());
	}

	@Override
	public void delete(GoodsModel goods) {
		goodsDao.delete(goods);
	}

	@Override
	public List<GoodsModel> getAll(Long goodsTypeUuid) {
		return goodsDao.getAll(goodsTypeUuid);
	}

	@Override
	public void useNumUpdate() {
		goodsDao.useNumUpdate();
		
	}

}
