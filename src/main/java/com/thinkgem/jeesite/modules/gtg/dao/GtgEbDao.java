/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gtg.entity.GtgEb;

/**
 * 货比货-电商信息DAO接口
 * @author libn
 * @version 2015-08-04
 */
@MyBatisDao
public interface GtgEbDao extends CrudDao<GtgEb> {
	
}