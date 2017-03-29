package com.bxp.erp.util.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bxp.erp.auth.res.business.ebi.ResEbi;
import com.bxp.erp.auth.res.vo.ResModel;
import com.google.common.collect.Sets.SetView;

public class AllResLoadListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
		ResEbi resEbi = (ResEbi) wac.getBean("resEbi");
		List<ResModel> resList = resEbi.getAll();
		context.setAttribute("resList", resList);
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
