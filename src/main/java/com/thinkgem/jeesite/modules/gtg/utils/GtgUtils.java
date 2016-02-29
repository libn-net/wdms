package com.thinkgem.jeesite.modules.gtg.utils;

import java.util.List;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.gtg.dao.GtgEbDao;
import com.thinkgem.jeesite.modules.gtg.entity.GtgEb;

public class GtgUtils {
	private static GtgEbDao gtgEbDao = SpringContextHolder.getBean(GtgEbDao.class);
	
	/**
	 * 获取电商信息
	 * @return
	 */
	public static List<GtgEb> getEbList(){
		
		GtgEb gtgEb = new GtgEb();
// 		app.setDelFlag(BaseEntity.DEL_FLAG_NORMAL);
 		List<GtgEb> gtgEbs = gtgEbDao.findList(gtgEb);
		
		return gtgEbs;
	}
	
	
}
