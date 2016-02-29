/**
 * 
 */
package com.thinkgem.jeesite.modules.gtg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.gtg.entity.GtgEb;
import com.thinkgem.jeesite.modules.gtg.dao.GtgEbDao;

/**
 * 货比货-电商信息Service
 * @author libn
 * @version 2015-08-04
 */
@Service
@Transactional(readOnly = true)
public class GtgEbService extends CrudService<GtgEbDao, GtgEb> {

	public GtgEb get(String id) {
		return super.get(id);
	}
	
	public List<GtgEb> findList(GtgEb gtgEb) {
		return super.findList(gtgEb);
	}
	
	public Page<GtgEb> findPage(Page<GtgEb> page, GtgEb gtgEb) {
		return super.findPage(page, gtgEb);
	}
	
	@Transactional(readOnly = false)
	public void save(GtgEb gtgEb) {
		super.save(gtgEb);
	}
	
	@Transactional(readOnly = false)
	public void delete(GtgEb gtgEb) {
		super.delete(gtgEb);
	}
	
}