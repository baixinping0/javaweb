package com.bxp.erp.invoice.goodstype.business.ebo;

import java.util.List;

import com.bxp.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import com.bxp.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeModel;
import com.bxp.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

public class GoodsTypeEbo implements GoodsTypeEbi{
	private GoodsTypeDao goodsTypeDao;

	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	@Override
	public void save(GoodsTypeModel goodsType) {
		goodsTypeDao.save(goodsType);
	}

	@Override
	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	@Override
	public GoodsTypeModel get(Long uuid) {
		return goodsTypeDao.get(uuid);
	}

	@Override
	public void update(GoodsTypeModel goodsType) {
		GoodsTypeModel cGoodsType = get(goodsType.getUuid());
		cGoodsType.setName(goodsType.getName());
		cGoodsType.setSupplier(goodsType.getSupplier());
	}

	@Override
	public void delete(GoodsTypeModel goodsType) {
		goodsTypeDao.delete(goodsType);
	}

	@Override
	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel goodsTypeq) {
		return goodsTypeDao.getAll(goodsTypeq);
	}

	@Override
	public List<GoodsTypeModel> getAll(Long supplierUuid) {
		return goodsTypeDao.getAll(supplierUuid);
	}

	@Override
	public List<GoodsTypeModel> getAllHasGoods(Long supplierUuid) {
		return goodsTypeDao.getAllHasGoods(supplierUuid);
	}
	
}
