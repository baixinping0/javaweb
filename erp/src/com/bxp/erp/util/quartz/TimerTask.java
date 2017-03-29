package com.bxp.erp.util.quartz;

import java.math.BigInteger;
import java.text.Format;
import java.util.Date;
import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.bxp.erp.invoice.goods.business.ebi.GoodsEbi;
import com.bxp.erp.invoice.storedetail.business.ebi.StoreDetailEbi;
import com.bxp.erp.util.format.FormatUtil;

public class TimerTask {
	
	private GoodsEbi goodsEbi;
	private MailSender mailSender;
	private StoreDetailEbi storeDetailEbi;
	
	
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}
	public void useNumUpdate(){
		goodsEbi.useNumUpdate();
	}
	public void storeWare(){
		//获取预警数据，然后发送email
		
		/*
select 
	g.name,
	g.minNum > sum(sd.num) min,
	g.maxNum < sum(sd.num) max
from 
	tbl_storeDetail sd, 
	tbl_goods g
where 
	g.uuid = sd.goodsUuid 
group by
	g.uuid	
		*/
		
		List<Object[]> warnLines = storeDetailEbi.getWarnInfo();
		//1、获取email发送器对象
		
		//2、组织要发送的email信息内容
		SimpleMailMessage message = new SimpleMailMessage();
		
		//设置发送人，必须设置，和配置文件中配置的用户名相同
		message.setFrom("1111@126.com");
		//设置接受人信息
		message.setTo("16474@126.com");
		//设置主题
		message.setSubject("库存预警 ： " + FormatUtil.formatDate(System.currentTimeMillis()));
		//设置内容
		StringBuilder warnInfo = new StringBuilder();
		for(Object[] objs : warnLines){
			
			BigInteger minFlag = (BigInteger) objs[1];
			if(minFlag.intValue() == 1){
				String goodsName = objs[0].toString();
				warnInfo.append("商品[ ");
				warnInfo.append(goodsName);
				warnInfo.append(" ]低于下限，请及时补货！！！\r\n");
				continue;
			}
			BigInteger maxFlag = (BigInteger) objs[2];
			if(maxFlag.intValue() == 1){
				String goodsName = objs[0].toString();
				warnInfo.append("商品[ ");
				warnInfo.append(goodsName);
				warnInfo.append(" ]低于高于上线，请禁止进货！！！\r\n");
			}
		}
		
		System.out.println(warnInfo.toString());
		
		message.setText(warnInfo.toString());
		//设置即时发送
		message.setSentDate(new Date());
		
		
		//3、发送email
		mailSender.send(message);
	}
}






