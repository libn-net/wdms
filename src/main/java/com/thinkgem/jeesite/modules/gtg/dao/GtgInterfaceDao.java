/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.gtg.entity.GtgInterface;

/**
 * 货比货-接口信息DAO接口
 * @author libn
 * @version 2015-08-04
 */
@MyBatisDao
public interface GtgInterfaceDao extends CrudDao<GtgInterface> {
	
}